package ru.kashigin.lottery.service;

import ru.kashigin.lottery.model.Winner;

import java.util.List;

public interface WinnerService {
    void save(Winner winner);
    List<Winner> getAllWinners();
}
