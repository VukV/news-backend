package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.User;
import com.vvukovic9420rn_projekat.requests.ChangeUserStatusRequest;
import com.vvukovic9420rn_projekat.requests.CreateUserRequest;
import com.vvukovic9420rn_projekat.requests.UpdateUserRequest;
import com.vvukovic9420rn_projekat.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(@DefaultValue("1") @QueryParam("page") int page){
        return userService.getAllUsers(page);
    }

    @POST
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(CreateUserRequest newUser){
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
    public Response updateUser(UpdateUserRequest updateUser){
        if(!updateUser.getType().equals("Admin") || !updateUser.getType().equals("Creator")){
            return Response.status(400).build();
        }

        userService.updateUser(updateUser);
        return Response.status(200).build();
    }

    @PUT
    @Path("/admin/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeUserStatus(ChangeUserStatusRequest status){
        userService.changeUserStatus(status);
        return Response.status(200).build();
    }
}
