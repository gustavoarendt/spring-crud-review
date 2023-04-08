package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import crud.review.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailableMedicValidation implements AppointmentScheduleValidation {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentInputModel input) {
        var medicAlreadyHasAppointmentAtThatTime = repository.existsByMedicIdAndDateTime(input.medicId(), input.dateTime());
        if (medicAlreadyHasAppointmentAtThatTime) {
            throw new AppointmentValidationException("The chosen medic is already busy at that day and time");
        }
    }
}
