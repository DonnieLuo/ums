package com.service;

import com.Entity.Token;
import com.Entity.msg.MsgResult;
import com.Entity.msg.TextMsg;
import com.Entity.sentLog.MsgLog;
import com.google.gson.Gson;
import com.repository.LogRepository;
import com.util.GsonUtil;
import com.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Donnie on 2017/2/20.
 */
@Slf4j
@Service
public class MsgService {
    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private LogRepository logRepository;

    /**
     *
     * @param msg
     * @return
     */
    @Transactional
    public MsgResult send(TextMsg msg, Token token) {
        Gson gson = GsonUtil.getInstance();
        String jsonContent = gson.toJson(msg);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token.getAccess_token();
        String msgResultJason = urlUtil.urlPost(sendUrl, jsonContent);
        MsgResult msgResult = gson.fromJson(msgResultJason, MsgResult.class);
        log.debug("send msg result:{}", msgResult.getErrmsg());

//        MsgLog msgLog = new MsgLog(msgResult);
//        msgLog.setSendDate(new Date());
        return msgResult;
    }
}
