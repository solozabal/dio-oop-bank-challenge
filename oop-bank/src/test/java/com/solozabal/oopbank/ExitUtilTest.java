package com.solozabal.oopbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ExitUtilTest {

    @Test
    public void testExit() {
        ExitException thrown = assertThrows(ExitException.class, () -> {
            ExitUtil.exit(1);
        });
        // Use the thrown variable to avoid warnings
        assertEquals(1, thrown.getStatus());
    }
}