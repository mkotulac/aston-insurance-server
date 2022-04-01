package com.markot.astoninsuranceserver.controller;

import com.markot.astoninsuranceserver.model.Insurance;
import com.markot.astoninsuranceserver.model.InsuranceType;
import com.markot.astoninsuranceserver.model.dto.InsuranceDto;
import com.markot.astoninsuranceserver.repository.InsuranceRepository;
import com.markot.astoninsuranceserver.service.InsuranceDataService;
import com.markot.astoninsuranceserver.service.PriceCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Martin Kotulac
 * on 01/04/2022
 */

@ExtendWith(MockitoExtension.class)
class InsuranceControllerTest {

    @Mock
    InsuranceRepository insuranceRepository;

    @Mock
    PriceCalculationService priceCalculationService;

    @Test
    public void testCalculateInsurancePrice() {
        InsuranceDataService insuranceDataService = new InsuranceDataService(insuranceRepository, null, null);
        InsuranceController insuranceController = new InsuranceController(priceCalculationService, insuranceDataService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Insurance insurance = Insurance.builder()
                .id(1L)
                .insuranceType(InsuranceType.YEAR_ROUND)
                .sportInsurance(true)
                .stornoInsurance(true)
                .price(BigDecimal.ONE)
                .numberOfPeople(2)
                .insurancePackage("EXTRA")
                .build();

        InsuranceDto dto = InsuranceDto.builder()
                .additionalInsurance(List.of("SPORT", "STORNO"))
                .insurancePackage("EXTRA")
                .insuranceType("YEAR_ROUND")
                .numberOfPeople(2)
                .startDate(LocalDate.now())
                .build();

        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);

        String price = insuranceController.calculateInsurancePrice(dto);

        assertThat(price).startsWith("1,00");
    }
}
