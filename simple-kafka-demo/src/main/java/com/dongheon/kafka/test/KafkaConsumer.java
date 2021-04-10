package com.dongheon.kafka.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "exam", groupId = "foo")
	public void consume(String message) {
		System.out.println(String.format("Consumed message : %s", message));
	}
}