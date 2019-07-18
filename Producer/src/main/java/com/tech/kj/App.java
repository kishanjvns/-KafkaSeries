package com.tech.kj;

import com.tech.kj.kafka.config.ProducerConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ProducerConfig config=new ProducerConfig();
    	ProducerConfig.createProducer();
    	config.sendMessage("producer1", "Kishan Producer Working");
        System.out.println( "Hello World!" );
    }
}
