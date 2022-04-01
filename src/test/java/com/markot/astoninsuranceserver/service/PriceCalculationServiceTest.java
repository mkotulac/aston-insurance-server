package com.markot.astoninsuranceserver.service;

import com.markot.astoninsuranceserver.model.dto.InsuranceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Martin Kotulac
 * on 01/04/2022
 */
@SpringBootTest
class PriceCalculationServiceTest {

    @Autowired
    PriceCalculationService priceCalculationService;

    @Test
    void testCalculatePrice_YearRoundPeriod() {
        InsuranceDto dto = InsuranceDto.builder()
                .additionalInsurance(List.of("SPORT", "STORNO"))
                .insurancePackage("EXTRA")
                .insuranceType("YEAR_ROUND")
                .numberOfPeople(2)
                .startDate(LocalDate.now())
                .build();

        BigDecimal price = priceCalculationService.calculatePrice(dto);

        assertThat(price).isEqualTo(new BigDecimal("153.40"));
    }

    @Test
    void testCalculatePrice_ShortTermPeriod() {
        InsuranceDto dto = InsuranceDto.builder()
                .additionalInsurance(List.of("SPORT", "STORNO"))
                .insurancePackage("EXTRA")
                .insuranceType("SHORT_TERM")
                .numberOfPeople(2)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        BigDecimal price = priceCalculationService.calculatePrice(dto);

        assertThat(price).isEqualTo(new BigDecimal("51.84"));
    }
}
