package org.tp2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Para ejecutar los distintos ejercicios cambiar el path del basePackages
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.tp2.seccion2.ejercicio1"})
public class SpringWSApp {
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(SpringWSApp.class, args);

	}
}


