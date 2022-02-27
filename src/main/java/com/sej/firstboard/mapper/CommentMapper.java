package com.sej.firstboard.mapper;

import java.util.List;

import com.sej.firstboard.model.CommentDTO;

import org.springframework.stereotype.Repository;

@Repository("com.sej.firstboard.mapper.CommentMapper")
public interface CommentMapper {
    //댓글 개수
    public int commentCount() throws Exception;

    //댓글 목록
    public List<CommentDTO> commentList(int bno) throws Exception; 

    //댓글 작성
    public int commentInsert(CommentDTO comment) throws Exception; 

    //댓글 수정
    public int commentUpdate(CommentDTO comment) throws Exception;

    //개별 댓글 삭제
    public int commentDelete(int cno) throws Exception; 

    //게시글 전체 댓글 삭제
    public int commentsDelete(int bno) throws Exception; 
}
