<!--로그인 페이지-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<form method="post" action="/loginProc">
    <div class="container">
        <h1>로그인</h1>
        <div class="form-group">
            <label for="inputEmail4">userId</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="사용자 아이디">
        </div>
        <div class="form-group">
            <label for="inputPassword4">password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="사용자 비밀번호">
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
    </div>
    <input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'> 
</form>
</body>
</html>