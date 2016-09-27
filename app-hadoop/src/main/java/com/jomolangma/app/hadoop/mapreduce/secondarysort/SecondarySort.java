package com.jomolangma.app.hadoop.mapreduce.secondarysort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by ZhangLijun on 9/19/16.
 */
public class SecondarySort {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(SecondarySort.class);
        job.setJobName("SecondarySort");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Entry.class);
        job.setOutputValueClass(Text.class);

        job.setMapperClass(SecondarySortMapper.class);
        job.setReducerClass(SecondarySortReducer.class);
        job.setPartitionerClass(EntryPartitioner.class);
        job.setGroupingComparatorClass(EntryGroupingComparator.class);
        job.setNumReduceTasks(Integer.valueOf(args[2]));

        job.waitForCompletion(true);
    }
}
