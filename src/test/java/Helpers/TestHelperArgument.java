package Helpers;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static Helpers.HelperArgument.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestHelperArgument {
    public static Stream<Arguments> genericVoidData() {
        return Stream.of(
                Arguments.of(true, "mensage"),
                Arguments.of(false, "mensage")
        );
    }

    @ParameterizedTest
    @MethodSource("genericVoidData")
    public void genericTest(boolean valid, String error){
        if (valid) assertDoesNotThrow(() -> generic(valid, error));
        else assertThrows(IllegalArgumentException.class, () -> generic(valid, error));
    }

    public static Stream<Arguments> genericValueData() {
        return Stream.of(
                Arguments.of(null, true, "mensage"),
                Arguments.of(1, true, "mensage"),
                Arguments.of("", true, "mensage"),
                Arguments.of(null, false, "mensage"),
                Arguments.of(1, false, "mensage"),
                Arguments.of("", false, "mensage")
        );
    }

    @ParameterizedTest
    @MethodSource("genericValueData")
    public <T> void genericTest(T value, boolean valid, String error){
        if (valid) assertEquals(value, generic(value, valid, error));
        else assertThrows(IllegalArgumentException.class, () -> generic(value, valid, error));
    }

    public static Stream<Arguments> stringNotEmptyData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("message", true)
        );
    }

    @ParameterizedTest
    @MethodSource("stringNotEmptyData")
    public void stringNotEmptyTest(String s, boolean valid) {
        if (valid) assertEquals(s, stringNotEmpty("mesage", s));
        else assertThrows(IllegalArgumentException.class, () -> stringNotEmpty("mesage", s));
    }

    public static Stream<Arguments> numberNotNegativeData() {
        return Stream.of(
                Arguments.of(-1, false),
                Arguments.of(0, true),
                Arguments.of(1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("numberNotNegativeData")
    public void numberNotNegativeTest(double n, boolean valid) {
        if (valid) assertEquals(n, numberNotNegative("mesage", n));
        else {
            assertThrows(IllegalArgumentException.class, () -> {
                double fix = numberNotNegative("mesage", n);  //Don't remove, generates a warning
            });
        }
    }

    public static Stream<Arguments> referenceNotNullData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(0, true),
                Arguments.of("", true)
        );
    }

    @ParameterizedTest
    @MethodSource("referenceNotNullData")
    public <T> void referenceNotNullTest(T value, boolean valid) {
        if (valid) assertEquals(value, referenceNotNull("mesage", value));
        else assertThrows(IllegalArgumentException.class, () -> referenceNotNull("mesage", value));
    }
}
