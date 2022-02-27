package com.sej.firstboard;

import javax.annotation.Resource;

import com.sej.firstboard.mapper.BoardMapper;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
public class JspTest {
    @Resource(name="com.sej.firstboard.mapper.BoardMapper")
    BoardMapper mBoardMapper; 

    @RequestMapping("/test")
    private String jspTest() throws Exception{
        System.out.println("boardCount: " + mBoardMapper.boardCount()); 
        return "test"; 
    }
}
