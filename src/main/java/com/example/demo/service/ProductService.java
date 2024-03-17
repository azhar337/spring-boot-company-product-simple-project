package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorCodeEnum;
import com.example.demo.model.dao.ProductModel;
import com.example.demo.model.dto.request.ProductRequestInsert;
import com.example.demo.model.dto.response.ProductResponse;
import com.example.demo.model.dto.response.ProductResultResponse;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.common.BaseResponse;
import com.example.demo.util.common.RefNoGenerator;
import com.example.demo.util.kafka.KafkaProducer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProductService {


    @Autowired
    @Qualifier("taskExecutor_")
    private TaskExecutor tExecutor;


    @Autowired
    KafkaProducer kafkaProducer;


    @Autowired
    private ProductRepository productRepository;

    @Value("${producer.kafka.demo.topic}")
    private String producerTopic;

    public BaseResponse<ProductResponse> insertData(ProductRequestInsert produceRequest){
        try {
            ProductModel productModel = new ProductModel();
            productModel.setPrice(produceRequest.getPrice());
            productModel.setQuantity(produceRequest.getQuantity());
            productModel.setTitle(produceRequest.getTitle());
            productModel.setRefNo(RefNoGenerator.generateRefNo());

            log.info("[ProductService] insertData inserting productModel:{}", productModel);
            productRepository.save(productModel);
            //send insert notifcation to user
            tExecutor.execute(()->kafkaProducer.send(producerTopic, "group-id", productModel));
             
            ProductResponse productResponse = new ProductResponse();
            productResponse.setRefNo(productModel.getRefNo());
            return BaseResponse.success(productResponse);
            
        } catch (Exception e) {
            log.error("[ProductService] insertData error inserting ERROR:{}", e);
            return BaseResponse.fail(ErrorCodeEnum.ERR_FAIL_INSERT.getCode(), ErrorCodeEnum.ERR_FAIL_INSERT.getMessage());
        }

    }

    public BaseResponse<ProductResultResponse> getAllData(String refNo){
        try {

        ProductModel productModel = productRepository.findByRefNo(refNo);
        ProductResultResponse productResultResponse = new ProductResultResponse();
        productResultResponse.setPrice(productModel.getPrice());
        productResultResponse.setQuantity(productModel.getQuantity());
        productResultResponse.setTitle(productModel.getTitle());
        return BaseResponse.success(productResultResponse);

    } catch (Exception e) {
        log.error("[ProductService] getAllData error retrieving ERROR:{}", e);
       return BaseResponse.fail(ErrorCodeEnum.ERR_FAIL_RETRIEVE.getCode(),ErrorCodeEnum.ERR_FAIL_RETRIEVE.getMessage());
    }
    }

    public  BaseResponse<ProductResponse> updateData(String refNo, ProductRequestInsert productRequestInsert){
        try {

        ProductModel productModel = productRepository.findByRefNo(refNo);
        productModel.setPrice(productRequestInsert.getPrice());
        productModel.setQuantity(productRequestInsert.getQuantity());
        productModel.setTitle(productRequestInsert.getTitle());

        log.info("[ProductService] updateData updating productModel:{}", productModel);
        productRepository.save(productModel);

        //send update notifcation
        tExecutor.execute(()->kafkaProducer.send(producerTopic, "group-id", productModel));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setRefNo(productModel.getRefNo());
        return BaseResponse.success(productResponse);
    } catch (Exception e) {
        log.error("[ProductService] updateData error inserting ERROR:{}", e);
        return BaseResponse.fail(ErrorCodeEnum.ERR_FAIL_RETRIEVE.getCode(),ErrorCodeEnum.ERR_FAIL_RETRIEVE.getMessage());
    }
    }



    public BaseResponse<ProductResponse> deleteData(String refNo){
        try {

            log.info("[ProductService] deleteData deleting productModel refno:{}", refNo);
        productRepository.deleteByRefNo(refNo);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setRefNo(refNo);
        return BaseResponse.success(productResponse);
        } catch (Exception e) {
            log.error("[ProductService] deleteData error delete ERROR:{}", e);
            return BaseResponse.fail(ErrorCodeEnum.ERR_FAIL_RETRIEVE.getCode(),ErrorCodeEnum.ERR_FAIL_RETRIEVE.getMessage());
        }
    }



    
}
