package com.markot.astoninsuranceserver.service;

import com.markot.astoninsuranceserver.model.Insurance;
import com.markot.astoninsuranceserver.model.InsuranceType;
import com.markot.astoninsuranceserver.model.dto.InitialDataDto;
import com.markot.astoninsuranceserver.model.dto.InsuranceDto;
import com.markot.astoninsuranceserver.model.dto.OptionItem;
import com.markot.astoninsuranceserver.repository.AdditionalInsuranceRepository;
import com.markot.astoninsuranceserver.repository.InsuranceRateRepository;
import com.markot.astoninsuranceserver.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceDataService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceRateRepository insuranceRateRepository;
    private final AdditionalInsuranceRepository additionalInsuranceRepository;

    public InitialDataDto readInitialData() {
        log.debug("Read initial data");

        Set<OptionItem> insuranceTypeOptions = Arrays.stream(InsuranceType.values())
                .map(insuranceType -> new OptionItem(insuranceType.name(), insuranceType.getDescripiton()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<OptionItem> insurancePackageOptions = insuranceRateRepository
                .findDistinctByPackageNameIn(insuranceRateRepository.findDistinctPackageNames())
                .stream()
                .map(insuranceRate -> new OptionItem(insuranceRate.getPackageName(), insuranceRate.getDescription()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<OptionItem> additionalInsuranceOptions = additionalInsuranceRepository.findAll()
                .stream()
                .map(additionalInsurance -> new OptionItem(additionalInsurance.getName(), additionalInsurance.getDescription()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        log.debug("Read initial data finished");

        return new InitialDataDto(insuranceTypeOptions, insurancePackageOptions, additionalInsuranceOptions);
    }

    public final Insurance save(@Valid InsuranceDto dto, @NotNull BigDecimal price) {
        log.debug("Save data");

        Insurance insurance = Insurance.builder()
                .insuranceType(InsuranceType.valueOf(dto.getInsuranceType()))
                .insurancePackage(dto.getInsurancePackage())
                .stornoInsurance(dto.getAdditionalInsurance().contains("STORNO"))
                .sportInsurance(dto.getAdditionalInsurance().contains("SPORT"))
                .startDate(dto.getStartDate().atStartOfDay())
                .endDate(dto.getEndDate() == null ? null : dto.getEndDate().atStartOfDay())
                .numberOfPeople(dto.getNumberOfPeople())
                .price(price)
                .build();

        log.debug("Save data finished");

        return insuranceRepository.save(insurance);
    }

}
