package com.vvukovic9420rn_projekat.filters;

import com.vvukovic9420rn_projekat.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (!this.isAuthRequired(containerRequestContext)) {
            return;
        }

        String token = containerRequestContext.getHeaderString("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        if (isAdminRequired(containerRequestContext)){
            if (!userService.isAdmin(token)){
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        else {
            if (!userService.isAuthorized(token)) {
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext){
        if (!requestContext.getUriInfo().getPath().contains("content") && !requestContext.getUriInfo().getPath().contains("admin")) {
            return false;
        }

        return true;
    }

    private boolean isAdminRequired(ContainerRequestContext requestContext){
        if (requestContext.getUriInfo().getPath().contains("admin")){
            return true;
        }

        return false;
    }
}
