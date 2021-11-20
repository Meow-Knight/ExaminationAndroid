package com.example.tcpexamination.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private Utils() {}

    public static <T> List<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
