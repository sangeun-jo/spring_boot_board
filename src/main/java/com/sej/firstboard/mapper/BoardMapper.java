package com.sej.firstboard.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.sej.firstboard.model.BoardDTO;
import com.sej.firstboard.model.Criteria;
import com.sej.firstboard.model.FileDTO; 


@Repository("com.sej.firstboard.mapper.BoardMapper") 
public interface BoardMapper {
    //게시글 개수 
    public int boardCount() throws Exception; 

    //게시글 목록
    public List<BoardDTO> boardList() throws Exception;

    //게시글 상세 
    public BoardDTO boardDetail(int bno) throws Exception; 

    //게시글 작성
    public int boardInsert(BoardDTO board) throws Exception; 

    //파일 추가
    public int fileInsert(FileDTO file) throws Exception; 

    //파일 상세
    public FileDTO fileDetail(int bno) throws Exception;

    //게시글 수정
    public int boardUpdate(BoardDTO board) throws Exception; 

    //게시글 삭제
    public int boardDelete(int bno) throws Exception; 

    //파일 삭제
    public int fileDelete(int bno) throws Exception; 

    //조회수 업데이트
    public int updateView(int bno) throws Exception;

    //페이징
    public List<BoardDTO> getListWithPaging(Criteria cri); 

    public int getTotalCount(Criteria cri);
}
