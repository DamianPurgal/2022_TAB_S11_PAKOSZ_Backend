package com.example.skiSlope.repository;

import com.example.skiSlope.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cards")
public interface CardRepository extends CrudRepository<Card, Long>,
        PagingAndSortingRepository<Card, Long>,
        JpaRepository<Card, Long> {

    <Optional>Card findCardByUserId(Long userId);

    List<Card> findCardsByUserId(Long userId);
}
