package com.markot.astoninsuranceserver.repository;

import com.markot.astoninsuranceserver.model.InsuranceRate;
import com.markot.astoninsuranceserver.model.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Repository
public interface InsuranceRateRepository extends JpaRepository<InsuranceRate, Long> {
    Optional<InsuranceRate> findOneByPackageNameAndInsuranceType(String insurancePackage, InsuranceType insuranceType);

    @Query(value = "SELECT DISTINCT package_name FROM insurance_rate", nativeQuery = true)
    List<String> findDistinctPackageNames();

    List<InsuranceRate> findDistinctByPackageNameIn(List<String> packages);
}
