package org.wecancodeit.hometask.Controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wecancodeit.hometask.Models.User;
import org.wecancodeit.hometask.Repositories.UserRepository;
import org.wecancodeit.hometask.Services.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

@Resource
private UserService userService;

public HomePageController(UserService userService) {
    this.userService = userService;
}




}