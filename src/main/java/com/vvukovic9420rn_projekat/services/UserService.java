package com.vvukovic9420rn_projekat.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.vvukovic9420rn_projekat.entities.User;
import com.vvukovic9420rn_projekat.repositories.user.UserRepository;
import com.vvukovic9420rn_projekat.requests.ChangeUserStatusRequest;
import com.vvukovic9420rn_projekat.requests.CreateUserRequest;
import com.vvukovic9420rn_projekat.requests.UpdateUserRequest;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    private static final String JWT_SECRET = "wp_secret_key";

    @Inject
    private UserRepository userRepository;

    public String login(String email, String password){
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("type", user.getType())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();

        User user = this.userRepository.getUserByEmail(email);

        if (user == null){
            return false;
        }

        return true;
    }

    public boolean isAdmin(String token){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        jwt.getClaim("type").asString();

        User user = this.userRepository.getUserByEmail(email);

        if (user == null){
            return false;
        }

        if(user.getType().equals("Admin")){
            return true;
        }
        else {
            return false;
        }
    }

    public List<User> getAllUsers(int page){
        return userRepository.getAllUsers(page);
    }

    public void addUser(CreateUserRequest newUser){
        String hashedPassword = DigestUtils.sha256Hex(newUser.getPassword());

        User user = new User();
        user.setPassword(hashedPassword);
        user.setActive(true);
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setType(newUser.getType());
        user.setEmail(newUser.getEmail());

        userRepository.addUser(user);
    }

    public void updateUser(UpdateUserRequest updateUser){
        User user = new User();
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setType(updateUser.getType());
        user.setId(updateUser.getId());

        userRepository.updateUser(user);
    }

    public void changeUserStatus(ChangeUserStatusRequest userStatus){
        userRepository.changeStatusById(userStatus.getUserId(), !userStatus.getStatus());
    }
}
