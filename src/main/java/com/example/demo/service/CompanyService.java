package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.CompanyModel;
import com.example.demo.model.dto.CompanyDto;
import com.example.demo.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    //to be inserted via kafka listener
    public void insertCompany(CompanyDto companyDto){
        try {
            CompanyModel companyModel = new CompanyModel();
            companyModel.setEmail(companyDto.getEmail());
            companyModel.setFirstName(companyDto.getFirstName());
            companyModel.setLastName(companyDto.getLastName());
            log.info("[CompanyService] insertCompany saving companyModel:{}", companyModel);
            companyRepository.save(companyModel);
            
        } catch (Exception e) {
            log.error("[CompanyService] insertCompany fail to save companyMode Error: {}", e);
        }
    }
    
}
