package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import crud.review.api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicValidation implements AppointmentScheduleValidation {

    @Autowired
    private MedicRepository medicRepository;

    public void validate(AppointmentInputModel input) {
        if (input.medicId() == null) {
            return;
        }

        var activeMedic = medicRepository.findActiveById(input.medicId());
        if (!activeMedic) {
            throw new AppointmentValidationException("Medic is no longer active.");
        }
    }
}
