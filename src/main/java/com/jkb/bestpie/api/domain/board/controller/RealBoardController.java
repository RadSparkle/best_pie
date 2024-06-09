package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.repository.BoardRepository;
import com.jkb.bestpie.api.domain.comment.entity.Comment;
import com.jkb.bestpie.api.domain.comment.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
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

    //게시판 리스트 요청받는 메소드
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


    //게시글 상세페이지 및 댓글 리스트 불러오는메소드
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
                "http://localhost:8080/api/v1/board/comments/" + id,
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

    //댓글 등록하는메소드
    @PostMapping("/boards/addComment/{boardId}")
    public String addCommentToBoard(@PathVariable("boardId") Integer boardId, Comment comment) {
        restTemplate.postForEntity(
                "http://localhost:8080/api/v1/board/comments/" + boardId, comment, String.class);
        return "redirect:/boards/detail/" + boardId;
    }

    @PostMapping("/boards/editComment/{commentId}")
    public String editComment(@PathVariable("commentId") Integer commentId, Comment comment) {
        restTemplate.put("http://localhost:8080/api/v1/board/comments/" + commentId, comment);
        // 수정 후 해당 게시글 상세 페이지로 리다이렉션
        return "redirect:/boards/detail/" + findBoardIdByCommentId(commentId);
    }

    @PostMapping("/boards/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId, HttpSession session) {
        // 삭제되기 전에 해당 댓글이 속한 게시글의 ID를 미리 가져옴
        Integer boardId = findBoardIdByCommentId(commentId);

        restTemplate.delete("http://localhost:8080/api/v1/board/comments/" + commentId);
        // 삭제 후 해당 게시글 상세 페이지로 리다이렉션
        return "redirect:/boards/detail/" + boardId;
    }


    public Integer findBoardIdByCommentId(Integer commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            return comment.getBoardId();
        } else {
            // 댓글이 존재하지 않을 경우 예외 처리 또는 기본값 설정 등의 작업 수행
            throw new IllegalArgumentException("댓글이 존재하지 않습니다. commentId: " + commentId);
        }
    }


    //게시판 등록페이지 요청받는 메소드
    @GetMapping("/boards/new")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("board", new Board());
        modelAndView.setViewName("board/boardPost"); // Thymeleaf 템플릿 파일의 이름 (board/boardCreate.html)
        return modelAndView;
    }


    //게시판 등록요청하는메소드
    @PostMapping("/boards/post")
    public String createBoard(Board board) {
        restTemplate.postForEntity(
                "http://localhost:8080/api/v1/board/post", board, String.class);
        return "redirect:/boards/list";
    }



    //게시판 삭제 요청하는 메소드
    @PostMapping("/boards/delete/{id}")
    public String deleteBoard(@PathVariable("id") Integer id) {
        // 해당 게시글에 연결된 댓글 조회
        List<Comment> comments = commentRepository.findByBoardId(id);

        // 댓글이 존재하는 경우 댓글 삭제
        if (!comments.isEmpty()) {
            commentRepository.deleteAll(comments);
        }

        // 게시글 삭제
        restTemplate.delete("http://localhost:8080/api/v1/board/delete/" + id);
        return "redirect:/boards/list";
    }

    //게시판 수정 요청하는 메소드
    @PostMapping("/boards/update/{id}")
    public String updateBoard(@PathVariable("id") Integer id, Board board) {
        restTemplate.put("http://localhost:8080/api/v1/board/update/" + id, board);
        return "redirect:/boards/list";
    }

    //게시판 수정페이지 요청받는 메소드
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
