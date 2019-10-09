package utils.secure;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class StringXORer {
    public static String encode(String str, Key key) {
        return base64Encode(xorWithKey(str.getBytes(), key.getCode().getBytes()));
    }

    public static String decode(String str, Key key) {
        return new String(xorWithKey(base64Decode(str), key.getCode().getBytes()));
    }

    private static byte[] xorWithKey(byte[] arr, byte[] key) {
        byte[] out = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            out[i] = (byte) (arr[i] ^ key[i%key.length]);
        }
        return out;
    }

    private static byte[] base64Decode(String str) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(str);
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    private static String base64Encode(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes).replaceAll("\\s", "");
    }
}
