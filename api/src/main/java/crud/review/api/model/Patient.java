package crud.review.api.model;

import crud.review.api.controller.dto.patient.CreatePatient;
import crud.review.api.controller.dto.patient.UpdatePatient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patient")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean active;

    public Patient(CreatePatient patient) {
        this.active = true;
        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.cpf = patient.cpf();
        this.address = new Address(patient.address());
    }

    public void updatePatient(UpdatePatient patient) {
        if (patient.name() != null){
            this.name = patient.name();
        }
        if (patient.phone() != null){
            this.phone = patient.phone();
        }
        if (patient.address() != null){
            this.address.updateAddress(patient.address());
        }
    }

    public void disable() {
        this.active = false;
    }
}
