package com.david.book.springboot.service;


import com.david.book.springboot.domain.posts.Posts;
import com.david.book.springboot.domain.posts.PostsRepository;
import com.david.book.springboot.web.dto.PostsListResponseDto;
import com.david.book.springboot.web.dto.PostsResponseDto;
import com.david.book.springboot.web.dto.PostsSaveRequestDto;
import com.david.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 글 저장
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 글 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    // 응답용 Dto를 반환하는 함수
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id= "+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        // ?
        return postsRepository.findAllDesc().stream()
                .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다 id="+id));

        postsRepository.delete(posts);
    }
}