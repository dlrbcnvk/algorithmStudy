package javajungsuk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputPractice {
    public static void main(String[] args) throws IOException {

//        useScanner();

        useBufferedReader();
    }

    public static void useBufferedReader() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        for (String word : line) {
            System.out.println(word);
        }
        System.out.println();


        String input = bf.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");
        System.out.println("----StringTokenizer started----");
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            System.out.println(str);
        }
        System.out.println("----StringTokenizer ended----");
        System.out.println();
        System.out.println("--StringBuilder started--");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i + " ");
        }
        System.out.print(sb);

        System.out.println("\n--StringBuilder ended--");
    }

    public static void useScanner() {
        // Scanner
        Scanner scanner = new Scanner(System.in);
        int num;
        String str;

        num = scanner.nextInt();
        scanner.nextLine();
        str = scanner.nextLine();

        System.out.println(num);
        System.out.println(str);
    }
}
