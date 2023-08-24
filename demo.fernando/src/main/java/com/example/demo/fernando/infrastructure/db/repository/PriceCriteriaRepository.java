package com.example.demo.fernando.infrastructure.db.repository;

import com.example.demo.fernando.infrastructure.db.entity.PriceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceCriteriaRepository {

    private final EntityManager entityManager;

    @Transactional
    public List<PriceEntity> getPricesOfDemandDate(LocalDateTime demandDate, Long productId, Long brandId) {

        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<PriceEntity> criteriaQuery = criteria.createQuery(PriceEntity.class);
        Root<PriceEntity> root = criteriaQuery.from(PriceEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteria.lessThanOrEqualTo(root.get("startDate"), demandDate));
        predicates.add(criteria.greaterThanOrEqualTo(root.get("endDate"), demandDate));
        predicates.add(criteria.equal(root.get("brandId"), brandId));
        predicates.add(criteria.equal(root.get("productId"), productId));
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}