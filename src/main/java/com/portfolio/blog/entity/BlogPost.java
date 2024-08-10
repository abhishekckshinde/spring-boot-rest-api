package com.portfolio.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BLOG_POSTS", uniqueConstraints = @UniqueConstraint(columnNames = "POST_TITLE"))
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "POST_TITLE", nullable = false)
    private String title;
    @Column(name = "POST_DESC", nullable = false)
    private String description;
    @Column(name = "POST_CONTENT", nullable = false)
    private String content;
    @OneToMany(mappedBy = "blogPost" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
