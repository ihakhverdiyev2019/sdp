package ada.spd.startup.Others;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSSender {
    public static final String ACCOUNT_SID =
            "AC5b837a8d190eeee0035243138ed670a9";
    public static final String AUTH_TOKEN =
            "adf739bde1438b7c17c1a942b75c0e95";


    public static void smsSender(String phone, int smsCode) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(phone), // to
                        new PhoneNumber("+19167964390"), // from
                        String.valueOf(smsCode))
                .create();
    }
}
