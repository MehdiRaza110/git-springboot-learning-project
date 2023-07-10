package com.war.service;

import com.war.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto savePosts(PostDto postDto);

    List<PostDto> findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
