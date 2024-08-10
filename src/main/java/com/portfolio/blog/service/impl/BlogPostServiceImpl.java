package com.portfolio.blog.service.impl;

import com.portfolio.blog.dto.BlogPostDto;
import com.portfolio.blog.dto.BlogResponseDto;
import com.portfolio.blog.entity.BlogPost;
import com.portfolio.blog.exception.ResourceNotFoundException;
import com.portfolio.blog.repositories.BlogPostRepository;
import com.portfolio.blog.service.BlogPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;

    //    @Autowired : Annotation is not needed if only one argument is added in constructor!
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public BlogPostDto createBlogPost(BlogPostDto blogPostDto) {
        //Validate DTO sent by user.

        //If validation passes, convert DTO to entity.
        BlogPost newPost = mapToEntity(blogPostDto);
        //Save Entity
        BlogPost savedPost = blogPostRepository.save(newPost);
        //Convert to DTO and return the DTO.
        return mapToDTO(savedPost);
    }

    /**
     * @return : List of Blog posts
     */
    @Override
    public BlogResponseDto getAllBlogPosts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(sortDir) ? Sort.by(Sort.Order.asc(sortBy)) : Sort.by(Sort.Order.desc(sortBy));
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<BlogPost> blogPostPage = blogPostRepository.findAll(pageable);
        BlogResponseDto blogResponseDto = new BlogResponseDto();
        blogResponseDto.setBlogPostDtoList(blogPostPage.getContent().stream().map(this::mapToDTO).collect(Collectors.toList()));
        blogResponseDto.setPageNumber(blogPostPage.getNumber());
        blogResponseDto.setPageSize(blogPostPage.getSize());
        blogResponseDto.setTotalPages(blogPostPage.getTotalPages());
        blogResponseDto.setTotalElements(blogPostPage.getTotalElements());
        blogResponseDto.setLastPage(blogPostPage.isLast());
        return blogResponseDto;
    }

    /**
     * @param postId : ID of blog post to find.
     * @return : Bolg Post associated with selected ID.
     */
    @Override
    public BlogPostDto getBlogPost(Long postId) {
        return blogPostRepository.findById(postId).map(this::mapToDTO).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", String.valueOf(postId)));
    }

    /**
     * @param blogPostDto : Details to update
     * @param id          : Id of Blog Post to update
     */
    @Override
    public BlogPostDto updateBlogPost(BlogPostDto blogPostDto, Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setDescription(blogPostDto.getDescription());
        blogPost.setContent(blogPostDto.getContent());
        return mapToDTO(blogPostRepository.saveAndFlush(blogPost));
    }

    @Override
    public void deleteBlogPost(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        blogPostRepository.delete(blogPost);
    }


    private BlogPost mapToEntity(BlogPostDto blogPostDto) {
        BlogPost newPost = new BlogPost();
        newPost.setPostId(blogPostDto.getPostId());
        newPost.setTitle(blogPostDto.getTitle());
        newPost.setDescription(blogPostDto.getDescription());
        newPost.setContent(blogPostDto.getContent());
        return newPost;
    }

    private BlogPostDto mapToDTO(BlogPost blogPost) {
        BlogPostDto postDto = new BlogPostDto();
        postDto.setPostId(blogPost.getPostId());
        postDto.setTitle(blogPost.getTitle());
        postDto.setDescription(blogPost.getDescription());
        postDto.setContent(blogPost.getContent());
        return postDto;
    }
}
