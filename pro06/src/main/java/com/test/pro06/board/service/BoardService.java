package com.test.pro06.board.service;

import java.util.List;

import com.test.pro06.board.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> boardList();

	void insertBoard(BoardDTO dto);

	BoardDTO getBoard(int articleNo);

	void updateBoard(BoardDTO dto);

	int deleteBoard(int articleNo);

}
