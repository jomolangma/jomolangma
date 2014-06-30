package com.jomolangma.app.jcip.examples;

import java.util.*;

/**
 * UnsafeVectorHelpers
 * <p/>
 * Compound actions on a Vector that may produce confusing results
 *
 * @author Brian Goetz and Tim Peierls
 */
public class UnsafeVectorHelpers {
    @SuppressWarnings("rawtypes")
	public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    @SuppressWarnings("rawtypes")
	public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
