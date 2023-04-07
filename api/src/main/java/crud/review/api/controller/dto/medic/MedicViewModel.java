package crud.review.api.controller.dto.medic;

import crud.review.api.model.Medic;
import crud.review.api.model.enums.Specialty;

public record MedicViewModel(Long id, String name, String email, String crm, Specialty specialty) {
    public MedicViewModel(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
