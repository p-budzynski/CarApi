package pl.kurs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.validation.Create;
import pl.kurs.validation.Update;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EngineDto {

    private Long id;

    @NotBlank(message = "Producer is required", groups = Create.class)
    private String producer;

    @NotBlank(message = "Model is required", groups = Create.class)
    private String model;

    @NotNull(message = "Capacity is required", groups = Create.class)
    @Positive(message = "Capacity must be greater than 0", groups = {Create.class, Update.class})
    private Integer capacity;

    @NotNull(message = "Horsepower is required", groups = Create.class)
    @Positive(message = "Horsepower must be greater than 0", groups = {Create.class, Update.class})
    private Integer horsepower;

    @NotNull(message = "Torque is required", groups = Create.class)
    @Positive(message = "Torque must be greater than 0", groups = {Create.class, Update.class})
    private Integer torque;

    @NotBlank(message = "Engine number is required", groups = Create.class)
    private String engineNumber;

}
