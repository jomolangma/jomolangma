package com.jomolangma.app.hadoop.mapreduce.secondarysort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by ZhangLijun on 9/19/16.
 */
public class SecondarySortReducer extends Reducer<Entry, Text, Entry, Text> {
    @Override
    protected void reduce(Entry key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        StringBuilder builder = new StringBuilder();
        for (Text value : values) {
            builder.append(value.toString());
            builder.append(",");
        }
        context.write(key, new Text(builder.toString()));
    }
}
