package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import crud.review.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientOnlyHasOneAppointmentPerDayValidation implements AppointmentScheduleValidation {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentInputModel input) {
        var firstHour = input.dateTime().withHour(7);
        var lastHour = input.dateTime().withHour(18);
        var patientAlreadyHasAppointmentForThatDay = repository.existsByPatientIdAndDateTimeBetween(input.patientId(), firstHour, lastHour);
        if (patientAlreadyHasAppointmentForThatDay) {
            throw new AppointmentValidationException("Patient already has an appointment for that day");
        }
    }
}
