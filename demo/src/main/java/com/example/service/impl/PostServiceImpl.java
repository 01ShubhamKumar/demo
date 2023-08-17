package com.example.service.impl;

import com.example.entity.Post;
import com.example.exception.ResourceNotFoundException;
import com.example.payload.PostDto;
import com.example.payload.PostResposne;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
private PostRepository postRepository;
private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.mapper=mapper;
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post= mapToEntity(postDto);
       Post newpost = postRepository.save(post);
        PostDto newPostDto= mapToDto(newpost);
        return newPostDto ;
    }



    Post mapToEntity(PostDto postDto){
       Post post= mapper.map(postDto,Post.class);
       // Post post=new Post();
       // post.setTitle(postDto.getTitle());
        //post.setDescription(postDto.getDescription());
        //post.setContent(postDto.getContent());
        return post;
    }

    @Override
    public PostResposne getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
 Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable =PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
       List<Post> contents= posts.getContent();

       List<PostDto> postDtos = contents.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResposne postReponse=new PostResposne();
        postReponse.setContent(postDtos);
        postReponse.setPageNo(posts.getNumber());
        postReponse.setTotalElement(posts.getTotalElements());
        postReponse.setTotalPages(posts.getTotalPages());
        postReponse.setPageSize(posts.getSize());
        postReponse.setLast(posts.isLast());
        return postReponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return  mapToDto(updatedPost);

    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);
    }

    PostDto mapToDto(Post post){
       PostDto postDto= mapper.map(post,PostDto.class);
        //PostDto postDto=new PostDto();
        //postDto.setId(post.getId());
       // postDto.setTitle(post.getTitle());
        //postDto.setDescription(post.getDescription());
        //postDto.setContent(post.getContent());
        return postDto;
    }

}
