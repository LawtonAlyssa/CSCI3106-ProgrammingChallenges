package programmingchallenges;
import java.io.*;

/**
 * @author Edward Flores
 * Link: https://open.kattis.com/problems/areal
 */

public class aReal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Math.sqrt(Double.parseDouble(br.readLine())) * 4);
    }
}