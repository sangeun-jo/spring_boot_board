package com.sej.firstboard.mapper;

import org.springframework.stereotype.Repository;

import java.util.List; 
import com.sej.firstboard.domain.BoardVO; 


@Repository("com.sej.firstboard.mapper.BoardMapper") // 데이터 베이스에 접근하는 인터페이스
public interface BoardMapper {
    //게시글 개수 
    public int boardCount() throws Exception; 

    //게시글 목록
    public List<BoardVO> boardList() throws Exception;

    //게시글 상세 
    public BoardVO boardDetail(int bno) throws Exception; 

    //게시글 작성
    public void boardInsert(BoardVO board) throws Exception; 

    //게시글 수정
    public void boardUpdate(BoardVO board) throws Exception; 

    //게시글 삭제
    public void boardDelete(int bno) throws Exception; 

}
