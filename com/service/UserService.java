package com.service;

import com.model.User;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private List<User> users;

    public UserService(List<User> users) {
    	this.users = users;
    	}
    	public void registerNewAdmin() {
    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println("\nNew Admin User Registration");

    	    System.out.print("Enter first name: ");
    	    String firstName = scanner.nextLine();

    	    System.out.print("Enter last name: ");
    	    String lastName = scanner.nextLine();

    	    System.out.print("Enter mobile number: ");
    	    String mobileNumber = scanner.nextLine();

    	    System.out.print("Enter gender: ");
    	    String gender = scanner.nextLine();

    	    System.out.print("Enter email: ");
    	    String email = scanner.nextLine();

    	    System.out.print("Enter password: ");
    	    String password = scanner.nextLine();

    	   
    	    if (isUserExists(email)) {
    	        System.out.println("User with this email: " + email + " already exists");
    	        return;
    	    }

    	    User newUser = new User(firstName, lastName, mobileNumber, gender, email, password, 0, "Active");
    	    users.add(newUser);
    	    System.out.println("Registration successful!");
    	}

    	private boolean isUserExists(String email) {
    	    for (User user : users) {
    	        if (user.getEmail().equals(email)) {
    	            return true;
    	        }
    	    }
    	    return false;
    	}

    	
    	public User login() {
    	    Scanner scanner = new Scanner(System.in);
    	    System.out.println("\nUser Login");

    	    System.out.print("Enter email: ");
    	    String email = scanner.nextLine();

    	    System.out.print("Enter password: ");
    	    String password = scanner.nextLine();

    	    for (User user : users) {
    	        if (user.getEmail().equals(email)) {
    	            if (user.getFailedCount() >= 5) {
    	                System.out.println("User account is locked due to multiple invalid login attempts.");
    	                return null;
    	            }

    	            if (user.getPassword().equals(password)) {
    	                System.out.println("\nLogin Successful");
    	                user.setFailedCount(0); // reset count on successful login
    	                return user;
    	            } else {
    	                user.setFailedCount(user.getFailedCount() + 1); // increment on failure
    	                System.out.println("\nInvalid Credentials. Attempt: " + user.getFailedCount() + " for email: " + email);
    	                return null;
    	            }
    	        }
    	    }

    	    System.out.println("No user found with email: " + email);
    	    return null;
    	}

    	}

	
	
