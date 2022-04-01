package com.markot.astoninsuranceserver.service;

import com.markot.astoninsuranceserver.configuration.AdditionalInsuranceConfig;
import com.markot.astoninsuranceserver.exception.InsuranceRateNotFoundException;
import com.markot.astoninsuranceserver.model.InsuranceRate;
import com.markot.astoninsuranceserver.model.InsuranceType;
import com.markot.astoninsuranceserver.model.dto.InsuranceDto;
import com.markot.astoninsuranceserver.repository.InsuranceRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Service
@AllArgsConstructor
public class PriceCalculationService {

    private final InsuranceRateRepository insuranceRateRepository;
    private final AdditionalInsuranceConfig additionalInsuranceConfig;

    public BigDecimal calculatePrice(InsuranceDto dto) {
        String insurancePackage = dto.getInsurancePackage();
        InsuranceType insuranceType = Enum.valueOf(InsuranceType.class, dto.getInsuranceType());
        List<String> additionalInsurance = dto.getAdditionalInsurance();
        Integer numberOfPeople = dto.getNumberOfPeople();
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();

        Long periodMultiplier = getPeriodMultiplier(insuranceType, startDate, endDate);
        InsuranceRate insuranceRate = insuranceRateRepository.findOneByPackageNameAndInsuranceType(insurancePackage, insuranceType)
                .orElseThrow(() -> new InsuranceRateNotFoundException(insurancePackage, insuranceType));
        BigDecimal additionalInsuranceRate = getAdditionalInsuranceRate(additionalInsurance, insuranceType);

        return  BigDecimal.valueOf(periodMultiplier)
                .multiply(insuranceRate.getRate())
                .multiply(additionalInsuranceRate)
                .multiply(BigDecimal.valueOf(numberOfPeople))
                .setScale(2, RoundingMode.UP);
    }

    private BigDecimal getAdditionalInsuranceRate(List<String> additionalInsurance, InsuranceType insuranceType) {
        BigDecimal additionalInsuranceRate = BigDecimal.ONE;

        for (String insurance : additionalInsurance) {
            switch (insurance.toUpperCase()) {
                case "SPORT":
                    BigDecimal sportRate;

                    switch (insuranceType) {
                        case SHORT_TERM:
                            sportRate = additionalInsuranceConfig.getSportShortTerm();
                            break;
                        case YEAR_ROUND:
                            sportRate = additionalInsuranceConfig.getSportYearRound();
                            break;
                        default:
                            throw new UnsupportedOperationException("Unknown insurance type");
                    }

                    sportRate = sportRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.UP);
                    additionalInsuranceRate = additionalInsuranceRate.add(sportRate);
                    break;

                case "STORNO":
                    BigDecimal stornoRate;

                    switch (insuranceType) {
                        case SHORT_TERM:
                            stornoRate = additionalInsuranceConfig.getStornoShortTerm();
                            break;
                        case YEAR_ROUND:
                            stornoRate = additionalInsuranceConfig.getStornoYearRound();
                            break;
                        default:
                            throw new UnsupportedOperationException("Unknown insurance type");
                    }

                    stornoRate = stornoRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.UP);
                    additionalInsuranceRate = additionalInsuranceRate.add(stornoRate);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown additional insurance type");
            }
        }

        return additionalInsuranceRate;
    }

    private Long getPeriodMultiplier(InsuranceType insuranceType, LocalDate startDate, LocalDate endDate) {
        switch (insuranceType) {
            case SHORT_TERM:
                return ChronoUnit.DAYS.between(startDate, endDate) + 1;
            case YEAR_ROUND:
                return 1L;
            default:
                throw new UnsupportedOperationException("Unknown insurance type");
        }
    }
}
