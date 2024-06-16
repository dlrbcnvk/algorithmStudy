package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 그룹단어 체커
 * https://www.acmicpc.net/problem/1316
 */
public class BOJ1316 {

    private static String a = "a";
    private static String b = "b";
    private static String c = "c";
    private static String d = "d";
    private static String e = "e";
    private static String f = "f";
    private static String g = "g";
    private static String h = "h";
    private static String i = "i";
    private static String j = "j";
    private static String k = "k";
    private static String l = "l";
    private static String m = "m";
    private static String n = "n";
    private static String o = "o";
    private static String p = "p";
    private static String q = "q";
    private static String r = "r";
    private static String s = "s";
    private static String t = "t";
    private static String u = "u";
    private static String v = "v";
    private static String w = "w";
    private static String x = "x";
    private static String y = "y";
    private static String z = "z";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (groupCheck(br.readLine())) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean groupCheck(String str) {
        str = str.replaceAll("a+", a)
                .replaceAll("b+", b)
                .replaceAll("c+", c)
                .replaceAll("d+", d)
                .replaceAll("e+", e)
                .replaceAll("f+", f)
                .replaceAll("g+", g)
                .replaceAll("h+", h)
                .replaceAll("i+", i)
                .replaceAll("j+", j)
                .replaceAll("k+", k)
                .replaceAll("l+", l)
                .replaceAll("m+", m)
                .replaceAll("n+", n)
                .replaceAll("o+", o)
                .replaceAll("p+", p)
                .replaceAll("q+", q)
                .replaceAll("r+", r)
                .replaceAll("s+", s)
                .replaceAll("t+", t)
                .replaceAll("u+", u)
                .replaceAll("v+", v)
                .replaceAll("w+", w)
                .replaceAll("x+", x)
                .replaceAll("y+", y)
                .replaceAll("z+", z);

        Set<Character> characterSet = new HashSet<>();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (!characterSet.contains(charArray[i])) {
                characterSet.add(charArray[i]);
            } else {
                return false;
            }
        }
        return true;
    }
}
