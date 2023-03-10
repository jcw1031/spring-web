package com.woopaca.myweb.service;

import com.woopaca.myweb.entity.Board;
import com.woopaca.myweb.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Page<Board> findAllToPage(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    public List<Board> findByTitleOrContent(String title, String content) {
        return boardRepository.findByTitleOrContent(title, content);
    }

    public Page<Board> searchPost(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContainingOrderByIdDesc(title, content, pageable);
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        boardRepository.delete(board);
    }

}
