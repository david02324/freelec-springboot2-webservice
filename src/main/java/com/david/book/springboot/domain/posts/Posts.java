package com.david.book.springboot.domain.posts;

import com.david.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    // GenType.IDENTITY를 적용해여 auto_increment가 적용됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column 을 써야 length나 nullable 옵션 변경 가능
    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }
}
