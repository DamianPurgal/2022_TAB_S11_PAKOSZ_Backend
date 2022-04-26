package com.example.skiSlope.repository;

import com.example.skiSlope.model.SkiLift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiLiftRepository extends CrudRepository<SkiLift, Long>,
        PagingAndSortingRepository<SkiLift, Long>,
        JpaRepository<SkiLift, Long> {
}
