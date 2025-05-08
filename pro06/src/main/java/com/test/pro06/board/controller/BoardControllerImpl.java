package com.test.pro06.board.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.test.pro06.board.dto.BoardDTO;
import com.test.pro06.board.service.BoardService;
import com.test.pro06.common.files.controller.FileController;
import com.test.pro06.common.files.dto.FileDTO;
import com.test.pro06.common.files.repository.FileRepository;

@Controller
@RequestMapping("/board")
public class BoardControllerImpl implements BoardController {

	@Autowired
	BoardService service;
	@Autowired
	FileRepository repository;
	
	@Override
	@GetMapping("/boardList")
	public String boardList(Model model) {
		// TODO Auto-generated method stub
		List<BoardDTO> boardList = service.boardList();
		model.addAttribute("boardList", boardList);
		return "board/boardList";
	}

	@Override
	@GetMapping("/insertBoard")
	public String insertBoard() {
		// TODO Auto-generated method stub
		return "board/insertBoard";
	}

	@Override
	@PostMapping("/insertBoard")
	public String insertBoard(BoardDTO dto, Model model, List<MultipartFile> files) throws Exception {
		// TODO Auto-generated method stub
		
		service.insertBoard(dto);
		for (MultipartFile file : files) {
			if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
				String saveDIR = FileController.FILE_REPO + "/" + dto.getArticleNo();
				
				File dir = new File(saveDIR);
				
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				Path savePath = Paths.get(saveDIR, originalFileName);
				file.transferTo(savePath.toFile());
				
				FileDTO fileDTO = new FileDTO();
				fileDTO.setArticleNo(dto.getArticleNo());
				fileDTO.setFileName(originalFileName);
				repository.save(fileDTO);
			}
		}
		model.addAttribute("message", "게시글이 등록되었습니다.");
		model.addAttribute("redirectUrl", "/board/boardList");
		return "common/alert";
	}

	@Override
	@GetMapping("/getBoard")
	public String getBoard(int articleNo, Model model) {
		// TODO Auto-generated method stub
		BoardDTO board = service.getBoard(articleNo);
		List<FileDTO> fileList = repository.findByArticleNo(articleNo);
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		return "board/getBoard";
	}

	@Override
	@PostMapping("/getBoard")
	public String getBoard(BoardDTO dto, Model model) {
		// TODO Auto-generated method stub
		service.updateBoard(dto);
		model.addAttribute("message", "게시글이 수정되었습니다.");
		model.addAttribute("redirectUrl", "/board/getBoard?articleNo="+dto.getArticleNo());
		return "common/alert";
	}

	@Override
	@GetMapping("/deleteBoard")
	public String deleteBoard(int articleNo, Model model) {
		// TODO Auto-generated method stub
int result = service.deleteBoard(articleNo);
		
		if(result >= 1) {
			model.addAttribute("message", "게시글이 삭제되었습니다.");
			model.addAttribute("redirectUrl", "/board/boardList");	
		} else {
			model.addAttribute("message", "게시글이 삭제되지 않았습니다. 다시 시도하세요.");
			model.addAttribute("redirectUrl", "/board/getBoard?articleNo="+articleNo);
		}
		return "common/alert";
	}
	
	
}
