package com.platform.rcmd.dao;

import com.ihrm.domain.rcmd.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlanDao extends JpaRepository<Plan,String>, JpaSpecificationExecutor<Plan> {
}
