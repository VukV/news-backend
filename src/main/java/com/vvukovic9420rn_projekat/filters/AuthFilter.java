package com.vvukovic9420rn_projekat.filters;

import com.vvukovic9420rn_projekat.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
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

        //TODO userService auth
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext){
        if (requestContext.getUriInfo().getPath().contains("login")) {
            return false;
        }

        //TODO GET ZA NEWS -> RETURN FALSE

        return true;
    }
}
