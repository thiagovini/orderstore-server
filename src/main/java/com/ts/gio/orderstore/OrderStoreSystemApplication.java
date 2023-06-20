package com.ts.gio.orderstore;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class OrderStoreSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderStoreSystemApplication.class, args);
	}

}
