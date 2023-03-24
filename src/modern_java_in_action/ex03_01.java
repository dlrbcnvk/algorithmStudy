package modern_java_in_action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Comparator.*;

public class ex03_01 {

    public class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.RED, 150));
        inventory.sort(comparing(Apple::getWeight));

        // way 1
        inventory.sort(new AppleComparator());

        // way 2
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        // way 3
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // way 4
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // way 5
        inventory.sort(comparing(apple -> apple.getWeight()));

        // way 6
        inventory.sort(comparing(Apple::getWeight));

        // 역정렬
        inventory.sort(comparing(Apple::getWeight).reversed());

        // 조건 여러개
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
    }
}
