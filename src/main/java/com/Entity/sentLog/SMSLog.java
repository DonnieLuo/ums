package com.Entity.sentLog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Donnie on 2017/2/20.
 */
@Entity
public class SMSLog {
    @Id
    private Long id;
//    private SMSResult smsResult;
    private Date date;

    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public SMSResult getSmsResult() {
//        return smsResult;
//    }
//
//    public void setSmsResult(SMSResult smsResult) {
//        this.smsResult = smsResult;
//    }


}
