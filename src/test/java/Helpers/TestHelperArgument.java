package Helpers;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static Helpers.TestHelperArgument.generic;
import static Helpers.TestHelperArgument.referenceNotNull;

public class TestHelperArgument {
    public static Stream<Arguments> genericData() {
        return Stream.of(
                Arguments.of(true, "bien"),
                Arguments.of(false, "error")
        );
    }


    @ParameterizedTest
    @MethodSource("genericData")
    public void genericTest(boolean valid, String error){


    }

}
