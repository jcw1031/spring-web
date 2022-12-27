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

    public void save(Board board) {
        boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

}
