package com.example.demo.util.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.CompanyDto;
import com.example.demo.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListener {

    private ObjectMapper objectMapper;

    @Autowired
    private CompanyService companyService;


    @org.springframework.kafka.annotation.KafkaListener(
        topics = {"${listener.kafka.demo.topic}"},
        autoStartup = "false",
        clientIdPrefix = "default",
        groupId = "group-id"
    )
    private void onEvent(String evenMessage) throws JsonProcessingException, JsonMappingException{
        log.info("[KafkaListener] Event message received: {}",evenMessage);
        CompanyDto companyDto = objectMapper.readValue(evenMessage, CompanyDto.class);
        companyService.insertCompany(companyDto);
    }
    
}
