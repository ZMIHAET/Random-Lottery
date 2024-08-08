package ru.kashigin.lottery.service;


import ru.kashigin.lottery.model.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getAllParticipants();
    void deleteAll();
    void save(Participant participant);
}
