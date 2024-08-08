package ru.kashigin.lottery.util.NotEnough;

public class NotEnoughParticipantsException extends RuntimeException{
    public NotEnoughParticipantsException(String msg){
        super(msg);
    }
}
