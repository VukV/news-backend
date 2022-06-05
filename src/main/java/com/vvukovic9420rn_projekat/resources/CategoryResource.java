package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.Category;
import com.vvukovic9420rn_projekat.services.ArticleService;
import com.vvukovic9420rn_projekat.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Path("/content")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories(@DefaultValue("1") @QueryParam("page") int page){
        return categoryService.getAllCategories(page);
    }

    @POST
    @Path("/content")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(@Valid Category category){
        categoryService.addCategory(category);
        return Response.status(200).build();
    }

    @PUT
    @Path("/content")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(@Valid Category category){
        categoryService.updateCategory(category);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/content/{id}")
    public Response deleteCategory(@PathParam("id") Integer id){
        int status = categoryService.deleteCategory(id);
        return Response.status(status).build();
    }

}
