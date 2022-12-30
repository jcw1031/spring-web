package com.woopaca.myweb.controller;

import com.woopaca.myweb.entity.Board;
import com.woopaca.myweb.service.BoardService;
import com.woopaca.myweb.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardService.findAllToPage(pageable);
        Page<Board> boards = boardService.searchPost(searchText, searchText, pageable);
        int startPage = Math.max(boards.getPageable().getPageNumber() - 4, 1);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
            return "board/form";
        }
        Board board = boardService.findById(id).orElse(null);
        model.addAttribute("board", board);
        return "board/form";
    }

    @PostMapping("/form")
    public String boardSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardService.save(board);
        return "redirect:/board/list";
    }
}
