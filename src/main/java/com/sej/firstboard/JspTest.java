package com.sej.firstboard;

import javax.annotation.Resource; 

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 

import com.sej.firstboard.board.mapper.BoardMapper; 

@Controller
public class JspTest {

    @Resource(name="com.sej.firstboard.board.mapper.BoardMapper")
    BoardMapper mBoardMapper; 

    @RequestMapping("/test")
    private String jspTest() throws Exception{
        System.out.println("boardCount: " + mBoardMapper.boardCount()); 
        return "test"; 
    }
}