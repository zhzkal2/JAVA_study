package com.example.test_demo.controller;

import com.example.test_demo.dto.ArticleForm;
import com.example.test_demo.entity.Article;
import com.example.test_demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AticleController {

    @Autowired //스프링 부트가 미리 생성해놓은 객체를 연결
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        log.info(form.toString());    // println() 을 로깅으로 대체!
        Article article = form.toEntity();
//        log.info(article.toString()); // println() 을 로깅으로 대체!
        Article saved = articleRepository.save(article);
//        log.info(saved.toString());   // println() 을 로깅으로 대체!
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long  id, Model model){

        Article articleEntity =articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        log.info("id = " + id);

        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepository.findAll();

        model.addAttribute("articleList",articleEntityList);


        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article",articleEntity);

        return "articles/edit";
    }
    @PostMapping("/aticles/update")
    public String update(ArticleForm form){

        Article articleEntity = form.toEntity();

        Article taget =articleRepository.findById(articleEntity.getId()).orElse(null);
        if (taget !=null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();

    }


    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청");

        Article target = articleRepository.findById(id).orElse(null);

        if (target !=  null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }

        return "redirect:/articles";
    }
}

