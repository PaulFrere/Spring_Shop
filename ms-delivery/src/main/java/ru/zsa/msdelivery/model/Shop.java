package ru.zsa.msdelivery.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String city;

    @Column
    private String location;

    @Column(name = "work_hours")
    private String workHours;

    @Column
    private String phone;
}
