package com.portfolio.blog.controller;

import com.portfolio.blog.dto.BlogPostDto;
import com.portfolio.blog.dto.BlogResponseDto;
import com.portfolio.blog.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    /**
     * This method will create a new Blog Post and return response to client.
     *
     * @param blogPostDto : The post details to be saved.
     * @return blogPostDto : The Blog Post which was created and saved in Database.
     */
    @PostMapping("/create")
    public ResponseEntity<BlogPostDto> createPost(@RequestBody BlogPostDto blogPostDto) {
        return new ResponseEntity<>(blogPostService.createBlogPost(blogPostDto), HttpStatus.CREATED);
    }

    /**
     * This method will retrieve all Blog Posts and return to client.
     *
     * @return : List of all Blog Posts
     */
    @GetMapping
    public ResponseEntity<BlogResponseDto> getAllPosts(
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "postId") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
        return new ResponseEntity<>(blogPostService.getAllBlogPosts(pageSize, pageNo, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDto> getBlogPost(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(blogPostService.getBlogPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDto> updateBlogPost(@RequestBody BlogPostDto blogPostDto, @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(blogPostDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable(value = "id") Long id) {
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
