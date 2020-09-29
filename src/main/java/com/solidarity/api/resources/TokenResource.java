package com.solidarity.api.resources;

import com.solidarity.api.config.SolidarityProperty;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SolidarityProperty solidarityProperty;

    @DeleteMapping(value = "/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse res){

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(solidarityProperty.getSeguranca().isEnableHttps()); // TODO: Em producao sera true
        cookie.setPath(req.getContextPath()+"/oauth/token");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        res.setStatus(HttpStatus.NO_CONTENT.value());

    }

}
