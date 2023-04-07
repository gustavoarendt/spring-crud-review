package crud.review.api.controller.dto.medic;

import crud.review.api.controller.dto.address.CreateAddress;
import crud.review.api.model.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record CreateMedic(
        @NotEmpty
        String name,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        CreateAddress address) {
}
