package Helpers;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static Helpers.HelperArgument.referenceNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class HelperArgument {
    public static Stream<Arguments> genericData() {
        return Stream.of(
                Arguments.of(true, "bien"),
                Arguments.of(false, "error")
        );
    }

    @Test
    public void genericTest() { //boolean valid, String error
        referenceNotNull("ref", new String());
        assertEquals(true, true);
        /*
        if (valid) assertDoesNotThrow(() -> Helpers.HelperArgument.generic(valid, error));
        else assertThrows(IllegalArgumentException.class, () -> Helpers.HelperArgument.generic(valid, error));*/
    }
}
