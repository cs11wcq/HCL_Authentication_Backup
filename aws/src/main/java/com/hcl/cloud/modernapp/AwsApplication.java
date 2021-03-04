package com.hcl.cloud.modernapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * make sure to run "npm install" in the HCL-user-front-end folder
 * then "ng serve" to run the front end
 *
 * if you try to sign in with bogus pw 3 times, lock them out
 *
 * error when doing duplicate registration
 *
 * once the login is successful, we need to navigate to home page
 *
 * application.properties file need to not drop table everytime
 *
 * Next – let's look at the validations that the controller will perform when registering a new account:
 *
 * All required fields are filled (No empty or null fields)
 * The email address is valid (well-formed)
 * The password confirmation field matches the password field
 * The account doesn't already exist
 */
@SpringBootApplication
public class AwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsApplication.class, args);
	}
}
