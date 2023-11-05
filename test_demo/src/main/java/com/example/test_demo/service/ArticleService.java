package com.example.test_demo.service;

import com.example.test_demo.dto.ArticleForm;
import com.example.test_demo.entity.Article;
import com.example.test_demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 개체를 스프링부트에 연결
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return  articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
            Article target = articleRepository.findById(id).orElse(null);

        if (target ==null){
        return null;
    }
        articleRepository.delete(target);
        return target;
    }


    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

        List<Article> articleList = dtos.stream().map(dto ->dto.toEntity()).collect(Collectors.toList());
//        for문으로 작성한다면
//        for (int i =0; i<dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }


        articleList.stream().forEach(article -> articleRepository.save(article));

//        for문으로 작성한다면
//        for(int i= 0; i<articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );

        return articleList;
    }
}
