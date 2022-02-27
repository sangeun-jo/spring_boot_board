# spring_boot_board
첫 스프링부트 게시판

#### 기술스택
- 언어: Java 
- 프레임워크: Spring boot 
- DB: MariaDB 
- 배포: heroku 

### 프로젝트 구조 
- maven build
- Controller.java - Service.java - Mapper.java - Mapper.xml 구조 
- Mybits를 사용함에 따라, DAO 클레스 대신 Mapper 클레스 사용

#### 새로 배운것 
1. 새로고침하지 않고 실시간으로 업데이트되는 덧글창(ajax 사용)
2. 파일업로드 
3. Spring Security
4. DTO, VO, Entity 차이점 

#### spring boot 게시판 만드는 중 만난 문제들

1. 헤로쿠 배포 안되는 문제    
헤로쿠 스프링부트는 Spring Boot initializr 사이트에서 다운받아서 하면 에러가 난다.   
배포할 때 spring 커멘드라인 툴 설치해서 하면 잘 배포 된다.    

2. 로컬에서는 잘 실행되는데 헤로쿠로 배포하면 db가 안되는 문제    
무료 버전을 써서 그렇다. 무료버전은 한번에 10회밖에 동시접속이 안된다.
돌리고 있는 서버를 몇개 닫거나, heroku restart 명령어로 다시 시작하자. 

3. 배포후 에러 로그를 볼 수 없는 문제    
application.properties 에 에러 레벨을 설정하기    
```server.error.include-exception=true```   
```server.error.include-stacktrace=always```   
물론 로그도 적극적으로 사용하자   

[참고 사이트](https://private.tistory.com/51?category=753861)


#### 개선점
- 로그인 기능 추가 
- 페이징 기능 추가 
- 게시글 목록에 댓글 개수 추가

