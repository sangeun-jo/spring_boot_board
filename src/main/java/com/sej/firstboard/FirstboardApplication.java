package com.sej.firstboard;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(value={"com.sej.firstboard.board.mapper"})
public class FirstboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstboardApplication.class, args);
	}

	//스프링부트 실행 시 마다 db 객체 반환 
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean(); 

		sessionFactory.setDataSource(dataSource); 
		return sessionFactory.getObject(); 
	}

}
