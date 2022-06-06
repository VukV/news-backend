package com.vvukovic9420rn_projekat;

import com.vvukovic9420rn_projekat.repositories.article.ArticleRepository;
import com.vvukovic9420rn_projekat.repositories.article.PgArticleRepository;
import com.vvukovic9420rn_projekat.repositories.articletag.ArticleTagRepository;
import com.vvukovic9420rn_projekat.repositories.articletag.PgArticleTagRepository;
import com.vvukovic9420rn_projekat.repositories.category.CategoryRepository;
import com.vvukovic9420rn_projekat.repositories.category.PgCategoryRepository;
import com.vvukovic9420rn_projekat.repositories.comment.CommentRepository;
import com.vvukovic9420rn_projekat.repositories.comment.PgCommentRepository;
import com.vvukovic9420rn_projekat.repositories.tag.PgTagRepository;
import com.vvukovic9420rn_projekat.repositories.tag.TagRepository;
import com.vvukovic9420rn_projekat.repositories.user.PgUserRepository;
import com.vvukovic9420rn_projekat.repositories.user.UserRepository;
import com.vvukovic9420rn_projekat.services.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class NewsApplication extends ResourceConfig {

    public NewsApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(PgArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);
                this.bind(PgArticleTagRepository.class).to(ArticleTagRepository.class).in(Singleton.class);
                this.bind(PgCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(PgCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(PgTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(PgUserRepository.class).to(UserRepository.class).in(Singleton.class);

                this.bindAsContract(ArticleService.class);
                this.bindAsContract(ArticleTagService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(UserService.class);
            }
        };

        register(binder);
        packages("com.vvukovic9420rn_projekat");
    }
}
