
package com.onlineshopping.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshopping.social.SocialContext;

@RequestMapping("/facebook")
@Controller
public class FacebookPostsController {

	private static final Logger logger = Logger.getLogger(FacebookPostsController.class.getName());

	@Autowired
	private SocialContext socialContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String signin() {
		return "signin";
	}

	// @RequestMapping(value = "/show-posts", method = RequestMethod.GET)
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String showPostsForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(
				"in online shopping project inside showPostsForUser postsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

		String nextView;

		if (socialContext.isSignedIn(request, response)) {
			System.out.println("\n==============>logged in Cool!");

			// List<Post> posts = retrievePosts();
			// model.addAttribute("posts", posts);
			nextView = "show-posts";
		} else {
			System.out.println("\n==============> not logged in yet ");
			nextView = "signin";
		}
		Facebook facebook = socialContext.getFacebook();
		String[] fields = { "id", "email", "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		logger.info("\n==========> email facebook: " + userProfile.getEmail() + " firstName: "
				+ userProfile.getFirstName());
		// List<Post> posts = feedOps.getHomeFeed();
		// logger.info("Retrieved " + posts.size() + " posts from the Facebook
		// authenticated user");
		return nextView;
	}
}
