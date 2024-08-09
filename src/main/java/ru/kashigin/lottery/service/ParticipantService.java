package ru.kashigin.lottery.service;


import ru.kashigin.lottery.model.Participant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface ParticipantService {
    List<Participant> getAllParticipants();
    void deleteAll();
    void save(Participant participant);
    int randomGenerator(int size) throws URISyntaxException, IOException, InterruptedException;
}
