<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<layoutTag:layout>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<div class="container">
    <div class="col-xs-12" style="margin:15px auto;">
        <label style="font-size:20px;"><span class="glyphicon glyphicon-edit"></span>게시글 수정</label>
    </div>
 
    <div class="col-xs-12">
        <form action="/updateProc" method="post" enctype="multipart/form-data">
          <div class="form-group">
            <label for="subject">제목</label>
            <input type="text" class="form-control" id="subject" name="subject" value="${detail.subject}">
          </div>
          <div class="form-group">
            <label for="writer">작성자</label><br>
            ${detail.writer}
          </div>

          <div class="form-group">
            <label for="writer">작성날짜</label><br>
            <fmt:formatDate value="${detail.reg_date}" pattern="yyyy/MM/dd"/>
          </div>

          <!-- 
          <div class="form-group">
            <label for="file">첨부파일</label><br>
            <a href="/fileDown/${files.bno}">${files.fileOriName}</a>
          </div>
        -->
        <div class="form-group">
          <label for="file">첨부파일</label>
          <input type="file" id="file" name="files">
        </div>

          <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="3">${detail.content}</textarea>
          </div>
          <input type="hidden" name="bno" value="${detail.bno}"/>
          <button type="submit" class="btn btn-primary btn-sm" style="float:right;">수정</button>
          <input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'> 
        </form>
    </div>
</div>
 
</body>
</html>
</layoutTag:layout>