package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.articletag.ArticleTagRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleTagService {

    @Inject
    private ArticleTagRepository articleTagRepository;

    public List<Tag> getTagsFromArticle(Integer articleId){
        return articleTagRepository.getTagsFromArticle(articleId);
    }
}
