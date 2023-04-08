package crud.review.api.controller;

import crud.review.api.controller.dto.appointment.AppointmentInputModel;
import crud.review.api.controller.dto.appointment.AppointmentViewModel;
import crud.review.api.controller.dto.appointment.CancellationInputModel;
import crud.review.api.repository.MedicRepository;
import crud.review.api.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentInputModel appointmentDto) {
        var dto = appointmentService.schedule(appointmentDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelAppointment(@RequestBody @Valid CancellationInputModel cancellation) {
        appointmentService.cancelSchedule(cancellation);
        return ResponseEntity.noContent().build();
    }
}
