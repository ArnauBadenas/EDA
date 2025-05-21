package pckg;

import java.util.Random;

public class Exer_03_Palindromes {


    private static Random rand = new Random();
    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        /* COMPLETE */
        // Using functions genPalindrom and genNoPalindrom, test your code here
        for (int i = 0; i < 100; i++) {
            try {
                String palindrom = genPalindrome();
                if (!isPalindrome(palindrom.toLowerCase())) {
                    System.out.println("Test failed with word " + palindrom);
                }
            } catch (NullPointerException e) {
                System.out.println("El generador de palindroms ha donat un palindrom nul.");
            }

        }

        for (int i = 0; i < 100; i++) {
            try {
                String noPalindrom = genNoPalindrome();
                if (isPalindrome(noPalindrom.toLowerCase())) {
                    System.out.println("Test failed with word " + noPalindrom);
                }
            } catch (NullPointerException e) {
                System.out.println("El generador de palindroms ha donat un palindrom nul.");
            }
        }

        System.out.println("Test finished.");

    }

    /* COMPLETE */
    // write here the code to determine whether a string is a palindrome or not
    public static boolean isPalindrome(String word) {
        return subWord(word, 0, word.length() - 1);
    }

    public static boolean subWord(String word, int startIndex, int endIndex) {
        if (startIndex >= endIndex) { //Paraula ja iterada
            return true;
        }
        if (word.charAt(startIndex) != word.charAt(endIndex)) {
            return false;
        }
        return subWord(word, startIndex + 1, endIndex - 1);
    }

    /*DO NOT MODIFY CODE BELOW*/
    private static String genPalindrome() {
        StringBuffer sb = new StringBuffer();
        int size = rand.nextInt(21);
        if (size % 2 == 1) sb.append(chars.charAt(rand.nextInt(chars.length())));
        for (int i = 1; i <= size / 2; i++) {
            sb.insert(0, chars.charAt(rand.nextInt(chars.length())));
            sb.append(sb.charAt(0));
        }
        return sb.toString();
    }

    private static String genNoPalindrome() {
        int offset, posCar;
        StringBuffer sb;
        String s = genPalindrome();
        while (s.length() <= 1)
            s = genPalindrome();
        sb = new StringBuffer(s);
        offset = rand.nextInt(sb.length() / 2);
        posCar = rand.nextInt(chars.length());
        sb.setCharAt(offset, chars.charAt(posCar));
        sb.setCharAt(sb.length() - 1 - offset, chars.charAt((posCar + 1) % chars.length()));
        return sb.toString();
    }

}
