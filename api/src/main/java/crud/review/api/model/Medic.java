package crud.review.api.model;

import crud.review.api.controller.dto.medic.CreateMedic;
import crud.review.api.controller.dto.medic.UpdateMedic;
import crud.review.api.model.enums.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medic")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Medic(CreateMedic medic) {
        this.active = true;
        this.name = medic.name();
        this.email = medic.email();
        this.phone = medic.phone();
        this.crm = medic.crm();
        this.specialty = medic.specialty();
        this.address = new Address(medic.address());
    }

    public void updateMedic(UpdateMedic medic) {
        if (medic.name() != null){
            this.name = medic.name();
        }
        if (medic.phone() != null){
            this.phone = medic.phone();
        }
        if (medic.address() != null){
            this.address.updateAddress(medic.address());
        }
    }

    public void disable() {
        this.active = false;
    }
}
