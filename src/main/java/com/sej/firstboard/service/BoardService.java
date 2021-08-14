package com.sej.firstboard.service;

import java.util.List;

import javax.annotation.Resource;

import com.sej.firstboard.mapper.BoardMapper;
import com.sej.firstboard.model.BoardVO;
import com.sej.firstboard.model.FileVO;

import org.springframework.stereotype.Service;

@Service("com.sej.firstboard.service.BoardService")
public class BoardService {
    @Resource(name="com.sej.firstboard.mapper.BoardMapper")
    BoardMapper mBoardMapper; 

    public List<BoardVO> boardListService() throws Exception {
        return mBoardMapper.boardList(); 
    }

    public BoardVO boardDetailService(int bno) throws Exception {
        return mBoardMapper.boardDetail(bno); 
    }

    public FileVO fileDetailService(int bno) throws Exception{
        return mBoardMapper.fileDetail(bno);
    }
    
    public void boardInsertService(BoardVO board) throws Exception {
        mBoardMapper.boardInsert(board);
    }

    public int fileInsertServcie(FileVO file) throws Exception {
        return mBoardMapper.fileInsert(file); 
    }

    public void boardUpdateService(BoardVO board) throws Exception {
        mBoardMapper.boardUpdate(board);
    }

    public void boardDeleteService(int bno) throws Exception {
        mBoardMapper.boardDelete(bno); 
    }

    public void fileDeleteService(int bno) throws Exception {
        mBoardMapper.fileDelete(bno); 
    }
}
