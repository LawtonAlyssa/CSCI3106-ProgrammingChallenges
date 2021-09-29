import java.util.*;
class NoDuplicates {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String[] words = scan.nextLine().split(" ");
        Set<String> set = new HashSet<>();
        boolean hasDuplicate = false;
        for(String word: words){
            if(!set.add(word)) {
                hasDuplicate = true;
                break;
            }
        }
        System.out.println(hasDuplicate?"no":"yes");
    }
}
