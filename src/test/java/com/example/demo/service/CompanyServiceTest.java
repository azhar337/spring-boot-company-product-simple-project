package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.demo.model.dao.CompanyModel;
import com.example.demo.model.dto.CompanyDto;
import com.example.demo.repository.CompanyRepository;


@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void testInsertCompany() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setEmail("test@example.com");
        companyDto.setFirstName("John");
        companyDto.setLastName("Doe");
        companyService.insertCompany(companyDto);
        verify(companyRepository, times(1)).save(any(CompanyModel.class));
    }

    //continue writing for all other class and lines
}
