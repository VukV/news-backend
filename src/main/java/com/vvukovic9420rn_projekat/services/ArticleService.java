package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.entities.ArticleTag;
import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.article.ArticleRepository;
import com.vvukovic9420rn_projekat.repositories.articletag.ArticleTagRepository;
import com.vvukovic9420rn_projekat.repositories.comment.CommentRepository;
import com.vvukovic9420rn_projekat.repositories.tag.TagRepository;
import com.vvukovic9420rn_projekat.requests.CreateArticleRequest;
import com.vvukovic9420rn_projekat.requests.UpdateArticleRequest;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;
    @Inject
    private CommentRepository commentRepository;
    @Inject
    private ArticleTagRepository articleTagRepository;
    @Inject
    private TagRepository tagRepository;

    public List<Article> getAllArticles(Integer page){
        return articleRepository.getAllArticlesSortedByDate(page);
    }

    public List<Article> getArticlesByCategory(Integer id, Integer page){
        return articleRepository.getAllArticlesByCategorySortedByDate(id, page);
    }

    public void addArticle(CreateArticleRequest createArticleRequest){
        Article article = new Article();
        article.setCategoryId(createArticleRequest.getCategoryId());
        article.setUserId(createArticleRequest.getUserId());
        article.setTitle(createArticleRequest.getTitle());
        article.setContent(createArticleRequest.getContent());

        article = articleRepository.addArticle(article);

        Tag currentTag;
        String[] tags = createArticleRequest.getTags().split(" ");

        for(int i = 0; i < tags.length; i++){
            currentTag = tagRepository.getTagByName(tags[i]);

            if (currentTag == null){
                currentTag = new Tag();
                currentTag.setName(tags[i]);

                currentTag = tagRepository.addTag(currentTag);
            }

            ArticleTag articleTag = new ArticleTag(article.getId(), currentTag.getId());
            articleTagRepository.addTagToArticle(articleTag);
        }

    }

    public void updateArticle(UpdateArticleRequest updateArticle){
        Article article = new Article();
        article.setId(updateArticle.getId());
        article.setCategoryId(updateArticle.getCategoryId());
        article.setUserId(updateArticle.getUserId());
        article.setTitle(updateArticle.getTitle());
        article.setContent(updateArticle.getContent());

        article = articleRepository.updateArticle(article);
        articleTagRepository.deleteArticle(article.getId());

        Tag currentTag;
        String[] tags = updateArticle.getTags().split(" ");

        for(int i = 0; i < tags.length; i++){
            currentTag = tagRepository.getTagByName(tags[i]);

            if (currentTag == null){
                currentTag = new Tag();
                currentTag.setName(tags[i]);

                currentTag = tagRepository.addTag(currentTag);
            }

            ArticleTag articleTag = new ArticleTag(article.getId(), currentTag.getId());
            articleTagRepository.addTagToArticle(articleTag);
        }
    }

    public void deleteArticle(Integer articleId){
        articleRepository.deleteArticleById(articleId);
        commentRepository.deleteCommentsFromArticle(articleId);
        articleTagRepository.deleteArticle(articleId);
    }

    public List<Article> getNewestArticles(){
        return articleRepository.getNewestTenArticles();
    }

    public List<Article> getTopArticles(){
        return articleRepository.getTopTenArticles();
    }

    public Article getArticleById(Integer id){
        return articleRepository.getArticleById(id);
    }

    public List<Article> getArticlesByTag(Integer articleId, Integer page){
        return articleTagRepository.getArticlesFromTag(articleId, page);
    }
}
