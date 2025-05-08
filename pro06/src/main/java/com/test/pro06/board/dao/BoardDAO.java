package com.test.pro06.board.dao;

import java.util.List;

import com.test.pro06.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> boardList();

	void insertBoard(BoardDTO dto);
	
	BoardDTO getBoard(int articleNo);

	void update(BoardDTO dto);
	
	void delete(BoardDTO dto);

	int deleteBoard(int articleNo);

}
