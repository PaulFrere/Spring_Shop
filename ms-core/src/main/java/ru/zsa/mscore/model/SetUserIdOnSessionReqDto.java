package ru.zsa.mscore.model;

import lombok.Data;

import java.util.UUID;

@Data
public class SetUserIdOnSessionReqDto {
    private UUID guid;
    private Integer userId;
}
