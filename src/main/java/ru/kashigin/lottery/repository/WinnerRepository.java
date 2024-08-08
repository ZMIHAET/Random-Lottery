package ru.kashigin.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kashigin.lottery.model.Winner;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
