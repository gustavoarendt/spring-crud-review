package crud.review.api.model;

import crud.review.api.controller.dto.address.CreateAddress;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String place;
    private String region;
    private String zipcode;
    private String number;
    private String details;
    private String city;
    private String state;

    public Address(CreateAddress address){
        this.place = address.place();
        this.region = address.region();
        this.zipcode = address.zipcode();
        this.number = address.number();
        this.details = address.details();
        this.city = address.city();
        this.state = address.state();
    }

    public void updateAddress(CreateAddress address) {
        if (address.place() != null) {
            this.place = address.place();
        }
        if (address.region() != null) {
            this.region = address.region();
        }
        if (address.zipcode() != null) {
            this.zipcode = address.zipcode();
        }
        if (address.number() != null) {
            this.number = address.number();
        }
        if (address.details() != null) {
            this.details = address.details();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.state() != null) {
            this.state = address.state();
        }

    }
}
