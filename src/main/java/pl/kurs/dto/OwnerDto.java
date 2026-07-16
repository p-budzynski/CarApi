package pl.kurs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.kurs.validation.Create;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerDto {

    private Long id;

    @NotBlank(message = "First name is required", groups = Create.class)
    private String firstName;

    @NotBlank(message = "Last name is required", groups = Create.class)
    private String lastName;

    @NotBlank(message = "Phone number is required", groups = Create.class)
    private String phoneNumber;

    @Email(message = "Email must be valid", groups = Create.class)
    @NotBlank(message = "E-mail is required", groups = Create.class)
    private String email;

    @PESEL(message = "PESEL must be valid", groups = Create.class)
    @NotBlank(message = "PESEL is required", groups = Create.class)
    private String pesel;

}
