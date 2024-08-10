package com.portfolio.blog.service;

import com.portfolio.blog.dto.BlogPostDto;
import com.portfolio.blog.dto.BlogResponseDto;

public interface BlogPostService {

    BlogPostDto createBlogPost(BlogPostDto blogPostDto);

    BlogResponseDto getAllBlogPosts(int pageSize, int pageNo, String sortBy, String sortDir);

    BlogPostDto getBlogPost(Long postId);

    BlogPostDto updateBlogPost(BlogPostDto blogPostDto, Long id);

    void deleteBlogPost(Long id);
}
