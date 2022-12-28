package com.woopaca.myweb.controller;

import com.woopaca.myweb.entity.Board;
import com.woopaca.myweb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                           @RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardService.findAll();
        }
        return boardService.findByTitleOrContent(title, content);
    }

    @PostMapping("/boards")
    public Board newBoard(@RequestBody Board board) {
        return boardService.save(board);
    }

    @GetMapping("/boards/{id}")
    public Board one(@PathVariable("id") Long id) {
        return boardService.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    public Board replaceBoard(@RequestBody Board newBoard, @PathVariable("id") Long id) {
        return boardService.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return boardService.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardService.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
}
