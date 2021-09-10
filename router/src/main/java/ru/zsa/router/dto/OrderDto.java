package ru.zsa.router.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Long userId;
    private Long price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
