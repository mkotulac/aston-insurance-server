package com.markot.astoninsuranceserver.exception;

import com.markot.astoninsuranceserver.model.InsuranceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Insurance rate not found")
@Slf4j
public class InsuranceRateNotFoundException extends RuntimeException {
    public InsuranceRateNotFoundException(String insurancePackage, InsuranceType insuranceType) {
        super();
        log.error("Cannot find insurance rate for insurancePackage: {}, insuranceType: {}", insurancePackage, insuranceType.name());
    }


}
