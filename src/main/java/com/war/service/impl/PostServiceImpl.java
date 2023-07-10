package com.war.service.impl;

import com.war.entity.Post;
import com.war.payload.PostDto;
import com.war.repository.PostRepository;
import com.war.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Model layer..
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepo;

    @Override
    public PostDto savePosts(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post save = postRepo.save(post);
        PostDto results = mapToDto(save);
        return results;
    }

    @Override
    public List<PostDto> findAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> all = postRepo.findAll(pageable);
        List<PostDto> posts = all.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return posts;
    }

    Post mapToEntity(PostDto postDto){
       return modelMapper.map(postDto, Post.class);
    }
    PostDto mapToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }
}
