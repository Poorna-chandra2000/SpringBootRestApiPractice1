package com.practice3.practice3.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Persondto {

    Long id;

    @NotNull//if annotation must work thn add @Valid for @RequestBody// while creating or updating
     @Size(min = 6,max = 20,message = "Enter Valid name")
    String name;

    Long age;
}
