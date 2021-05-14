package ru.zsa.msorder.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.zsa.msorder.domain.ProductBasketDto;
import ru.zsa.msorder.domain.ProductBasketRequestDto;

import java.util.List;

@FeignClient("ms-product")
public interface ProductClient {
    @PostMapping("/products/by_ids")
    List<ProductBasketDto> getProductsByIds(@RequestBody ProductBasketRequestDto productIds);
}
