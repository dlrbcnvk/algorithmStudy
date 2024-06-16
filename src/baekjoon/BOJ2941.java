package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 크로아티아 알파벳
 * https://www.acmicpc.net/problem/2941
 */
public class BOJ2941 {

    private static String[] dic = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    private static String EMPTY = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int count = 0;
        while (!str.equals(EMPTY)) {
            boolean startsWithDic = false;
            for (int i = 0; i < dic.length; i++) {
                if (str.startsWith(dic[i])) {
                    str = str.substring(dic[i].length());
                    startsWithDic = true;
                    count++;
                    break;
                }
            }
            if (!startsWithDic) {
                str = str.substring(1);
                count++;
            }
        }

        System.out.println(count);
    }
}
