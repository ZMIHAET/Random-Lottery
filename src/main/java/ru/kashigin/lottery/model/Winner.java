package ru.kashigin.lottery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "winner")
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "City should not be empty")
    @Size(min = 2, max = 200, message = "City should be between 2 and 30 characters")
    private String city;

    private int prize;
}
