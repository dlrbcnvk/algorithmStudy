package programmers.lv2;

import java.util.Arrays;

/**
 * 파일명 정렬
 */
public class Programmers17686 {

    static class File implements Comparable<File> {
        int id;
        String fileName;
        String head;
        Integer number;
        String tail;

        public File(int id, String fileName) {
            this.id = id;
            this.fileName = fileName;
            separateFileName();
        }

        public void separateFileName() {

            char[] chars = fileName.toCharArray();
            int numberStartIdx = 0;
            int tailStartIdx = 0;
            boolean numberStartIdxFound = false;
            boolean tailStartIdxFound = false;
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i]) && !numberStartIdxFound) {
                    numberStartIdx = i;
                    numberStartIdxFound = true;
                }
                if (numberStartIdxFound && !Character.isDigit(chars[i]) && !tailStartIdxFound) {
                    tailStartIdx = i;
                    tailStartIdxFound = true;
                }
                if (numberStartIdxFound && tailStartIdxFound) {
                    break;
                }
            }

            this.head = fileName.substring(0, numberStartIdx);
            if (!tailStartIdxFound) {
                this.number = Integer.parseInt(fileName.substring(numberStartIdx));
            } else {
                this.number = Integer.parseInt(fileName.substring(numberStartIdx, tailStartIdx));
            }
            if (fileName.length() == tailStartIdx || !tailStartIdxFound) {
                this.tail = "";
            } else {
                this.tail = fileName.substring(tailStartIdx);
            }
        }

        @Override
        public int compareTo(File file) {
            String s1 = this.head.toLowerCase();
            String s2 = file.head.toLowerCase();
            if (s1.compareTo(s2) < 0) {
                return -1;
            } else if (s1.compareTo(s2) > 0) {
                return 1;
            } else {
                if (this.number < file.number) {
                    return -1;
                } else if (this.number > file.number) {
                    return 1;
                } else {
                    if (this.id < file.id) {
                        return -1;
                    } else if (this.id > file.id) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

    public String[] solution(String[] files) {

        File[] fileArr = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileArr[i] = new File(i, files[i]);
        }

        Arrays.sort(fileArr);

        String[] answer = new String[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            answer[i] = fileArr[i].fileName;
        }
        return answer;
    }

    public static void main(String[] args) {
        Programmers17686 programmers17686 = new Programmers17686();
        String[] solution = programmers17686.solution(
            new String[]{"foo9.txt", "foo010bar020.zip", "F-15"}
        );

        for (String file : solution) {
            System.out.print(file + " ");
        }
    }
}
