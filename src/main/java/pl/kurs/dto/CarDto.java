package pl.kurs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kurs.validation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    private Long id;

    @NoAudi(groups = {Create.class, Update.class})
    @ValidCarBrand(groups = {Create.class, Update.class})
    @NotBlank(message = "Producer is required", groups = Create.class)
    private String producer;

    @NotBlank(message = "Model is required", groups = Create.class)
    private String model;

    @NotNull(message = "Year of production is required", groups = Create.class)
    @YearInRange(groups = {Create.class, Update.class})
    private Integer yearOfProduction;

    @NotBlank(message = "Registration number is required", groups = Create.class)
    @ValidPolishPlate(groups = {Create.class, Update.class})
    @UniqueRegistrationNumber(groups = {Create.class, Update.class})
    private String registrationNumber;

    @NotBlank(message = "VIN number is required", groups = Create.class)
    @VinNumber(groups = {Create.class, Update.class})
    @UniqueVinNumber(groups = {Create.class, Update.class})
    private String vinNumber;

    @NotBlank(message = "Color is required", groups = Create.class)
    private String color;

    @NotNull(message = "Engine ID is required", groups = Create.class)
    @Positive(message = "Engine ID must be greater than 0", groups = {Create.class, Update.class})
    private Long engineId;

}