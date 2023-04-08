package crud.review.api.service;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.controller.dto.appointment.AppointmentViewModel;
import crud.review.api.controller.dto.appointment.CancellationInputModel;
import crud.review.api.model.Appointment;
import crud.review.api.model.Medic;
import crud.review.api.model.exception.AppointmentValidationException;
import crud.review.api.model.validation.AppointmentScheduleValidation;
import crud.review.api.repository.AppointmentRepository;
import crud.review.api.repository.MedicRepository;
import crud.review.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    MedicRepository medicRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private List<AppointmentScheduleValidation> validations;

    public AppointmentViewModel schedule(AppointmentInputModel input) {
        if (!patientRepository.existsById(input.patientId())) {
            throw new AppointmentValidationException("Patient with given id doesn't exists.");
        }

        if (input.medicId() != null && !medicRepository.existsById(input.medicId())) {
            throw new AppointmentValidationException("Medic with given id doesn't exists.");
        }

        validations.forEach(v -> v.validate(input));

        var patient = patientRepository.getReferenceById(input.patientId());
        var medic = findMedic(input);
        if (medic == null) {
            throw new AppointmentValidationException("There are no medic available for this date and time");
        }
        var appointment = new Appointment(null, patient, medic, input.dateTime(), null);
        appointmentRepository.save(appointment);

        return new AppointmentViewModel(appointment);
    }

    private Medic findMedic(AppointmentInputModel input) {
        if (input.medicId() != null) {
            return medicRepository.getReferenceById(input.medicId());
        }

        if (input.specialty() == null) {
            throw new AppointmentValidationException("Specialty is required when medic is not chosen.");
        }

        return medicRepository.randomAndAvailableMedic(input.specialty(), input.dateTime());
    }

    public void cancelSchedule(CancellationInputModel cancellation) {
        if (!appointmentRepository.existsById(cancellation.AppointmentId())) {
            throw new AppointmentValidationException("Appointment Id doesn't exists.");
        }

        var appointment = appointmentRepository.getReferenceById(cancellation.AppointmentId());
        appointment.cancel(cancellation.reason());
    }
}
