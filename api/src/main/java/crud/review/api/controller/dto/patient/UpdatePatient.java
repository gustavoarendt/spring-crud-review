package crud.review.api.controller.dto.patient;

import crud.review.api.controller.dto.address.CreateAddress;
import jakarta.validation.constraints.NotNull;

public record UpdatePatient(@NotNull Long id, String name, String phone, CreateAddress address) {
}
