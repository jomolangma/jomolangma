package com.jomolangma.app.hadoop.mapreduce.secondarysort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by ZhangLijun on 9/19/16.
 */

public class EntryPartitioner extends Partitioner<Entry, Text> {

    @Override
    public int getPartition(Entry entry, Text integer, int numberPartitions) {
        return Math.abs((entry.getYearMonth().hashCode() % numberPartitions));
    }
}
