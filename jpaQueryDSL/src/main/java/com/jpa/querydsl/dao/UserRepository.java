package com.jpa.querydsl.dao;

import com.jpa.querydsl.entity.SenyiUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<SenyiUserEntity,Integer>,QuerydslPredicateExecutor<SenyiUserEntity> {
}
