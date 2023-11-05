package com.example.test_demo.repository;

import com.example.test_demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


//CrudRepository<Article,Long>  Article자리는 저장할 데이터 객체 타입 Long 자리는 대표 key값
public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();
}
