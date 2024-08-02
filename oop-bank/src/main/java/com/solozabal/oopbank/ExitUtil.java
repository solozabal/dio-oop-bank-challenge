package com.solozabal.oopbank;

public class ExitUtil {
    public static void exit(int status) {
        throw new ExitException(status);
    }
}