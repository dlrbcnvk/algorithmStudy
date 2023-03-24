package modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class ex05_01 {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
                );

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        // 질문 1: 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        // 질문 2: 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        // 질문 3: 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .sorted()
                .collect(toList());

        // 질문 4: 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        traders.stream()
                .map(Trader::getName)
                .sorted()
                .collect(toList());

        // 질문 5: 밀라노에 거래자가 있는가?
        traders.stream()
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        // 질문 6: 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getName().equals("Cambridge"))
                .forEach(System.out::println);

        // 질문 7: 전체 트랜잭션 중 최댓값은 얼마인가?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);

        // 질문 8: 전체 트랜잭션 중 최솟값은 얼마인가?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer.MAX_VALUE, Integer::min);

    }
}
