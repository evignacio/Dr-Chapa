package com.vidanaestrada;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vidanaestrada.configuration.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnableSwagger2
@Import(WebSecurityConfig.class)
@SpringBootApplication
public class VidaNaEstradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidaNaEstradaApplication.class, args);
	}

	@Bean
	public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	@Bean
	public FirebaseApp firebaseApp() throws IOException {

		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path filePath = Paths.get(root.toString(),"src", "main", "resources", "hack-c8271-firebase-adminsdk-x6hh4-3af6eec313.json");

		System.out.println(filePath.toString());
		FileInputStream serviceAccount =
				new FileInputStream(filePath.toString());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://hack-c8271.firebaseio.com")
				.build();

		return FirebaseApp.initializeApp(options);
	}

}
