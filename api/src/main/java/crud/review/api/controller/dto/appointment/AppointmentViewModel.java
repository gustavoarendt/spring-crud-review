package crud.review.api.controller.dto.appointment;

import crud.review.api.model.Appointment;

import java.time.LocalDateTime;

public record AppointmentViewModel(Long id, Long patientId, Long medicId, LocalDateTime date) {

    public AppointmentViewModel(Appointment appointment) {
        this(appointment.getId(), appointment.getPatient().getId(), appointment.getMedic().getId(), appointment.getDateTime());
    }
}
