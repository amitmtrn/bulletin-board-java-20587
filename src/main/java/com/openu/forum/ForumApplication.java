package com.openu.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 									--FORUM APPLICATION--
 * 									=====================
 * 
 * This web application is functioned as a system that organizes forums in different subjects.
 * This system enables reading and writing topics in different subjects and commenting to them, 
 * as well as deletion/editing of a topic/comment. only by the one who wrote it or by an admin.
 * To avoid unlimited accumulation of messages, it also erases 1-month-old messages or older than that.
 * It allows users to write private messages to each other and delete them, 
 * and also allows only admins to add new subjects.
 *
 * THE SCREENS DESCRIPTION:
 * -----------------------
 * 
 * The application includes a main screen displaying the last 10 topics created. Buttons at the bottom of the screen
 * will allow the user to navigate to older or newer topics. Clicking on a topic results in a screen 
 * displays the different comments to this topic. In order to open a new topic, the user can click the button
 * that appears at the top of the screen of the topics view. Clicking on it displays a new screen
 * where the user can write the contents of the topic and then add it to the forum or cancel it, 
 * by clicking the corresponding buttons at the bottom of the screen.
 * 
 * Each topic will have 3 options (buttons in the same row):
 * (1) Adding a comment - clicking this button navigates the user to a new screen where he can write the content of the comment.
 * (2) Deleting the topic - an option granted only to the user who opened this topic and an admin.
 * (3) Updating the contents of the topic - an option granted only to the user who opened this topic and an admin.
 * 
 * Each comment will have 2 options (buttons in the same row):
 * (1) Deleting the comment - an option granted only to the user who wrote this comment and an admin.
 * (2) Editing the comment - an option granted only to the user who wrote this comment and an admin.
 *    
 * @author amit and nir
 *
 */
@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
}
