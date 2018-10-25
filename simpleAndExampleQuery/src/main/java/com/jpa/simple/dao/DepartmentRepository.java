package com.jpa.simple.dao;

import com.jpa.simple.entity.SenyiDeptmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 因为继承了JpaRepository，因此会被spring框架自动注入到容器中，因此不需要实现类
 */
public interface DepartmentRepository extends JpaRepository<SenyiDeptmentEntity,Integer> {
}
