package crud.review.api.controller;

import crud.review.api.controller.dto.patient.CreatePatient;
import crud.review.api.controller.dto.patient.PatientViewModel;
import crud.review.api.controller.dto.patient.UpdatePatient;
import crud.review.api.model.Patient;
import crud.review.api.repository.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createPatient(@RequestBody @Valid CreatePatient patientDto, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(patientDto);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientViewModel(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientViewModel>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(PatientViewModel::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientViewModel> updatePatient(@RequestBody @Valid UpdatePatient patientDto) {
        var patient = repository.getReferenceById(patientDto.id());
        patient.updatePatient(patientDto);

        return ResponseEntity.ok(new PatientViewModel(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.disable();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientViewModel> getDetailed(@PathVariable Long id){
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientViewModel(patient));
    }
}
