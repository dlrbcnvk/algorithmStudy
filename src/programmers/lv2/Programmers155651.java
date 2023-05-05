package programmers.lv2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 호텔 대실
 * 미해결
 */
public class Programmers155651 {

    private static class Book implements Comparable<Book> {
        int enterHour;
        int enterMinute;
        int outHour;
        int outMinute;

        public Book(int enterHour, int enterMinute, int outHour, int outMinute) {
            this.enterHour = enterHour;
            this.enterMinute = enterMinute;
            this.outHour = outHour;
            this.outMinute = outMinute;
        }

        @Override
        public int compareTo(Book b) {
            if (this.enterHour != b.enterHour) {
                return this.enterHour - b.enterHour;
            } else {
                return this.enterMinute - b.enterMinute;
            }
        }
    }

    public int solution(String[][] book_time) {
        Book[] books = new Book[book_time.length];
        for (int i = 0; i < books.length; i++) {
            String[] enterTime = book_time[i][0].split(":");
            String[] outTime = book_time[i][1].split(":");
            int enterHour = Integer.parseInt(enterTime[0]);
            int enterMinute = Integer.parseInt(enterTime[1]);
            int outHour = Integer.parseInt(outTime[0]);
            int outMinute = Integer.parseInt(outTime[1]);
            books[i] = new Book(enterHour, enterMinute, outHour, outMinute);
        }
        Arrays.sort(books);

        int currentHour = 0;
        int currentMinute = 0;
        int booksIdx = 0;
        PriorityQueue<Book> pq = new PriorityQueue<>((b1, b2) -> {
            if (b1.outHour != b2.outHour) {
                return b1.outHour - b2.outHour;
            } else {
                return b1.outMinute - b2.outMinute;
            }
        });
        int maxPQSize = 0;
        while (booksIdx < books.length) {

            // 예약받을 손님이 있으면 받고
            while (booksIdx < books.length) {
                Book book = books[booksIdx];
                if (book.enterHour == currentHour && book.enterMinute == currentMinute) {
                    pq.add(books[booksIdx]);
                    booksIdx++;
                } else {
                    break;
                }
            }


            // 나갈 사람은 나가고
            while (!pq.isEmpty()) {
                Book peek = pq.peek();
                if (peek.outHour == currentHour && peek.outMinute == currentMinute) {
                    pq.poll();
                } else {
                    break;
                }
            }

            // 현재 동시 예약자수 확인
            maxPQSize = Math.max(pq.size(), maxPQSize);

            // 시간이 지나간다
            if (currentMinute == 59) {
                currentHour++;
                currentMinute = 0;
            } else {
                currentMinute++;
            }

        }

        return maxPQSize;
    }

    public static void main(String[] args) {
        Programmers155651 programmers155651 = new Programmers155651();
        int solution = programmers155651.solution(
                new String[][]{
                        {"15:00", "17:00"},
                        {"16:40", "18:20"},
                        {"14:20", "15:20"},
                        {"14:10", "19:20"},
                        {"14:20", "15:20"}
                }
        );
        System.out.println(solution);
    }
}
