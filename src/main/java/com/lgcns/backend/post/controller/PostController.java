package com.lgcns.backend.post.controller;

import com.lgcns.backend.global.response.CustomResponse;
import com.lgcns.backend.post.dto.PostRequest;
import com.lgcns.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.lgcns.backend.post.dto.PostRequest.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comm")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<CustomResponse<?>> getPostList(
            @RequestParam(required = false) String category,
            Pageable pageable
    ){
        if (category != null) {
            return ResponseEntity.ok(CustomResponse.ok(postService.getPostListByCategory(category, pageable)));
        } else {
            return ResponseEntity.ok(CustomResponse.ok(postService.getPostList(pageable)));
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CustomResponse<?>> getPostDetail(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(CustomResponse.ok(postService.getPostDetail(postId)));
    }

    @PostMapping
    public ResponseEntity<CustomResponse<?>> createPost(
            @RequestBody PostCreateRequest request
            ){
        //TODO 사용자 id
        Long userId = 1L;
        return ResponseEntity.ok(CustomResponse.ok(postService.createPost(request, userId)));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CustomResponse<?>> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request
    ){
        //TODO 사용자 id
        Long userId = 1L;
        return ResponseEntity.ok(CustomResponse.ok(postService.updatePost(postId, request, userId)));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<CustomResponse<?>> deletePost(
            @PathVariable Long postId
    ){
        //TODO 사용자 id
        Long userId = 1L;
        postService.deletePost(postId, userId);
        return ResponseEntity.ok(CustomResponse.ok(null));
    }


}
