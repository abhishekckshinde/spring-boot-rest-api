package com.portfolio.blog.dto;

import com.portfolio.blog.entity.BlogPost;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String email;
    private String username;
    private String body;
}
