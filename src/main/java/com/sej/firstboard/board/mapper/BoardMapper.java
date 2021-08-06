package com.sej.firstboard.board.mapper;

import org.springframework.stereotype.Repository;


@Repository("com.sej.firstboard.board.mapper.BoardMapper") // 데이터 베이스에 접근하는 인터페이스
public interface BoardMapper {
    public int boardCount() throws Exception; 
}
