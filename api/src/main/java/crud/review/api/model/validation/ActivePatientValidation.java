package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import crud.review.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements AppointmentScheduleValidation {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentInputModel input) {
        if (input.patientId() == null) {
            return;
        }

        var activePatient = patientRepository.findActiveById(input.patientId());
        if (!activePatient) {
            throw new AppointmentValidationException("Patient is no longer active.");
        }
    }
}
