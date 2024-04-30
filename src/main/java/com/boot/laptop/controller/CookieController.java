package com.boot.laptop.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


@RestController
public class CookieController {

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "john_doe");
        cookie.setMaxAge(3600); // Cookie will expire in 1 hour
        cookie.setPath("/"); // Cookie is accessible from the root path
        response.addCookie(cookie);
        return "Cookie has been set";
    }

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    return "Username: " + cookie.getValue();
                }
            }
        }
        return "Cookie not found";
    }

    @GetMapping("/deleteCookie")
    public String deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // Delete the cookie immediately
        cookie.setPath("/");
        response.addCookie(cookie);
        return "Cookie has been deleted";
    }
}
