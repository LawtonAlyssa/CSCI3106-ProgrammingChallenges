import java.lang.Math;
import java.util.Scanner;
class ARealChallenge {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long base = ( n / 2 ) + 1;
        long height = ( n / 2 + (n%2) ) + 1; 
        System.out.println(base * height);
    }
}