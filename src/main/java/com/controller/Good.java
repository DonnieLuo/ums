package com.controller;

import com.Entity.SMS.SMSResult;
import com.Entity.sentLog.SMSLog;
import com.repository.SMSRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Donnie on 2017/2/20.
 */
public class Good {
    @Autowired
    private SMSRepository smsRepository;

    @Test
    public void dof() {
        SMSResult smsResult = new SMSResult();
        smsResult.setRemainpoint(100);
        smsResult.setMessage("success");
        smsResult.setReturnstatus("ok");
        smsResult.setSuccessCounts(10);
        smsResult.setTaskID(9);

        SMSLog smsLog = new SMSLog();
        smsLog.setDate(new Date());
        smsLog.setSmsResult(smsResult);

        smsRepository.save(smsLog);

    }
}
