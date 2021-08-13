package com.sej.firstboard.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sej.firstboard.model.BoardVO;
import com.sej.firstboard.model.FileVO;
import com.sej.firstboard.service.BoardService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller 
public class BoardController {
    @Resource(name="com.sej.firstboard.service.BoardService")
    BoardService mBoardService; 

    //게시판 리스트 화면 호출 
    @RequestMapping("/") 
    private String boardList(Model model) throws Exception{

        model.addAttribute("list", mBoardService.boardListService()); 
        return "list"; 
    }
    
    @RequestMapping("/detail/{bno}")
    private String boardDetail(@PathVariable int bno, Model model) throws Exception {
        model.addAttribute("detail", mBoardService.boardDetailService(bno)); 
        return "detail"; 
    }

    //게시글 작성폼 호출 
    @RequestMapping("/insert") 
    private String boardInsertForm() {
        return "insert"; 
    }

    @RequestMapping("/insertProc")
    private String boardInsertProc(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
        BoardVO board = new BoardVO();
        FileVO file = new FileVO();  

        board.setSubject(request.getParameter("subject"));
        board.setContent(request.getParameter("content")); 
        board.setWriter(request.getParameter("writer"));

        if (files.isEmpty()) { //업로드할 파일 없음
            mBoardService.boardInsertService(board);
        } else {
            String fileName = files.getOriginalFilename(); 
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile; 
            String destinationFileName;  
            String fileUrl = request.getServletContext().getRealPath("/uploadFiles/"); 

            do {
                destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + fileNameExtension; 
                destinationFile = new File(fileUrl + destinationFileName); 
            } while (destinationFile.exists()); 
    
            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);

            mBoardService.boardInsertService(board);

            file.setBno(board.getBno());
            file.setFileName(destinationFileName);
            file.setFileOriName(fileName);
            file.setFileUrl(fileUrl);
            
            mBoardService.fileInsertServcie(file); 

           
    
        }
        return "redirect:/"; 
    }

    //게시글 수정폼 
    @RequestMapping("/update/{bno}")
    private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception { 
        model.addAttribute("detail", mBoardService.boardDetailService(bno)); 
        return "update"; 
    }

    @RequestMapping("/updateProc")
    private String boardUpdateProc(HttpServletRequest request) throws Exception {
        BoardVO board = new BoardVO();

        int bno = Integer.parseInt(request.getParameter("bno")); 

        board.setBno(bno);
        board.setSubject(request.getParameter("subject"));
        board.setContent(request.getParameter("content"));

        mBoardService.boardUpdateService(board); 
        return "redirect:/detail/" + bno; 
    }

    //게시글 삭제 
    @RequestMapping("/delete/{bno}")
    private String boardDelete(@PathVariable int bno) throws Exception{
        mBoardService.boardDeleteService(bno); 
        return "redirect:/"; 
    }

}
