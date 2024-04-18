package com.myfirstweb.myfirstweb.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myfirstweb.myfirstweb.dto.AccessTokenDTO;
import com.myfirstweb.myfirstweb.dto.GithubUser;
import com.myfirstweb.myfirstweb.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken((accessTokenDTO));
        GithubUser user = githubProvider.getUser(accessToken);

        //获取session和cookie
        if(user != null){
            //登录成功
            request.getSession().setAttribute("user", user);
        }
        else{
            //登录失败
        }
        return "redirect:/";


//        System.out.println(user.getId());
//        System.out.println(user.getLogin());
//        return "index";
    }
}
