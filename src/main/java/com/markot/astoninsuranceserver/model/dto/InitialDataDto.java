package com.markot.astoninsuranceserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * Created by Martin Kotulac
 * on 01/04/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class InitialDataDto {
    private Set<OptionItem> insuranceTypeOptions;
    private Set<OptionItem> insurancePackageOptions;
    private Set<OptionItem> additionalInsuranceOptions;
}
