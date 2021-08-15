package com.sej.firstboard.service;

import java.util.List;

import javax.annotation.Resource;

import com.sej.firstboard.mapper.CommentMapper;
import com.sej.firstboard.model.CommentVO;

import org.springframework.stereotype.Service;

@Service("com.sej.firstboard.service.CommentService")
public class CommentService {

    @Resource(name="com.sej.firstboard.mapper.CommentMapper")
    CommentMapper mCommentMapper; 
    
    public List<CommentVO> commentListService(int bno) throws Exception {
        return mCommentMapper.commentList(bno);
    } 

    public int commentInsertService(CommentVO comment) throws Exception {
        return mCommentMapper.commentInsert(comment); 
    }

    public int commentUpdateService(CommentVO comment) throws Exception {
        return mCommentMapper.commentUpdate(comment); 
    }

    public int commentDeleteService(int cno) throws Exception {
        return mCommentMapper.commentDelete(cno); 
    }

    public int commentsDeleteService(int bno) throws Exception {
        return mCommentMapper.commentsDelete(bno);
    }
}
