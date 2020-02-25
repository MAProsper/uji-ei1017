package Tarifas;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestTarifa {


    public static Stream<Arguments> genericData() {
        return Stream.of(
                Arguments.of(0.2),
                Arguments.of(0.7),
                Arguments.of(0.15),
                Arguments.of(0.19),
                Arguments.of(18.2),
                Arguments.of(15.4)
        );
    }

    private static Tarifa tarifaConstructor;

    @ParameterizedTest
    @MethodSource("genericData")
    public void ConstructorTestTarifa(double t) {
        tarifaConstructor = new Tarifa(t);
        assertEquals(tarifaConstructor.getTarifa(), t, 0.1);
    }

    @AfterAll
    public static void finish() {
        tarifaConstructor = null;
    }
}

