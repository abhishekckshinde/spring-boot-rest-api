package com.portfolio.blog.service.impl;

import com.portfolio.blog.dto.CommentDto;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.entity.Comment;
import com.portfolio.blog.exception.ResourceNotFoundException;
import com.portfolio.blog.repositories.BlogPostRepository;
import com.portfolio.blog.repositories.CommentRepository;
import com.portfolio.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final BlogPostRepository blogPostRepository;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(BlogPostRepository blogPostRepository, CommentRepository commentRepository) {
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * @param postId
     * @return
     */
    @Override
    public CommentDto getComments(Integer postId) {
        return null;
    }

    /**
     * This method will create a Comment against given Blog Post Id
     * @param postId : The post id user is commenting upon
     * @param commentDto : The details entered by User
     * @return : Saved comment dto
     */
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(postId);
        Comment comment = getComment(commentDto);
        comment.setBlogPost(blogPost.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", String.valueOf(postId))));
        Comment savedComment = commentRepository.save(comment);
        return getCommentDto(savedComment);
    }

    //Map Entity to DTO
    private static CommentDto getCommentDto(Comment savedComment) {
        CommentDto responseDto = new CommentDto();
        responseDto.setUsername(savedComment.getUsername());
        responseDto.setEmail(savedComment.getEmail());
        responseDto.setBody(savedComment.getBody());
        return responseDto;
    }

    //Map DTO to Entity
    private static Comment getComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setUsername(commentDto.getUsername());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
