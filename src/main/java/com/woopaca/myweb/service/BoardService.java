package com.woopaca.myweb.service;

import com.woopaca.myweb.entity.Board;
import com.woopaca.myweb.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        boardRepository.delete(board);
    }

}
