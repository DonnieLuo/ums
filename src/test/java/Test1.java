import com.Entity.SMS.SMSResult;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.util.XmlUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Donnie on 2017/2/20.
 */
public class Test1 {

    @Test
    public void test() {
        JsonObject jsonObject = new JsonObject();
        String enc = DigestUtils.md5Hex("123456");
        System.out.print(enc);
    }
}
