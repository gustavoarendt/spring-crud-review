package crud.review.api.controller.dto.patient;

import crud.review.api.model.Patient;

public record PatientViewModel(Long id, String name, String email, String cpf) {
    public PatientViewModel(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
