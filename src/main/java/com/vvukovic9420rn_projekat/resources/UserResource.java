package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.User;
import com.vvukovic9420rn_projekat.requests.ChangeUserStatusRequest;
import com.vvukovic9420rn_projekat.requests.CreateUserRequest;
import com.vvukovic9420rn_projekat.requests.LoginRequest;
import com.vvukovic9420rn_projekat.requests.UpdateUserRequest;
import com.vvukovic9420rn_projekat.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest){
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            return Response.status(422, "Unprocessable Entity").build();
        }

        response.put("jwt", this.userService.login(loginRequest.getEmail(), loginRequest.getPassword()));

        return Response.ok(response).build();
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(@DefaultValue("1") @QueryParam("page") int page){
        return userService.getAllUsers(page);
    }

    @POST
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid CreateUserRequest newUser){
        if(!newUser.getType().equals("Admin") || !newUser.getType().equals("Creator")){
            return Response.status(400).build();
        }

        if (newUser.getPassword().equals(newUser.getPasswordRepeat())){
            userService.addUser(newUser);
            return Response.status(200).build();
        }

       return Response.status(400).build();
    }

    @PUT
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Valid UpdateUserRequest updateUser){
        if(!updateUser.getType().equals("Admin") || !updateUser.getType().equals("Creator")){
            return Response.status(400).build();
        }

        userService.updateUser(updateUser);
        return Response.status(200).build();
    }

    @PUT
    @Path("/admin/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeUserStatus(@Valid ChangeUserStatusRequest status){
        userService.changeUserStatus(status);
        return Response.status(200).build();
    }
}
