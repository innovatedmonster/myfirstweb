package com.myfirstweb.myfirstweb.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myfirstweb.myfirstweb.dto.AccessTokenDTO;
import com.myfirstweb.myfirstweb.dto.GithubUser;
import com.myfirstweb.myfirstweb.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("923057d72d43fa4c456d");
        accessTokenDTO.setClient_secret("624537b4368d4febade394ec9e60b359436b6c83");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8081/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken((accessTokenDTO));
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getId());
        System.out.println(user.getLogin());
        return "index";
    }
}
