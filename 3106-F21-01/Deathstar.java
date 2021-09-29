import java.util.*;

class Deathstar {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int testCases = scan.nextInt();
        for(int i = 0; i < testCases; i++){
            int ans = 0;
            for(int j = 0; j < testCases; j++){
                ans |= scan.nextInt();
            }
            System.out.println(ans);
        }
    }
}