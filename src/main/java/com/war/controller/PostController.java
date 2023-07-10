package com.war.controller;

import com.war.payload.PostDto;
import com.war.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
@Autowired
private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> savePosts(@RequestBody PostDto postDto){
        PostDto dto=postService.savePosts(postDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<PostDto> findAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,
                                      @RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir){
        List<PostDto> dto=postService.findAllPosts(pageNo, pageSize, sortBy, sortDir);
        return dto;
    }
}
