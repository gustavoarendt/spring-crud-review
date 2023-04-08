package crud.review.api.controller.dto.appointment;

import crud.review.api.model.enums.CancellationReason;
import jakarta.validation.constraints.NotNull;

public record CancellationInputModel(
        @NotNull Long AppointmentId,
        @NotNull CancellationReason reason
) {
}
