package com.jkb.bestpie.api.domain.comment.cotroller;

import com.jkb.bestpie.api.domain.comment.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class RealCommentCotroller {

    private final RestTemplate restTemplate;

    @Autowired
    public RealCommentCotroller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/board/{id}/comments")
    public String getCommentsByBoardId(@PathVariable("id") Integer boardId, Model model) {
        ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/v1/board/comments/" + boardId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {}
        );
        List<Comment> comments = responseEntity.getBody();
        model.addAttribute("comments", comments);
        return "commentList"; // 댓글 리스트를 보여줄 Thymeleaf 템플릿의 이름
    }
}
