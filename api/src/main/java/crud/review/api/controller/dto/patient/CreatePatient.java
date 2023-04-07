package crud.review.api.controller.dto.patient;

import crud.review.api.controller.dto.address.CreateAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record CreatePatient(
        @NotEmpty
        String name,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        @Valid
        CreateAddress address) {
}
