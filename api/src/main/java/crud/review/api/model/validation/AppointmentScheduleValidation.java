package crud.review.api.model.validation;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;

public interface AppointmentScheduleValidation {

    void validate(AppointmentInputModel input);
}
