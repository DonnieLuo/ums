package com.controller;

/**
 * Created by Donnie on 2017/2/17.
 */

import com.Entity.Token;
import com.Entity.msg.Text;
import com.Entity.msg.TextMsg;
import com.google.gson.Gson;
import com.repository.LogRepository;
import com.util.GsonUtil;
import com.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    private Gson gson = GsonUtil.getInstance();

    @RequestMapping("/")
    public String login() {
        log.info(authenticationManager.toString());
        return "login";
    }
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam String username, @RequestParam String password) {
        if (true) {
            try {
                Authentication authentication = tryToAuthenticateWithUsernameAndPassword(username, password);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BadCredentialsException e) {
                log.info(e.toString());
            }
        }
        return "sendLog";
    }
    private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }
    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("auth failed");
        }
        return responseAuthentication;
    }
    @RequestMapping("/print")
    public String sendWechat() {
        String tokenJson = urlUtil.urlGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx3a42b774b7b91ccf&corpsecret=tpfikag8WOgdhafho3-cEgqJVQwTN3daf-u9182mUbVT4H-uHsTqYUye7uk6Acnr");

        TextMsg msg = new TextMsg();

        msg.setTouser("@all");
        msg.setMsgtype("text");
        msg.setAgentid(0);
        msg.setText(new Text("System testing message "));

        String jsonContent = gson.toJson(msg);
        Token token = gson.fromJson(tokenJson, Token.class);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token.getAccess_token();

        return urlUtil.urlPost(sendUrl, jsonContent);
    }
    @RequestMapping("/loop")
    public String loop() {
        //ty
        String tokenJson = urlUtil.urlGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx3a42b774b7b91ccf&corpsecret=tpfikag8WOgdhafho3-cEgqJVQwTN3daf-u9182mUbVT4H-uHsTqYUye7uk6Acnr");
        //ldl
//        String tokenJson = urlUtil.urlGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac0c7f608dd7b462&corpsecret=94vG_nM3VE1HM0iACx9_fxOLFuapDyGjZJKjyB5jrpviDQTr0LSUYY_28IaOppel");
        Token token = gson.fromJson(tokenJson, Token.class);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token.getAccess_token();
        TextMsg msg = new TextMsg();
        msg.setTouser("@all");
        msg.setMsgtype("text");
        msg.setAgentid(0);
        String result = "fail";
        for (int i=0; i<80; i++) {
            msg.setText(new Text("System testing message "+i));
            String jsonContent = gson.toJson(msg);
            result = urlUtil.urlPost(sendUrl, jsonContent);
        }
        return result;
    }


}
