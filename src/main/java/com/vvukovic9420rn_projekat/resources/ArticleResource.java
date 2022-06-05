package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.requests.CreateArticleRequest;
import com.vvukovic9420rn_projekat.services.ArticleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articles")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Path("/content")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticles(@DefaultValue("1") @QueryParam("page") int page){
        return articleService.getAllArticles(page);
    }

    @POST
    @Path("/content")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArticle(CreateArticleRequest createArticleRequest){
        //TODO
        return Response.status(200).build();
    }

    @PUT
    @Path("/content")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArticle(CreateArticleRequest updateArticle){
        //TODO
        return Response.status(200).build();
    }

    @DELETE
    @Path("/content/{id}")
    public Response deleteArticle(@PathParam("id") Integer id){
        articleService.deleteArticle(id);
        return Response.status(200).build();
    }

    @GET
    @Path("/newest")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getNewestArticles(){
        return articleService.getNewestArticles();
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getTopArticles(){
        return articleService.getTopArticles();
    }

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticlesByCategory(@DefaultValue("1") @QueryParam("page") int page, @PathParam("id") Integer categoryId){
        return articleService.getArticlesByCategory(categoryId, page);
    }

    @GET
    @Path("/article/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") Integer articleId){
        return articleService.getArticleById(articleId);
    }
}
