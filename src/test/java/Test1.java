import com.Entity.SMS.SMSResult;
import com.util.XmlUtil;
import org.junit.Test;

/**
 * Created by Donnie on 2017/2/20.
 */
public class Test1 {
    @Test
    public void test() {
        SMSResult result = new SMSResult();
        result.setMessage("message");
        result.setRemainpoint(20);
        String xml = XmlUtil.toXml(result);
        System.out.print(xml);
    }
}
