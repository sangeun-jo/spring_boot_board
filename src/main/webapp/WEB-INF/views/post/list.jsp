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
    <link rel="stylesheet" type="text/css" href="css/general.css" >
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.scrollTo-min.js"></script>
    <script src="js/layout.js"></script>
    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div id="wrap">
     <!-- @ CONTAINER -->
    <section id="container" class="sub  new">
        <!-- @ CONTENTS -->
        <div id="contents">
            <!-- @ SUB TITLE AREA -->
            <div class="sub-title-area">
                <h2 class="tit">News & Info </h2>
            </div>
			<div class="btn_area">
				<a onclick="location.href='/insert'" class="btn_blue_line">글쓰기</a>
            </div>
			<!-- 페이징 처리는 5개씩 해주세요-->
            <table class="news_list">
            <caption>News 리스트</caption>
            <colgroup>
                <col style="width: 10%">
                <col style="width:auto">
                <col style="width: 10%">
                <col style="width: 10%">
                <col style="width: 5%">
                <col style="width: 8%">
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">등록일</th>
                    <th scope="col">조회</th>
                    <th scope="col">첨부</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="l" items="${list}">
                  <tr onclick="location.href='/detail?bno=${l.bno}&pageNum=${pageMaker.cri.pageNum}'"> 
                      <td>${l.bno}</td>
                      <td class="board_man">${l.subject}</td>
                      <td class="board_date">${l.writer}</td>
                      <td>
                        <fmt:formatDate value="${l.reg_date}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td class="board_read">${l.view}</td>
                    <td class="board_file">
                        <c:if test="${l.fno != 0}">
                            <span class="board_file">
                                <image src="/images/sub/icon_file.png" href="/fileDown/${l.bno}"></a>
                            </span>
                        </c:if>
                    </td>
                  </tr>
              </c:forEach>
            
            </table>

        
            <div class="pagination" id="paginate_button">
                <a class="prev end" href="${1}">첫 페이지</a> 
                <a href="${pageMaker.cri.pageNum - 1}" class="prev">이전 페이지</a>
                <c:forEach 
                    var="num"
                    begin="${pageMaker.startPage}"
                    end="${pageMaker.endPage}">
                    <a class="${pageMaker.cri.pageNum == num ? 'on':''}" href="${num}">${num}</a>
                </c:forEach>
                <a href="${pageMaker.cri.pageNum + 1}" class="next">다음 페이지</a>
                <a class="next end" href="${pageMaker.realEnd}">마지막 페이지</a>
            </div>     

            <div class="find_wrap">
                <form id="searchForm" action="/list" method="get">
                    <select name="type">
                        <option value="S">제목</option>
                        <option value="C">내용</option>
                    </select>
                    <input type="text" name="keyword" placeholder="검색어 입력">
                    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                    <a class="btn_gray">검색</a>
                </form>
            </div>
    
            <form id="actionForm" action="/list" method="get">
                <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" >
                <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                <input type="hidden" name="type" value="${pageMaker.cri.type}">
                <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
            </form>
    
        
        <!-- CONTENTS @ -->
    </section>
    <!-- CONTAINER @ -->
</div>
<script>
    $(document).ready(function(){
        var actionForm = $("#actionForm");
        $("#paginate_button a").on("click", function(e) {
            e.preventDefault();
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        })

        var searchForm = $('#searchForm');
        $('#searchForm a').on("click", function(e) {
            
            if(!searchForm.find("input[name='keyword']").val()) {
                alert("키워드를 입력하세요."); 
                return false;
            } 

            searchForm.find("input[name='pageNum']").val("1"); 
            e.preventDefault(); 

            searchForm.submit();
        })

    })
 </script>
</body>
</html>
