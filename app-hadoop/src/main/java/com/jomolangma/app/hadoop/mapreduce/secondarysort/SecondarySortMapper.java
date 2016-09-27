package com.jomolangma.app.hadoop.mapreduce.secondarysort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ZhangLijun on 9/19/16.
 */
public class SecondarySortMapper extends Mapper<LongWritable, Text, Entry, Text> {

    private Entry entry = new Entry();
    private Text value = new Text();

    @Override
    protected void map(LongWritable key, Text lines, Mapper.Context context)
            throws IOException, InterruptedException {
        String line = lines.toString();
        String[] tokens = line.split(",");
        // YYYY = tokens[0]
        // MM = tokens[1]
        // count = tokens[2]
        String yearMonth = tokens[0] + "-" + tokens[1];
        int count = Integer.parseInt(tokens[2]);

        entry.setYearMonth(yearMonth);
        entry.setCount(count);
        value.set(tokens[2]);

        context.write(entry, value);
    }
}
