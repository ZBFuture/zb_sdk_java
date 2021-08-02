package sign;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryDigestUtil {

    private static String encodingCharset = "UTF-8";

    private static final String MAC_NAME = "HmacSHA1";

    public static String hmacSign(String aValue, String aKey) {
        return hmacSign(aValue, aKey, "MD5");
    }

    /**
     * 生成签名消息
     *
     * @param aValue 要签名的字符串
     * @param aKey   签名密钥
     * @return
     */
    public static String hmacSign(String aValue, String aKey, String jiami) {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try {
            keyb = aKey.getBytes(encodingCharset);
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5c);
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(jiami);//"MD5"
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    /***
     * 对应python里面的hmac.new(API_SECRET, msg=message, digestmod=hashlib.sha256).hexdigest().upper()
     * @param key
     * @param value
     * @return
     */
    public static String hamacSha256(String key, String value) {
        String result = null;
        byte[] keyBytes = key.getBytes();
        SecretKeySpec localMac = new SecretKeySpec(keyBytes, "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(localMac);
            byte[] arrayOfByte = mac.doFinal(value.getBytes());
            BigInteger localBigInteger = new BigInteger(1,
                    arrayOfByte);
            result = String.format("%0" + (arrayOfByte.length << 1) + "x",
                    new Object[]{localBigInteger});

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String toHex(byte input[]) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    /**
     * @param args
     * @param key
     * @return
     */
    public static String getHmac(String[] args, String key) {
        if (args == null || args.length == 0) {
            return (null);
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            str.append(args[i]);
        }
        return (hmacSign(str.toString(), key));
    }

    /**
     * SHA加密
     *
     * @param aValue
     * @return
     */
    public static String digest(String aValue, String algorithm) {
        aValue = aValue.trim();
        byte value[];
        try {
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));

    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(encodingCharset);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        byte[] text = encryptText.getBytes(encodingCharset);
        //完成 Mac 操作
        return mac.doFinal(text);
    }

    /***
     * sha-1散列加密
     * @param aValue
     * @return
     */
    public static String digest(String aValue) {
        return digest(aValue, "SHA");
    }

    /***
     * sha-256散列加密
     * @param aValue
     * @return
     */
    public static String digestSha256(String aValue) {
        return digest(aValue, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA(final String strText) {
        return SHA(strText, "SHA");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param
     * @return
     */
    private static String SHA(final String strText, final String strType) {
        String strResult = null;
        if (strText != null && strText.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    public static String HmacSHA1(String aValue, String aKey) {
        try {

            byte key[] = aKey.getBytes(encodingCharset);
            byte data[] = aValue.getBytes(encodingCharset);

            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data);
            StringBuilder hs = new StringBuilder();
            String stmp;
            for (int n = 0; rawHmac != null && n < rawHmac.length; n++) {
                stmp = Integer.toHexString(rawHmac[n] & 0XFF);
                if (stmp.length() == 1) {
                    hs.append('0');
                }
                hs.append(stmp);
            }
            return hs.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
