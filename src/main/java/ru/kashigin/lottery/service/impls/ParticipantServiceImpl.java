package ru.kashigin.lottery.service.impls;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.kashigin.lottery.model.Participant;
import ru.kashigin.lottery.repository.ParticipantRepository;
import ru.kashigin.lottery.service.ParticipantService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    @Override
    public int randomGenerator(int size) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String httpUrl = "https://www.random.org/integers/?num=1&min=1&max=" + size + "&col=1&base=10&format=plain&rnd=new";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(httpUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body().trim();
        return Integer.parseInt(responseBody);

    }
}
