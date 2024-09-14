package com.practice3.practice3.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//now give table name
@Table(name = "Person")
public class Personentity {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    private Long age;


}
