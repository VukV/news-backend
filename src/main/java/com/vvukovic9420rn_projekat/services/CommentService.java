package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Comment;
import com.vvukovic9420rn_projekat.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    public List<Comment> getCommentsFromArticle(Integer articleId){
        return commentRepository.getCommentsFromArticle(articleId);
    }

    public Comment addComment(Comment comment){
        return commentRepository.addComment(comment);
    }
}