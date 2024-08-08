package ru.kashigin.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kashigin.lottery.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
