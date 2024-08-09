package ru.kashigin.lottery.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kashigin.lottery.model.Participant;
import ru.kashigin.lottery.model.Winner;
import ru.kashigin.lottery.service.ParticipantService;
import ru.kashigin.lottery.service.WinnerService;
import ru.kashigin.lottery.util.NotCreated.ParticipantErrorResponse;
import ru.kashigin.lottery.util.NotCreated.ParticipantNotCreatedException;
import ru.kashigin.lottery.util.NotEnough.NotEnoughParticipantsException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/lottery")
public class LotteryController {
    private final ParticipantService participantService;
    private final WinnerService winnerService;
    Random random = new Random();

    @Autowired
    public LotteryController(ParticipantService participantService, WinnerService winnerService) {
        this.participantService = participantService;
        this.winnerService = winnerService;
    }

    @PostMapping("/participant")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Participant participant,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new ParticipantNotCreatedException(errorMsg.toString());
        }
        participantService.save(participant);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/participant")
    public List<Participant> getParticipants(){
        return participantService.getAllParticipants();
    }

    @GetMapping("/start")
    public String randomLottery(Model model) throws URISyntaxException, IOException, InterruptedException {
        if (participantService.getAllParticipants().size() < 2)
            throw new NotEnoughParticipantsException("Недостаточно участников для проведения лотереи");

        int prize = participantService.randomGenerator(1000);

        List<Participant> participants = participantService.getAllParticipants();

        int randomIndex = participantService.randomGenerator(participants.size());

        Participant winnerParticipant = participants.get(randomIndex - 1);

        Winner winner = new Winner();
        winner.setName(winnerParticipant.getName());
        winner.setAge(winnerParticipant.getAge());
        winner.setCity(winnerParticipant.getCity());
        winner.setPrize(prize);

        winnerService.save(winner);

        model.addAttribute("winner", winnerParticipant);
        model.addAttribute("prize", prize);


        participantService.deleteAll();
        return "/winner";
    }

    @GetMapping("/winners")
    public String showWinners(Model model){
        model.addAttribute("winners", winnerService.getAllWinners());
        return "/winners";
    }

    @ExceptionHandler
    private ResponseEntity<ParticipantErrorResponse> handleException(ParticipantNotCreatedException e){
        ParticipantErrorResponse response = new ParticipantErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ParticipantErrorResponse> handleException(NotEnoughParticipantsException e){
        ParticipantErrorResponse response = new ParticipantErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}