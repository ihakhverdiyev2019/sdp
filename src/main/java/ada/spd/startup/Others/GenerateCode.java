package ada.spd.startup.Others;


import java.util.Random;

public class GenerateCode {

    public static int codeSMS(){
        Random sc = new Random();
        return (sc.nextInt(89999)+10000);
    }




}
