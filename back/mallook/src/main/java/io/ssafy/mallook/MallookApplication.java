package io.ssafy.mallook;

import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.TimeZone;

@Log4j2
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = ProductsRepository.class
))
@EnableMongoRepositories(basePackages = "io.ssafy.mallook.domain.product.dao.mongo")
public class MallookApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallookApplication.class, args);
	}

	@PostConstruct
	public void setTimezone(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		log.info("서버 시작 시간: " + new Date());
	}
}
