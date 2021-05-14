package ru.zsa.msauth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zsa.mscore.model.UserDeliveryAddressDto;

import javax.persistence.*;

@Entity
@Table(name = "user_delivery_addresses")
@Data
@NoArgsConstructor
public class UserDeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String house;
    @Column(name = "postal_code")
    private String postalCode;
    @Column
    private String apt;
    @Column(name = "add_info")
    private String addInfo;

    public UserDeliveryAddress(UserDeliveryAddressDto addressDto) {
    }
}