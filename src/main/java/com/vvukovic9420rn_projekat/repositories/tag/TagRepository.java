package com.vvukovic9420rn_projekat.repositories.tag;

import com.vvukovic9420rn_projekat.entities.Tag;

public interface TagRepository {

    Tag addTag(Tag tag);
    Tag getTagByName(String name);
}
