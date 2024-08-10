package com.portfolio.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogPostDto {

    private Long postId;
    private String title;
    private String description;
    private String content;
}
