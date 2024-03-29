<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="" >
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <title>게시글 쓰기</title>
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
               <div class="sub-title-area">
                <h2 class="tit">News & Info </h2>
				</div>
            
                <form action="/insertProc" method="post" enctype="multipart/form-data">
                <table class="basic_write">
                    <caption>News 입력</caption>
                    <colgroup>
                        <col style="width:150px">
                        <col style="width:auto">
                    </colgroup>
					   <tr>
                        <th>제목<span class="star">*</span></th>
                        <td><input type="text" title="제목" name="subject" id="subject" class="add_txt"  placeholder="제목 *"></td>
                    </tr>
                    <tr>
                        <th>작성자<span class="star">*</span></th>
                        <td><input type="text" title="성명" name="writer" id="writer" class="name_txt" placeholder="성명 *"></td>
                    </tr>
                 
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            <input type="file" title="첨부파일" name="files" id="file" placeholder="학과/부서" class="name_txt">
                        </td>
                    </tr>
                    <tr>
                        <th>내용 <span class="star">*</span></th>
                        <td>
                           <textarea name="content" id="content" cols="30" rows="10" style="width:100%"></textarea>
                        </td>
                    </tr>
                    
                </table>   
                   
                <div class="btn_area">
					<button type="submit" class="btn_blue">확인</button> 
					<a href="/" class="btn_blue_line">취소</a><!-- 목록으로 가기 -->
                </div>
            </form>         
        </div>
        <!-- CONTENTS @ -->
    </section>
    <!-- CONTAINER @ -->
</div>
</body>
</html>