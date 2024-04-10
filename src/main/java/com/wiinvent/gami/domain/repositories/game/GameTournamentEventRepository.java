package com.wiinvent.gami.domain.repositories.game;

import com.wiinvent.gami.domain.entities.game.GameTournamentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameTournamentEventRepository extends JpaRepository<GameTournamentEvent, String> {
  List<GameTournamentEvent> findByGameTournamentIdAndStartTimeLessThanEqual(Integer gameId, LocalDateTime startAt);

  GameTournamentEvent findByGameTournamentIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer gameId, LocalDateTime startAt, LocalDateTime endAt);
}