package com.markot.astoninsuranceserver.repository;

import com.markot.astoninsuranceserver.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Martin Kotulac
 * on 31/03/2022
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
