import com.Entity.SMS.SMSResult;
import com.Entity.sentLog.SMSLog;
import com.repository.SMSRepository;
import com.util.XmlUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Donnie on 2017/2/20.
 */
public class Test1 {
    @Autowired
    private SMSRepository smsRepository;
    @Test
    public void test() {
        SMSResult result = new SMSResult();
        result.setMessage("message");
        result.setRemainpoint(20);
        String xml = XmlUtil.toXml(result);
//        System.out.print(xml);
        SMSLog smsLog = new SMSLog();
//        smsLog.setSmsResult(result);
        smsLog.setDate(new Date());
        System.out.print(smsRepository);
        smsRepository.save(smsLog);
    }
}
