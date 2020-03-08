package helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static helpers.ValidatorArguments.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestHelperArgument {
    public static Stream<Arguments> dataValidateVoid() {
        return Stream.of(
                Arguments.of("valid", true),
                Arguments.of("invalid", false)
        );
    }

    public static Stream<Arguments> dataValidateValue() {
        return Stream.of(
                Arguments.of("valid", null, true),
                Arguments.of("valid", 1, true),
                Arguments.of("valid", "", true),
                Arguments.of("invalid", null, false),
                Arguments.of("invalid", 1, false),
                Arguments.of("invalid", "", false)
        );
    }

    public static Stream<Arguments> dataNumberNotNegative() {
        return Stream.of(
                Arguments.of("negative", -1, false),
                Arguments.of("zero", 0, true),
                Arguments.of("positive", 1, true)
        );
    }

    public static Stream<Arguments> dataReferenceNotNull() {
        return Stream.of(
                Arguments.of("null", null, false),
                Arguments.of("base", 0, true),
                Arguments.of("class", "", true)
        );
    }

    public static Stream<Arguments> dataStringNotEmpty() {
        return Stream.of(
                Arguments.of("null", null, false),
                Arguments.of("empty", "", false),
                Arguments.of("non-empty", "message", true)
        );
    }

    public static Stream<Arguments> dataStringMatchesPattern() {
        return Stream.of(
                Arguments.of("valid", "0", "\\d", true),
                Arguments.of("invalid", "00", "\\d", false),
                Arguments.of("invalid", "a", "\\d", false)
        );
    }

    public static Stream<Arguments> dataCollectionWithoutNull() {
        return Stream.of(
                Arguments.of("valid", Collections.emptyList(), true),
                Arguments.of("valid", Arrays.asList(1, 2), true),
                Arguments.of("invalid", Arrays.asList(1, null), false),
                Arguments.of("invalid", null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("dataValidateVoid")
    public void testValidate(final String message, final boolean valid) {
        if (valid) assertDoesNotThrow(() -> validate(message, valid));
        else assertThrows(IllegalArgumentException.class, () -> validate(message, valid));
    }

    @ParameterizedTest
    @MethodSource("dataValidateValue")
    public <T> void testValidate(final String message, final T value, final boolean valid) {
        if (valid) assertEquals(value, validate(message, value, valid));
        else assertThrows(IllegalArgumentException.class, () -> validate(message, value, valid));
    }

    @ParameterizedTest
    @MethodSource("dataNumberNotNegative")
    public void testNumberNotNegative(final String message, final Number n, final boolean valid) {
        if (valid) assertEquals(n, numberNotNegative(message, n));
        else assertThrows(IllegalArgumentException.class, () -> numberNotNegative(message, n));
    }

    @ParameterizedTest
    @MethodSource("dataReferenceNotNull")
    public <T> void testReferenceNotNull(final String message, final T value, final boolean valid) {
        if (valid) assertEquals(value, referenceNotNull(message, value));
        else assertThrows(IllegalArgumentException.class, () -> referenceNotNull(message, value));
    }

    @ParameterizedTest
    @MethodSource("dataStringNotEmpty")
    public void testStringNotEmpty(final String message, final String s, final boolean valid) {
        if (valid) assertEquals(s, stringNotEmpty(message, s));
        else assertThrows(IllegalArgumentException.class, () -> stringNotEmpty(message, s));
    }

    @ParameterizedTest
    @MethodSource("dataStringMatchesPattern")
    public void testStringMatchesPattern(final String message, final String s, final String regex, final boolean valid) {
        if (valid) assertEquals(s, stringMatchesPattern(message, s, regex));
        else assertThrows(IllegalArgumentException.class, () -> stringMatchesPattern(message, s, regex));
    }

    @ParameterizedTest
    @MethodSource("dataCollectionWithoutNull")
    public void testCollectionWithoutNull(final String message, final Collection<?> ref, final boolean valid) {
        if (valid) assertEquals(ref, collectionWithoutNull(message, ref));
        else assertThrows(IllegalArgumentException.class, () -> collectionWithoutNull(message, ref));
    }
}
