package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.Comment;
import com.vvukovic9420rn_projekat.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsFromArticle(@PathParam("id") Integer articleId){
        return commentService.getCommentsFromArticle(articleId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(@Valid Comment comment){
        return commentService.addComment(comment);
    }
}
