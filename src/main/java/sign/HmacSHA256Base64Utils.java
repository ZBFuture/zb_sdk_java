package sign;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

public class HmacSHA256Base64Utils {

    private static final int MAX_FAST_TIME = 3000;                  // 最大允许比服务器快的毫秒数
    private static final int MAX_SLOW_TIME = 1 * 60 * 1000;        // 最大允许比服务器慢的毫秒数

    private static final String ALGORITHM = "HmacSHA256";
    private static final String CHARSET = "UTF-8";

    public static Mac SHA256_HMAC;

    static {
        try {
            SHA256_HMAC = Mac.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeErrorException(new Error("Can't get Mac's instance."));
        }
    }

    /**
     * 验证签名
     *
     * @param timestamp   时间戳或UTC时间字符串
     * @param method      请求方法：GET/POST
     * @param requestPath 请求接口路径
     * @param params      请求参数
     * @param apiKey      api key
     * @param secretKey   api密钥
     * @return
     */
    public static boolean verify(Object timestamp, String method, String requestPath, Map<String, Object> params, String sign, String apiKey, String secretKey)
            throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {

        if (timestamp == null || method == null) {
            return false;
        }

        long t = 0;
        if (timestamp instanceof Long) {
            t = (long) timestamp;
        } else {
            t = DateLocalUtil.toLocalDate((String) timestamp).getTime();
        }

        long serverTime = System.currentTimeMillis();
        if (t >= (serverTime + MAX_FAST_TIME) || (serverTime - t) > MAX_SLOW_TIME) {
            System.out.println("time invalid");
            return false;
        }

        return sign(timestamp, method, requestPath, params, apiKey, secretKey).equalsIgnoreCase(sign);
    }

    /**
     * 生成签名
     * 对timestamp + method + requestPath + request query/request body字符串 (+表示字符串连接)，以及SecretKey，使用HMAC SHA256方法加密，通过Base64编码输出而得到的
     *
     * @param timestamp   时间戳
     * @param method      请求/zb app的token
     * @param requestPath 请求接口路径
     * @param params      请求参数
     * @param apiKey      api key
     * @param secretKey   api密钥
     * @return
     */
    public static String sign(Object timestamp, String method, String requestPath, Map<String, Object> params, String apiKey, String secretKey)
            throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {
        if (apiKey == null || secretKey == null) {
            throw new RuntimeException("apiKey/secretKey must not be null !");
        }

        String signStr = buildSortParam(params);
        String content = timestamp + method + requestPath + signStr;
        String sign = generateSign(secretKey, content);
        
        System.out.println("sign content: " + content);
        System.out.println("sign result: " + sign);
        return sign;
    }

    /**
     * 按照ASCII码排序
     *
     * @param params
     * @return
     */
    private static String buildSortParam(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }

        StringBuilder toSign = params.entrySet().stream()
                .filter(en -> en.getValue() != null && !"".equals(en.getValue()))
                .sorted(Map.Entry.comparingByKey())
                // url
                .reduce(new StringBuilder(),
                        (acc, it) -> acc.append("&").append(it.getKey()).append("=").append(it.getValue()),
                        (l, r) -> null)
                .deleteCharAt(0);
        return toSign.toString();
    }

    private static String generateSign(String secretKey, String content)
            throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {
        byte[] secretKeyBytes = secretKey.getBytes(CHARSET);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, ALGORITHM);
        Mac mac = (Mac) SHA256_HMAC.clone();
        mac.init(secretKeySpec);
        return Base64.getEncoder().encodeToString(mac.doFinal(content.getBytes(CHARSET)));
    }

}
