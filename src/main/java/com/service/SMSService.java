package com.service;

import com.Entity.SMS.SMS;
import com.Entity.SMS.SMSResult;
import com.google.gson.Gson;
import com.util.UrlUtil;
import com.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Donnie on 2017/2/20.
 */
@Service
public class SMSService {
    private static Logger logger = LoggerFactory.getLogger(SMSService.class);
    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private XmlUtil xmlUtil;

    private String SMSUrl = "http://120.24.77.129:8888/sms.aspx";

    /**
     *
     * @param sms
     * @return
     */
    public SMSResult send(SMS sms) {
        Gson gson = new Gson();
        String jsonContent = gson.toJson(sms);
        String xmlResult = urlUtil.urlPost(SMSUrl, jsonContent);
        SMSResult smsResult = xmlUtil.fromXml(xmlResult, SMSResult.class);
        logger.debug("send sms to {}, result: {}",sms.getMobile(), smsResult.getReturnstatus());
        return smsResult;
    }
}
