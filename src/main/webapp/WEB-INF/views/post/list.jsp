<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
 
<div class="container">
    <div class="col-xs-12" style="margin:15px auto;">
        <label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt" ></span>게시글 목록</label>
        <button class="btn btn-primary btn-sm" style="float:right;" onclick="location.href='/insert'">글쓰기</button>
    </div>
    
    <div class="col-xs-12">
        <table class="table table-hover">
            <tr>
                <th>No</th>
                <th>Subject</th>
                <th>Writer</th>
                <th>View</th>
                <th>Date</th>
                <th>첨부파일</th>
            </tr>
              <c:forEach var="l" items="${list}">
                  <tr onclick="location.href='/detail/${l.bno}'"> 
                      <td>${l.bno}</td>
                      <td>${l.subject}</td>
                      <td>${l.writer}</td>
                      <td>${l.view}</td>
                      <td>
                        <fmt:formatDate value="${l.reg_date}" pattern="yyyy/MM/dd"/>
                    </td>
                    <td>
                        ${l.fno}
                    </td>
                  </tr>
              </c:forEach>
        </table>

        <div class="row">
            <div class="col-lg-12">
                <form id="searchForm" action="/list" method="get">
                    <select name="type">
                        <option value="S">제목</option>
                        <option value="C">내용</option>
                    </select>
                    <input type="text" name="keyword">
                    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                    <button class="btn btn-default">Search</button>
                </form>
            </div>
        </div>

        <form id="actionForm" action="/list" method="get">
            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" >
            <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
            <input type="hidden" name="type" value="${pageMaker.cri.type}">
            <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
        </form>

        <div>
            <ul class="pagination">
                <c:if test="${pageMaker.prev}">
                    <li class="paginate_button previous">
                        <a href="${pageMaker.startPage - 1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach
                    var="num"
                    begin="${pageMaker.startPage}"
                    end="${pageMaker.endPage}">
                    <li class="paginate_button 
                        ${pageMaker.cri.pageNum == num ? 'active':''}">
                        <a href="${num}">${num}</a>
                    </li>
                </c:forEach>

                <c:if test="${pageMaker.next}">
                    <li class="paginate_button next">
                        <a href="${pageMaker.endPage +1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
 
 <script>
    $(document).ready(function(){
        var actionForm = $("#actionForm");
        $(".paginate_button a").on("click", function(e) {
            e.preventDefault();
            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        })

        var searchForm = $('#searchForm');
        $('#searchForm button').on("click", function(e) {
            
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
 
</layoutTag:layout>
