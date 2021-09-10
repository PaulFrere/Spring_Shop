package ru.zsa.mscore.model;

import lombok.Data;

@Data
public class PickUpPointDto {
    private String name;
    private String city;
    private String location;
    private String workHours;
}
