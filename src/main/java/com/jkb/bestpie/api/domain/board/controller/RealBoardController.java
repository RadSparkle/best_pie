package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.repository.BoardRepository;
import com.jkb.bestpie.api.domain.comment.entity.Comment;
import com.jkb.bestpie.api.domain.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class RealBoardController {

    private final RestTemplate restTemplate;

    @Autowired
    public RealBoardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

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

        // 게시글 정보 가져오기
        ResponseEntity<Board> boardResponse = restTemplate.getForEntity(
                "http://localhost:8080/api/v1/board/" + id,
                Board.class
        );
        Board board = boardResponse.getBody();

        // 댓글 리스트 가져오기
        ResponseEntity<List<Comment>> commentsResponse = restTemplate.exchange(
                "http://localhost:8080/api/v1/board/commnets/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {}
        );
        List<Comment> comments = commentsResponse.getBody();

        modelAndView.addObject("board", board);
        modelAndView.addObject("comments", comments);
        modelAndView.setViewName("board/boardDetail"); // Thymeleaf 템플릿 파일의 이름 (boardDetail.html)
        return modelAndView;
    }


    @PostMapping("/comments/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer commentId) {
        // 댓글 삭제
        Optional<Comment> deletedCommentOptional = commentRepository.findById(commentId);
        if (!deletedCommentOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // 삭제된 댓글을 찾을 수 없는 경우 404 응답 반환
        }

        Integer boardId = deletedCommentOptional.get().getBoardId();

        // 실제로 댓글 삭제
        commentRepository.deleteById(commentId);

        // 해당 게시글로 리다이렉션
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/boards/detail/" + boardId) // 해당 게시글의 상세 페이지로 리다이렉션
                .build();
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
