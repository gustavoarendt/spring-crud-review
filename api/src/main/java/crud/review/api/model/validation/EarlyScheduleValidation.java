package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EarlyScheduleValidation implements AppointmentScheduleValidation {

    public void validate(AppointmentInputModel input) {
        var appointmentDate = input.dateTime();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new AppointmentValidationException("Appointment must be schedule at least 30min before.");
        }
    }
}
