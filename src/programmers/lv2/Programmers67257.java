package programmers.lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 수식 최대화
 */
public class Programmers67257 {

    private static final String[][] precedence = new String[][]{
            "+-*".split(""),
            "+*-".split(""),
            "-+*".split(""),
            "-*+".split(""),
            "*+-".split(""),
            "*-+".split(""),
    };

    private Long maxLong = Long.MIN_VALUE;

    public long solution(String expression) {

        ArrayList<String> strList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(expression, "+-*", true);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            strList.add(s);
        }

//        // Pattern - Matcher 사용
//        Pattern p = Pattern.compile("[+\\-*]|[0-9]+");
//        Matcher m = p.matcher(expression);
//        while (m.find()) {
//            strList.add(m.group());
//        }

        for (int i = 0; i < 6; i++) {
            ArrayList<String> inputList = new ArrayList<>(strList);
            String[] operators = precedence[i];

            operateInOrder(inputList, operators);

            Long value = Long.valueOf(inputList.get(0));
            if (value < 0) {
                value = -value;
            }

            maxLong = Math.max(maxLong, value);
        }

        return maxLong;
    }

    public long calculate(long lhs, long rhs, String operator) {
        if (operator.equals("+")) {
            return lhs + rhs;
        } else if (operator.equals("-")) {
            return lhs - rhs;
        } else if (operator.equals("*")) {
            return lhs * rhs;
        } else {
            return 0L;
        }
    }

    public void operateInOrder(List<String> inputList, String[] operators) {
        String operator;
        for (int k = 0; k < 3; k++) {
            operator = operators[k];

            while (inputList.contains(operator)) {
                int idx = inputList.indexOf(operator);
                long lhs = Long.parseLong(inputList.get(idx - 1));
                long rhs = Long.parseLong(inputList.get(idx + 1));
                long calculate = calculate(lhs, rhs, operator);
                inputList.remove(idx - 1);
                inputList.remove(idx - 1);
                inputList.remove(idx - 1);

                inputList.add(idx - 1, String.valueOf(calculate));
            }
        }
    }

    public static void main(String[] args) {
        Programmers67257 programmers67257 = new Programmers67257();
        long solution = programmers67257.solution("100-200*300-500+20"	);
        System.out.println(solution);
    }
}
