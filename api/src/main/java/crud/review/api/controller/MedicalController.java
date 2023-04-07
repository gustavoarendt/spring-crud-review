package crud.review.api.controller;

import crud.review.api.controller.dto.medic.CreateMedic;
import crud.review.api.controller.dto.medic.MedicViewModel;
import crud.review.api.controller.dto.medic.UpdateMedic;
import crud.review.api.model.Medic;
import crud.review.api.repository.MedicRepository;
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
@RequestMapping("/medic")
public class MedicalController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createMedic(@RequestBody @Valid CreateMedic medicDto, UriComponentsBuilder uriBuilder) {
        var medic = new Medic(medicDto);
        repository.save(medic);

        var uri = uriBuilder.path("/medic/{id}").buildAndExpand(medic.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicViewModel(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicViewModel>> listMedics(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page = repository.findAllByActiveTrue(pageable).map(MedicViewModel::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicViewModel> updateMedic(@RequestBody @Valid UpdateMedic medicDto) {
        var medic = repository.getReferenceById(medicDto.id());
        medic.updateMedic(medicDto);

        return ResponseEntity.ok(new MedicViewModel(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedic(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.disable();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicViewModel> getDetailed(@PathVariable Long id){
        var medic = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicViewModel(medic));
    }
}
