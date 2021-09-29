import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
public class DasLights {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int light1 = scan.nextInt();
        int light2 = scan.nextInt();
        int seconds = scan.nextInt();
        Set<Integer> set = new HashSet<>(); 
        for(int i = light1; i <= seconds; i += light1 ){
            set.add(i);
        }
        boolean flag = false;
        for(int i = light2; i <= seconds; i += light2 ){

            if(set.contains(i)) {
                flag = true;
                break;
            }
        }
        System.out.println((flag)?"yes":"no");
    }
}
