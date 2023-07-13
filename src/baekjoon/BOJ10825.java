package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * BOJ 10825
 * 국영수
 * 정렬
 */
public class BOJ10825 {

    static class Student implements Comparable<Student> {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }


        @Override
        public int compareTo(Student student) {
            if (this.korean != student.korean) {
                // 국어점수 내림차순
                return student.korean - this.korean;
            } else {
                // 영어점수 오름차순
                if (this.english != student.english) {
                    return this.english - student.english;
                } else {
                    // 수학점수 내림차순
                    if (this.math != student.math) {
                        return student.math - this.math;
                    } else {
                        return this.name.compareTo(student.name);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            String name = split[0];
            Integer korean = Integer.valueOf(split[1]);
            Integer english = Integer.valueOf(split[2]);
            Integer math = Integer.valueOf(split[3]);
            Student student = new Student(name, korean, english, math);
            students[i] = student;
        }

        Arrays.sort(students);

        Arrays.sort(students, (s1, s2) -> {
            if (s1.korean != s2.korean) {
                // 국어점수 내림차순
                return s2.korean - s1.korean;
            } else {
                // 영어점수 오름차순
                if (s1.english != s2.english) {
                    return s1.english - s2.english;
                } else {
                    // 수학점수 내림차순
                    if (s1.math != s2.math) {
                        return s2.math - s1.math;
                    } else {
                        return s1.name.compareTo(s2.name);
                    }
                }
            }
        });




        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.name);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
