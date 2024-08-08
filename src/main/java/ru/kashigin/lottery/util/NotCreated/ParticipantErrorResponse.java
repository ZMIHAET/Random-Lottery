package ru.kashigin.lottery.util.NotCreated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantErrorResponse {
    private String message;
    private long timestamp;
}
