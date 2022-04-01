package com.markot.astoninsuranceserver.controller;

import com.markot.astoninsuranceserver.model.Insurance;
import com.markot.astoninsuranceserver.model.dto.InitialDataDto;
import com.markot.astoninsuranceserver.model.dto.InsuranceDto;
import com.markot.astoninsuranceserver.service.InsuranceDataService;
import com.markot.astoninsuranceserver.service.PriceCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@RestController
@RequestMapping("/insurance")
@AllArgsConstructor
public class InsuranceController {

    private final PriceCalculationService priceCalculationService;
    private final InsuranceDataService insuranceDataService;

    @GetMapping
    public InitialDataDto readInitialData() {
        return insuranceDataService.readInitialData();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String calculateInsurancePrice(@RequestBody @Valid InsuranceDto dto) {
        Insurance saved = insuranceDataService.save(dto, priceCalculationService.calculatePrice(dto));

        Locale skLocale = new Locale("sk", "SK");

        return NumberFormat.getCurrencyInstance(skLocale).format(saved.getPrice());
    }


}
