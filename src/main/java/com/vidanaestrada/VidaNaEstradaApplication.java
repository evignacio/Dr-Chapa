package com.vidanaestrada;

import com.vidanaestrada.configuration.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Import(WebSecurityConfig.class)
@SpringBootApplication
public class VidaNaEstradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidaNaEstradaApplication.class, args);
	}

}
