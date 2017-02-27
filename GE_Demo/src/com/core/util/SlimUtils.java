package com.core.util;

import java.util.Arrays;
import java.util.List;


/**
 * Utilities for SLIM fixtures.
 *
 */
public class SlimUtils {

    /**
     * Create list from parameters.
     * @param items objects to place in list.
     * @return list of parameters.
     */
    public static List list(Object... items) {
        return Arrays.asList(items);
    }

    /**
     * Constructor to prevent accidental creation.
     */
    private SlimUtils() {
    }
}
