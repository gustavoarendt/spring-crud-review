package crud.review.api.controller.dto.medic;

import crud.review.api.controller.dto.address.CreateAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateMedic(@NotNull Long id, String name, String phone, CreateAddress address) {
}
