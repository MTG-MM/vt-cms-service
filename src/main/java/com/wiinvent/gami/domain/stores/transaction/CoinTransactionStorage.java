package com.wiinvent.gami.domain.stores.transaction;

import com.wiinvent.gami.domain.entities.transaction.CoinTransaction;
import com.wiinvent.gami.domain.entities.user.GameTournamentUser;
import com.wiinvent.gami.domain.response.type.CursorType;
import com.wiinvent.gami.domain.stores.BaseStorage;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CoinTransactionStorage extends BaseStorage {
  public List<CoinTransaction> findAll(UUID userId, Long next, Long pre, int limit, CursorType type) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<CoinTransaction> query = criteriaBuilder.createQuery(CoinTransaction.class);
    Root<CoinTransaction> root = query.from(CoinTransaction.class);
    List<Predicate> conditionList = new ArrayList<>();
    conditionList.add(criteriaBuilder.equal(root.get("userId"), userId));
    conditionList.add(criteriaBuilder.and(criteriaBuilder.greaterThan(root.get("createdAt"), pre),
        criteriaBuilder.lessThan(root.get("createdAt"), next)));
    if (type == CursorType.NEXT || type == CursorType.FIRST) {
      query.where(criteriaBuilder.and(conditionList.toArray(new Predicate[0])))
          .orderBy(criteriaBuilder.desc(root.get("createdAt")));
    } else if (type == CursorType.PRE) {
      query.where(criteriaBuilder.and(conditionList.toArray(new Predicate[0])))
          .orderBy(criteriaBuilder.asc(root.get("createdAt")));
    }
    TypedQuery<CoinTransaction> typedQuery = entityManager.createQuery(query);
    typedQuery.setMaxResults(limit);
    return typedQuery.getResultList();
  }
}
