package com.vvukovic9420rn_projekat.repositories.comment;

import com.vvukovic9420rn_projekat.entities.Comment;

import java.util.List;

public interface CommentRepository {

    void deleteCommentsFromArticle(Integer articleId);

    Comment addComment(Comment comment);
    List<Comment> getCommentsFromArticle(Integer articleId);
}
