package crud.review.api.controller.dto.appointment;

import crud.review.api.model.enums.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentInputModel(
        @NotNull
        Long patientId,
        Long medicId,
        @NotNull
        @Future
        LocalDateTime dateTime,
        Specialty specialty
) {
}
