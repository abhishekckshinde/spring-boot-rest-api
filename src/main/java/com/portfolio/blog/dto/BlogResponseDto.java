package com.portfolio.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponseDto {
    private List<BlogPostDto> blogPostDtoList;
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
