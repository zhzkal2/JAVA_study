package com.example.test_demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //댓글 여러개가 하나의 Article과 연관됨
    @JoinColumn(name = "article_id") // articleid 컬럼에 aricle의 대표값을 저장
    private  Article article;

    @Column
    private  String nickname;

    @Column
    private  String body;
}
