package com.jomolangma.app.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class CustomerPartitioner implements Partitioner {

    public CustomerPartitioner(VerifiableProperties verifiableProperties) {
    }

    @Override
    public int partition(Object key, int numPartitions) {
        try {
            int partitionNum = Integer.parseInt((String) key);
            return Math.abs(Integer.parseInt((String) key) % numPartitions);
        } catch (Exception e) {
            return Math.abs(key.hashCode() % numPartitions);
        }
    }
}