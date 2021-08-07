package com.sej.firstboard.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.border.Border;

import com.sej.firstboard.board.domain.BoardVO;
import com.sej.firstboard.board.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class BoardController {
    @Resource(name="com.sej.firstboard.board.service.BoardService")
    BoardService mBoardService; 

    //게시판 리스트 화면 호출 
    @RequestMapping("/list") 
    private String boardList(Model model) throws Exception{
        List<BoardVO> boards =  mBoardService.boardListService(); 
        for(BoardVO board: boards) {
            board.setBno(board.getBno() / 10 + 1); 
        }
        model.addAttribute("list", boards); 
        return "list"; 
    }
    
    @RequestMapping("/detail/{bno}")
    private String boardDetail(@PathVariable int bno, Model model) throws Exception {
        BoardVO board = mBoardService.boardDetailService(bno * 10 - 5); 
        model.addAttribute("detail", board); 
        return "detail"; 
    }

    //게시글 작성폼 호출 
    @RequestMapping("/insert") 
    private String boardInsertForm() {
        return "insert"; 
    }

    @RequestMapping("/insertProc")
    private String boardInsertProc(HttpServletRequest request) throws Exception {
        BoardVO board = new BoardVO(); 

        board.setSubject(request.getParameter("subject"));
        board.setContent(request.getParameter("content")); 
        board.setWriter(request.getParameter("writer"));

        mBoardService.boardInsertService(board); 

        return "redirect:/list"; 
    }

    //게시글 수정폼 
    @RequestMapping("/update/{bno}")
    private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
        model.addAttribute("detail", mBoardService.boardDetailService(bno)); 
        return "update"; 
    }

    @RequestMapping("/updateProc")
    private void boardUpdateProc(HttpServletRequest request) throws Exception {
        BoardVO board = (BoardVO) request.getParameterMap(); 
        mBoardService.boardUpdateService(board); 
    }

    //게시글 삭제 
    @RequestMapping("/delete/{bno}")
    private String boardDelete(@PathVariable int bno) throws Exception{
        mBoardService.boardDeleteService(bno); 
        return "redirect:/list"; 
    }

}
