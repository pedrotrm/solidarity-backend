package com.solidarity.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/tokens")
public class TokenResource {

    @DeleteMapping(value = "/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse res){

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // TODO: Em producao sera true
        cookie.setPath(req.getContextPath()+"/oauth/token");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        res.setStatus(HttpStatus.NO_CONTENT.value());

    }

}
