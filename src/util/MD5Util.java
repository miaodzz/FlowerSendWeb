package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by Android on 2016/1/13.
 */
public class MD5Util {

    private static final int BUFFER_LENGTH = 1024*512;

    /**
     * 获取单个文件的MD5值！
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        DigestInputStream digestInputStream = null;
        byte buffer[] = new byte[BUFFER_LENGTH];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            digestInputStream = new DigestInputStream(in,digest);

            while ((len = in.read(buffer, 0, BUFFER_LENGTH)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (digestInputStream != null) digestInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /**
     *
     * @param str
     * @return
     */
    public static String getStringMD5(String str) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));

            byte[] byteArray = messageDigest.digest();

            StringBuilder md5StrBuff = new StringBuilder();
            for (byte aByteArray : byteArray) {
                if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & aByteArray));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
            }

            return md5StrBuff.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
