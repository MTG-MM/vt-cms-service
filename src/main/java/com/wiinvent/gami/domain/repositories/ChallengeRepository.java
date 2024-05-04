package com.wiinvent.gami.domain.repositories;

import com.wiinvent.gami.domain.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer>, JpaSpecificationExecutor<Challenge> {

}