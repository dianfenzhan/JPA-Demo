package com.jpa.criteria.dao;

/**
 * Dao层
 */

import com.jpa.criteria.entity.SenyiDeptmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DepartmentRepository extends JpaRepository<SenyiDeptmentEntity,Integer>,JpaSpecificationExecutor {
}

