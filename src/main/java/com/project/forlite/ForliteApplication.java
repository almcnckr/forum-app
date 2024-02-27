package com.project.forlite;

import com.project.forlite.core.utilities.exceptions.BusinessException;
import com.project.forlite.core.utilities.exceptions.ExceptionDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@SpringBootApplication
public class ForliteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForliteApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleBusinessException(BusinessException businessException) {
        return new ExceptionDetails(businessException.getMessage());
    }
}
