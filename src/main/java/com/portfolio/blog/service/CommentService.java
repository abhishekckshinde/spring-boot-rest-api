package com.portfolio.blog.service;

import com.portfolio.blog.dto.CommentDto;

public interface CommentService {

    CommentDto getComments(Integer postId);

    //Create new comment against a Blog Post
    CommentDto createComment(Long postId, CommentDto commentDto);
}
