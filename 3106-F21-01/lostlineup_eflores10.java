import java.util.*;

class lostlineup {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numPeople = scan.nextInt();
        int[] ans = new int[numPeople];
        ans[0] = 1;
        for(int i = 2; i <= numPeople; i++){
            int pos = scan.nextInt();
            ans[1 + pos] = i;
        }
        for(int i = 0; i < ans.length; i++){
            if(i == ans.length - 1) {
                System.out.print(ans[i]);
            } else {
                System.out.print(ans[i] + " ");
            }
        }
    }
}
