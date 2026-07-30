package pl.kurs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRequestDto {

        private String producer;

        private String model;

        private Integer yearOfProduction;

        private String registrationNumber;

        private String vinNumber;

        private String color;

        private EngineDto engine;
}
