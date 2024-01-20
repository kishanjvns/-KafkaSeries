package com.tech.kj.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConsumerRunner implements ApplicationRunner {
    @Autowired
    private KafkaConsumer consumer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runnable consumerWorker =()->{
            consumer.subscribe(List.of("testTopic"));
            while (true){
                ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String,String> record:records){
                    System.out.println("Key "+record.key()+ " value "+ record.value());
                    System.out.println("read from partition "+record.partition()+" at offset "+record.offset());
                }

            }
        };
        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(consumerWorker);
        es.shutdown();
    }
}
