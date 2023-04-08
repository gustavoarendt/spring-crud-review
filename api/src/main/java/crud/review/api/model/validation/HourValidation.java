package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.model.exception.AppointmentValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HourValidation implements AppointmentScheduleValidation {

    public void validate(AppointmentInputModel input) {
        var appointmentDate = input.dateTime();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpen = appointmentDate.getHour() < 7;
        var afterClose = appointmentDate.getHour() > 18;

        if (sunday || beforeOpen || afterClose) {
            throw new AppointmentValidationException("Can't schedule appointment out of working hours");
        }
    }
}
