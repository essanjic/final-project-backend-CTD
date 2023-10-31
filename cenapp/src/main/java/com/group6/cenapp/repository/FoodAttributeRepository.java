package com.group6.cenapp.repository;

import com.group6.cenapp.model.FoodAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodAttributeRepository extends JpaRepository<FoodAttribute, Integer> {
    @Query(value = "SELECT S.* FROM spec S WHERE S.icon = ?1", nativeQuery = true)
    Optional<FoodAttribute> getByIcon(String icon);
}
