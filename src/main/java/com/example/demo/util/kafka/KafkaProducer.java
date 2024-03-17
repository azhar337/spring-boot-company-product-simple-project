package com.example.demo.util.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.util.common.JsonUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //send insert notifcation
    @Async
    public void send(String topic, String key, Object payload){
        try {
            String data = JsonUtil.serializeObject(payload);
            log.info("[KafkaProducer] send : creating topic:{}, message:{}", topic, data);
            kafkaTemplate.send(topic, key, data);
            
        } catch (Exception e) {
            log.error("[KafkaProducer] send kafka error:{}", e);
            throw e;
        }

    }
    
}
