package com.sej.firstboard.model;
 
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class BoardDTO {
    private int bno;
    private String subject;
    private String content;
    private String writer;
    private Date reg_date; 
    private int view;
}