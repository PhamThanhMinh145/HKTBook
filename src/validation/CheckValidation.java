package validation;

import java.util.Scanner;

public class CheckValidation {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String inputMsg,String error, String format){
        String input;
        boolean match;
        while(true){
            System.out.println(inputMsg);
            input = sc.nextLine().toUpperCase();
            match = input.matches(format);
            if(input.length() == 0 || input.isEmpty()|| match == false)
                System.out.println(error);
            else
                return input;
        }
    }

    public static String getStringNoFormat(String inputMsg, String error){
        String input;
        while(true){
            System.out.println(inputMsg);
            input = sc.nextLine();
            if(input.length() == 0 || input.isEmpty())
                System.out.println(error);
            else
                return input;
        }


    }
    public static double getDoubleInAnInterval(String inputMsg, String error, double lowerBound, double upperBound){
        double n, tmp;
        if(lowerBound > upperBound){
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound= tmp;
        }
        while (true){
            try {
                System.out.println(inputMsg);
                n= Double.parseDouble(sc.nextLine());
                if(n < lowerBound || n > upperBound) throw  new Exception();
                return n ;
            }catch (Exception e){
                System.out.println(error);
            }
        }
    }

    public static double getDouble(String inputMsg, String error){
        double n;
        while (true){
            try{
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            }catch (Exception e){
                System.out.println(error);
            }
        }
    }

    public static int getIntInAnInterval(String inputMsg, String error, int lowerBound, int upperBound){
        int n, tmp;
        if(lowerBound > upperBound){
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound= tmp;
        }
        while (true){
            try {
                System.out.println(inputMsg);
                n= Integer.parseInt(sc.nextLine());
                if(n < lowerBound || n > upperBound) throw  new Exception();
                return n ;
            }catch (Exception e){
                System.out.println(error);
            }
        }
    }
    
    public static double getFloatInAnInterval(String inputMsg, String error, float lowerBound, float upperBound){
        float n, tmp;
        if(lowerBound > upperBound){
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound= tmp;
        }
        while (true){
            try {
                System.out.println(inputMsg);
                n= Float.parseFloat(sc.nextLine());
                if(n < lowerBound || n > upperBound) throw  new Exception();
                return n ;
            }catch (Exception e){
                System.out.println(error);
            }
        }
    }
}
