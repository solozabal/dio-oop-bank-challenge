package com.solozabal.oopbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ExitExceptionTest {

    @Test
    public void testExitExceptionStatus() {
        int expectedStatus = 1;
        ExitException exception = new ExitException(expectedStatus);
        assertEquals(expectedStatus, exception.getStatus());
    }
}