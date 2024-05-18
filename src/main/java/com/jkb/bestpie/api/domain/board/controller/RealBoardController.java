package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        modelAndView.setViewName("board/boardList"); // Thymeleaf 템플릿 파일의 이름 (boardList.html)
        return modelAndView;
    }

    @GetMapping("boards/detail/{id}")
    public ModelAndView getBoardDetail(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<Board> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/v1/board/" + id,
                Board.class
        );
        Board board = responseEntity.getBody();
        modelAndView.addObject("board", board);
        modelAndView.setViewName("board/boardDetail"); // Thymeleaf 템플릿 파일의 이름 (boardDetail.html)
        return modelAndView;
    }

    @GetMapping("/boards/new")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("board", new Board());
        modelAndView.setViewName("board/boardPost"); // Thymeleaf 템플릿 파일의 이름 (board/boardCreate.html)
        return modelAndView;
    }



    @PostMapping("/boards/post")
    public String createBoard(Board board) {
        restTemplate.postForEntity(
                "http://localhost:8080/api/v1/board/post", board, String.class);
        return "redirect:/boards/list";
    }

    @PostMapping("/boards/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        restTemplate.delete("http://localhost:8080/api/v1/board/delete/" + id);
        return "redirect:/boards/list";
    }

    @PostMapping("/boards/update/{id}")
    public String updateBoard(@PathVariable("id") Integer id, Board board) {
        restTemplate.put("http://localhost:8080/api/v1/board/update/" + id, board);
        return "redirect:/boards/list";
    }

    @GetMapping("/boards/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        ResponseEntity<Board> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/v1/board/" + id,
                Board.class
        );
        Board board = responseEntity.getBody();
        modelAndView.addObject("board", board);
        modelAndView.setViewName("board/boardEdit"); // Thymeleaf 템플릿 파일의 이름 (board/boardEdit.html)
        return modelAndView;
    }












}
