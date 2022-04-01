package com.markot.astoninsuranceserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Validated
@Builder
@AllArgsConstructor
public class InsuranceDto {

    @NotNull
    private String insuranceType;

    @NotNull
    private String insurancePackage;

    @NotNull
    private List<String> additionalInsurance;

    @NotNull
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer numberOfPeople;
}
