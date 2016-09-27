package com.jomolangma.app.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerTest {
	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();
		props.put("metadata.broker.list", "node1:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.jomolangma.app.kafka.CustomerPartitioner");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);

		for (int i = 1; i <= 500; i++) {
			List<KeyedMessage<String, String>> messageList = new ArrayList<KeyedMessage<String, String>>();
			for (int j = 0; j < 4; j++) {
				messageList.add(new KeyedMessage<>(
						"kafka_zhanglijun", j + "", "The " + i + " message for key " + j));
			}
			producer.send(messageList);

			Thread.sleep(1000);
		}
		producer.close();
	}
}