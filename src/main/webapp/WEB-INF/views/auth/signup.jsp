<!--회원가입 페이지-->
<!--signup.html-->
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
<form method="post" action="/signup">
    <div class="container">
        <h1>회원가입</h1>
        <div class="form-group">
            <label for="userid">userId</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="사용자 아이디">
        </div>
        <div class="form-group">
            <label for="username">userName</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="사용자 이름">
        </div>
        <div class="form-group">
            <label for="password">password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="사용자 비밀번호">
        </div>
        <div class="form-group">
            <input type="radio" name="auth" value="ROLE_ADMIN,ROLE_USER"> 
            <label class="form-check-label">Admin</label>
            <input type="radio" name="auth" value="ROLE_USER" checked="checked"> 
            <label class="form-check-label">User</label><br>
        </div>
        <button type="submit" class="btn btn-primary">가입 완료</button>
    </div>
    <input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'> 
</form>
</body>
</html>
        
