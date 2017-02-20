package com.controller;

/**
 * Created by Donnie on 2017/2/17.
 */

import com.Entity.Token;
import com.Entity.msg.Text;
import com.Entity.msg.TextMsg;
import com.google.gson.Gson;
import com.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private UrlUtil urlUtil;

    @RequestMapping("/")
    public String main() {
        return "";
    }

    @RequestMapping("/print")
    public String sendWechat() {
        String tokenJson = urlUtil.urlGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxac0c7f608dd7b462&corpsecret=94vG_nM3VE1HM0iACx9_fxOLFuapDyGjZJKjyB5jrpviDQTr0LSUYY_28IaOppel");

        TextMsg msg = new TextMsg();

        msg.setTouser("@all");
        msg.setMsgtype("text");
        msg.setAgentid(0);
        msg.setText(new Text("good morning!"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(msg);
        Token token = gson.fromJson(tokenJson, Token.class);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token.getAccess_token();

        return urlUtil.urlPost(sendUrl, jsonContent);
    }


}
