package com.jomolangma.app.hadoop.mapreduce.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by ZhangLijun on 9/19/16.
 */

public class EntryGroupingComparator extends WritableComparator {
    public EntryGroupingComparator() {
        super(Entry.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Entry a1 = (Entry) a;
        Entry b1 = (Entry) b;
        return a1.getYearMonth().compareTo(b1.getYearMonth());
    }
}
