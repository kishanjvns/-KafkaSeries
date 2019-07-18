package com.tech.kj.kafka.config;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerConfig {
	private static Producer<String, String> producer;

	public static Producer<String, String> createProducer() {
		Properties p = new Properties();
		p.put("bootstrap.servers", "192.168.0.4:9092");
		//p.put("acks", "all");
		//p.put("retries", 0);
		//p.put("batch.size", 10);
		//p.put("linger.ms", 1);
		p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(p);
		return producer;
	}

	public void sendMessage(String topic, String message) {
		switch (topic) {
		case "producer1":
			Future<RecordMetadata> futureResponse= producer.send(new ProducerRecord<String, String>(topic, message));
			try {
				System.out.println(futureResponse.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			break;

		default:
			throw new IllegalArgumentException("Invalid Topic name pass");			
		}
	}
}
