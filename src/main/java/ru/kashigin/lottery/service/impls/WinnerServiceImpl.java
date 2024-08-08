package ru.kashigin.lottery.service.impls;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kashigin.lottery.model.Winner;
import ru.kashigin.lottery.repository.WinnerRepository;
import ru.kashigin.lottery.service.WinnerService;

import java.util.List;

@Service
public class WinnerServiceImpl implements WinnerService {
    private final WinnerRepository winnerRepository;

    public WinnerServiceImpl(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    @Override
    @Transactional
    public void save(Winner winner) {
        winnerRepository.save(winner);
    }

    @Override
    public List<Winner> getAllWinners() {
        return winnerRepository.findAll();
    }
}
