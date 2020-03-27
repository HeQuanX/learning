package cn.crabapples.spring.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * TODO AES加密工具
 *
 * @author Mr.He
 * 2019/7/3 23:24
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class AesUtils {
    private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);
    private static final String SEED = "123456";

    /**
     * @param keyString 密钥
     * @param source    需要加解密的字符串
     * @param type      需要执行的操作(加/解密)
     * @return 输出的字符串
     * @throws Exception 运行过程中可能出现的异常
     */
    public static String doFinal(String keyString, String source, int type) throws Exception {
        /*
         * 初始化加密方式
         */
        Cipher cipher = Cipher.getInstance("AES");
        /*
         * 当操作类型为加密时
         */
        if (type == Cipher.ENCRYPT_MODE) {
            /*
             * 初始化Cipher为加密
             */
            cipher.init(Cipher.ENCRYPT_MODE, createKey(keyString));
            byte[] dataByte = cipher.doFinal(source.getBytes());
            byte[] encodeByte = Base64.getEncoder().encode(dataByte);
            String data = parseByte2HexStr(encodeByte);
            logger.info("加密之后的数据:[{}]", data);
            return data;
        } else if (type == Cipher.DECRYPT_MODE) {
            /*
             * 初始化Cipher为解密
             */
            cipher.init(Cipher.DECRYPT_MODE, createKey(keyString));
            byte[] sourceByte = parseHexStr2Byte(source);
            byte[] decodeByte = Base64.getDecoder().decode(sourceByte);
            byte[] dataByte = cipher.doFinal(decodeByte);
            String data = new String(dataByte);
            logger.info("解密之后的数据:[{}]", data);
            return data;
        } else {
            /*
             * 当输入的类型不匹配加/解密时抛出异常
             */
            throw new RuntimeException("please input type");
        }
    }

    /**
     * 用于将密钥种子转换为KEY
     *
     * @param seed 密钥种子
     * @return 密钥
     * @throws Exception 生成密钥可能出现的异常
     */
    private static Key createKey(String seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        /*
         * Linux下默认的算法是“NativePRNG”
         * windows下默认是“SHA1PRNG”（sun提供的算法）
         */
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        logger.debug("生成的key为:[{}],种子为:[{}]", secretKey, seed);
        return secretKey;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param source 二进制数组
     * @return 16进制字符串
     */
    public static String parseByte2HexStr(byte source[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length; i++) {
            String hex = Integer.toHexString(source[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 16进制字符串
     * @return 二进制数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}