package com.vvukovic9420rn_projekat.resources;

import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.services.ArticleTagService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tags")
public class TagResource {

    @Inject
    private ArticleTagService articleTagService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> getTagsFromArticle(@PathParam("id") Integer articleId){
        return articleTagService.getTagsFromArticle(articleId);
    }
}
