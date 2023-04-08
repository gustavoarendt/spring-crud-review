package crud.review.api.repository;

import crud.review.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByMedicIdAndDateTime(Long medicId, LocalDateTime dateTime);

    Boolean existsByPatientIdAndDateTimeBetween(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);
}
