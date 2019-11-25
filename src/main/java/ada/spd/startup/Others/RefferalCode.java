package ada.spd.startup.Others;

import java.security.SecureRandom;
import java.util.Random;

public class RefferalCode {

    private final int codeLength = 8;
    public String createRandomCode(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output ;
    }
}
