package sign;

import constant.constant;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.HashMap;

class HmacSHA256Base64UtilsTest {

    @Test
    void sign() throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {
        String ts = DateLocalUtil.toUTC();
        ts = "2021-04-19T06:21:13.089Z";
        //http://172.16.100.175:9006/Server/api/v2/Fund/getAccount?convertUnit=usdt&futuresAccountType=1
        HashMap para = new HashMap();
        para.put("convertUnit", "usdt");
        para.put("futuresAccountType", constant.BASE_USDT);
        String digest = EncryDigestUtil.digest("3b5b1e07-383e-42dc-ae85-90449241c0dc");
        String sign = HmacSHA256Base64Utils.sign(ts, "GET", "/Server/api/v2/Fund/getAccount", para, "a55caded-eef9-426b-af7c-faf53c78e2ae", digest);
        System.out.println(ts);
        System.out.println(sign);
    }

    @Test
    void sign2() throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {
        String ts = DateLocalUtil.toUTC();
        ts = "2021-01-05T14:05:28.616Z";
        HashMap para = new HashMap();
        para.put("orderId", "1234567890");
        para.put("symbol", "BTC_USDT");
        String digest = EncryDigestUtil.digest("ceb892e0-0367-4cc1-88d1-ef9289feb053");
        String sign = HmacSHA256Base64Utils.sign(ts, "GET", "/Server/api/v1/trade/getOrder", para, "wewer", digest);
        System.out.println(ts);
        System.out.println(sign);
    }

}