package com.jomolangma.app.jcip.examples;

import com.jomolangma.app.jcip.annotations.GuardedBy;
import com.jomolangma.app.jcip.annotations.ThreadSafe;

/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
