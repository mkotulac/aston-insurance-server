package com.markot.astoninsuranceserver.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Configuration
@ConfigurationProperties(prefix = "additional-insurance")
@NoArgsConstructor
@Getter
@Setter
@Validated
public class AdditionalInsuranceConfig {
    @NotNull
    private BigDecimal stornoShortTerm;

    @NotNull
    private BigDecimal sportShortTerm;

    @NotNull
    private BigDecimal stornoYearRound;

    @NotNull
    private BigDecimal sportYearRound;
}
