package com.tech.kj.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProducerRunner implements ApplicationRunner {
    @Autowired
    private KafkaProducer producer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runnable producerWorker = ()->{
            for(int i=0;i< 100;i++){
                try{
                    Thread.sleep(11000);
                    System.out.println("message send to topic testTopic");
                    ProducerRecord<String,String> rc=new ProducerRecord("testTopic","Testing from java consumer, counter "+i);
                    producer.send(rc);
                }catch (Exception ex){
                    System.out.println("exception in producing "+ex.getMessage());
                }
            }
            System.out.println("completed");
        };

        ExecutorService es= Executors.newSingleThreadExecutor();
        es.submit(()->producerWorker);
        es.shutdown();


    }
}
