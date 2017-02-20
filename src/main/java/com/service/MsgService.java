package com.service;

import com.Entity.Token;
import com.Entity.msg.MsgResult;
import com.Entity.msg.TextMsg;
import com.google.gson.Gson;
import com.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Donnie on 2017/2/20.
 */
@Service
public class MsgService {
    private static Logger logger = LoggerFactory.getLogger(MsgService.class);
    @Autowired
    private UrlUtil urlUtil;

    /**
     *
     * @param msg
     * @return
     */
    public MsgResult send(TextMsg msg, Token token) {
        Gson gson = new Gson();
        String jsonContent = gson.toJson(msg);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token.getAccess_token();
        String msgResultJason = urlUtil.urlPost(sendUrl, jsonContent);
        MsgResult msgResult = gson.fromJson(msgResultJason, MsgResult.class);
        logger.debug("send msg result:{}", msgResult.getErrmsg());
        return msgResult;
    }
}
