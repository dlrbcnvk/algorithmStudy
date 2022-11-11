package javajungsuk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestInfo {
    int count() default 1;
    String testedBy();
    String[] testTools() default "JUnit";

}
