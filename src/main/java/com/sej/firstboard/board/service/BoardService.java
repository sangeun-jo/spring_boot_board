package com.sej.firstboard.board.service;

import java.util.List;

import javax.annotation.Resource;

import com.sej.firstboard.board.domain.BoardVO;
import com.sej.firstboard.board.mapper.BoardMapper;

import org.springframework.stereotype.Service;

@Service("com.sej.firstboard.board.service.BoardService")
public class BoardService {
    @Resource(name="com.sej.firstboard.board.mapper.BoardMapper")
    BoardMapper mBoardMapper; 

    public List<BoardVO> boardListService() throws Exception {
        return mBoardMapper.boardList(); 
    }

    public BoardVO boardDetailService(int bno) throws Exception {
        return mBoardMapper.boardDetail(bno); 
    }

    public void boardInsertService(BoardVO board) throws Exception {
        mBoardMapper.boardInsert(board);
    }

    public void boardUpdateService(BoardVO board) throws Exception {
        mBoardMapper.boardUpdate(board);
    }

    public void boardDeleteService(int bno) throws Exception {
        mBoardMapper.boardDelete(bno); 
    }
}
