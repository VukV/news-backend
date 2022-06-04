package com.vvukovic9420rn_projekat.repositories.comment;

import com.vvukovic9420rn_projekat.entities.Comment;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.util.List;

public class PgCommentRepository extends Postgres implements CommentRepository {

    @Override
    public void deleteCommentsFromArticle(Integer articleId) {

    }

    @Override
    public Comment addComment(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getCommentsFromArticle(Integer articleId) {
        return null;
    }
}
