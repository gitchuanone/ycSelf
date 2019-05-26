package com.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication  //开启springboot启动项
@MapperScan("com.campus.dao")  //开启包扫描     **mapper.xml配置
//@EnableTransactionManagement    //开启事务(执行数据库的隔离)
//@ComponentScan(basePackages={"com.campus"}) //自动扫描注册备案;如果没有配置的话，Spring Boot会扫描启动类所在包下以及子包下的使用了@Service,@Repository等注解的类。
public class CampusApplication {
	public static void main(String[] args) {
		SpringApplication.run(CampusApplication.class, args);
	}

}
