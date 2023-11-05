package com.example.test_demo.repository;

import com.example.test_demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * " + "FROM comment " + "WHERE article_id = :articleId", nativeQuery = true)

//    특정게시물 댓글 조회
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

//    특정 닉네임 모든 댓글 조회
    List<Comment> findByNickname(@Param("nickname") String nickname);



}

