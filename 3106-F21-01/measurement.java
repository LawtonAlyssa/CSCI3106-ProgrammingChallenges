import java.util.*;
public class measurement {
    public static void main(String[] args){
        double[] values = new double[]{3,8,10,22,3,12,1000,1};
        Scanner scan = new Scanner(System.in);
        HashMap<String,Integer> words = new HashMap<>();
        words.put("thou", 7);
        words.put("th", 7);
        words.put("inch",6);
        words.put("in",6);
        words.put("foot",5);
        words.put("ft",5);
        words.put("yard",4);
        words.put("yd",4);
        words.put("chain",3);
        words.put("ch",3);
        words.put("furlong",2);
        words.put("fur",2);
        words.put("mile",1);
        words.put("mi",1);
        words.put("league",0);
        words.put("lea",0);

        String[] input = scan.nextLine().split(" ");
        int num = Integer.valueOf(input[0]);
        String arg1 = input[1];
        String arg2 = input[3];

        double ans = num;
        for(int i = words.get(arg1) - 1; i >= words.get(arg2); i--) {
            ans /= values[i];
        }
        for(int i = words.get(arg1); i <= words.get(arg2) - 1; i++) {
            ans *= values[i];
        }
        System.out.println(ans);
        


    }
}
