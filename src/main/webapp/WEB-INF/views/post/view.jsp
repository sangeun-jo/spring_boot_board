<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" >
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="format-detection" content="telephone=no">
        <title>2016 굿모닝 경기 소통 크리에이터 공모전</title>
        <link rel="stylesheet" type="text/css" href="/css/general.css" >
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
        <script src="/js/layout.js"></script>
        <!--[if IE]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body>
    <div id="wrap">
        <!-- @ CONTAINER -->
        <section id="container" class="sub">
            <!-- @ CONTENTS -->
            <div id="contents">
                <!-- @ SUB TITLE AREA -->
                <div class="sub-title-area">
                    <h2 class="tit">News & Info 
                </div>
                <div class="write_title">
                    ${detail.subject}
                </div>
                <div class="write_date">
                    <span class="write_line"><strong>작성자  :</strong>${detail.writer}</span>
                    <span class="write_line"><strong>조회수  :</strong>${detail.view}</span>
                    <span class="write_line"><fmt:formatDate value="${detail.reg_date}" pattern="yyyy-MM-dd HH:MM:SS"/></span>
                    <span><em class="file_icon"></em><a href="/fileDown/${files.bno}">${files.fileOriName}</a></span>
    
                </div>
                <div class="con_box">
                    ${detail.content}
                </div>
    
                <div class="btn_area">
                    <a href="/list?pageNum=${pageNum}" class="btn_blue">목록</a>
                    <button type="button" class="btn_blue" onclick="location.href='/update?bno=${detail.bno}&pageNum=${pageNum}'">수정</button> 
                    <button type="button" class="btn_blue" onclick="location.href='/delete?bno=${detail.bno}&pageNum=${pageNum}'">삭제</button> 
                </div>
            </div>
            <!-- CONTENTS @ -->
        </section>
        <!-- CONTAINER @ -->
    </div>
    </body>
</html>