package com.jomolangma.app.jcip.examples;

/**
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
    	int i = n;
        if (n != i)
            throw new AssertionError("This statement is false.");
    }
}
