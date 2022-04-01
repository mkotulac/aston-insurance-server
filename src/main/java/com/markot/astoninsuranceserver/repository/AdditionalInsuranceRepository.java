package com.markot.astoninsuranceserver.repository;

import com.markot.astoninsuranceserver.model.AdditionalInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Repository
public interface AdditionalInsuranceRepository extends JpaRepository<AdditionalInsurance, Long> {

}
