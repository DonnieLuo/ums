package com.service;

import com.Entity.SMS.SMS;
import com.Entity.SMS.SMSResult;
import com.Entity.sentLog.SMSLog;
import com.google.gson.Gson;
import com.repository.LogRepository;
import com.util.GsonUtil;
import com.util.UrlUtil;
import com.util.XmlUtil;
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

@Service
@Slf4j
public class SMSService {
    private static Logger logger = LoggerFactory.getLogger(SMSService.class);
    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    private LogRepository logRepository;

    private String SMSUrl = "http://120.24.77.129:8888/sms.aspx";

    /**
     *
     * @param sms
     * @return
     */
    @Transactional
    public SMSResult send(SMS sms) {
        Gson gson = GsonUtil.getInstance();
        String jsonContent = gson.toJson(sms);
        String xmlResult = urlUtil.urlPost(SMSUrl, jsonContent);
        SMSResult smsResult = xmlUtil.fromXml(xmlResult, SMSResult.class);
        log.debug("send sms to {}, result: {}",sms.getMobile(), smsResult.getReturnstatus());

//        SMSLog smsLog = new SMSLog(smsResult);
//        smsLog.setSendDate(new Date());
//        smsRepository.save(smsLog);

        return smsResult;
    }
}
