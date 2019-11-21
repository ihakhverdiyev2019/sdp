package ada.spd.startup.Others;


import java.util.Random;

public class GenerateCode {

    public static int codeSMS(){
        Random sc = new Random();
        return (sc.nextInt(8999)+1000);

    }




}
