package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RealBoardController {

    private final RestTemplate restTemplate;

    @Autowired
    public RealBoardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/boards/list")
    public ModelAndView getBoardList() {
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<List<Board>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/api/v1/board/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Board>>() {}
        );
        List<Board> boards = responseEntity.getBody();
        modelAndView.addObject("boards", boards);
        modelAndView.setViewName("boardList"); // Thymeleaf 템플릿 파일의 이름 (boardList.html)
        return modelAndView;
    }
}
