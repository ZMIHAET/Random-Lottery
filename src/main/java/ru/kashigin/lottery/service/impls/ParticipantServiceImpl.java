package ru.kashigin.lottery.service.impls;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.kashigin.lottery.model.Participant;
import ru.kashigin.lottery.repository.ParticipantRepository;
import ru.kashigin.lottery.service.ParticipantService;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public void deleteAll() {
        participantRepository.deleteAll();
    }

    @Override
    @Transactional
    public void save(Participant participant){
        participantRepository.save(participant);
    }
}
