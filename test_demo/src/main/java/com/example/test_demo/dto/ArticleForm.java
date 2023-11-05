package com.example.test_demo.dto;

import com.example.test_demo.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter
@ToString
@NoArgsConstructor
public class ArticleForm {

    @JsonProperty
    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        return new Article(id, title, content);
    }



}
