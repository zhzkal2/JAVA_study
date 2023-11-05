package com.example.test_demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    //정보 수정
    public void patch(Article article){
        if(article.title !=null){
            this.title = article.title;
        }
        if (article.content !=null){
            this.content = article.content;
        }
    }

}
