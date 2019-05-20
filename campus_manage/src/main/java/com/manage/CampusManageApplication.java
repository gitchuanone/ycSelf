package com.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @ComponentScan是组件扫描注解，用来扫描@Controller  @Service  @Repository这类,主要就是定义扫描的路径从中找出标志了需要装配的类到Spring容器中
	其次，@MapperScan 是扫描mapper类的注解,就不用在每个mapper类上加@MapperScan了
 * @author Administrator
 */
@SpringBootApplication
//@ComponentScan
@MapperScan({"com.manage.dao"})
public class CampusManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusManageApplication.class, args);
	}

}
