package com.repository;

import com.Entity.sentLog.SMSLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Donnie on 2017/2/20.
 */
@Repository
public interface SMSRepository extends JpaRepository<SMSLog, Long> {

}
