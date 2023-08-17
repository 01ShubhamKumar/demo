package com.example.service;

import com.example.payload.PostDto;
import com.example.payload.PostResposne;

import java.util.List;

public interface PostService {
  PostDto createPost(PostDto postDto);

  PostResposne getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(long id);

  PostDto updatePost(PostDto postDto, long id);

  void deletePostById(long id);
}
