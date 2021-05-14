package ru.zsa.msdelivery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zsa.msdelivery.model.UserDeliveryAddressDto;

@FeignClient("ms-auth")
public interface AuthClient {
    @GetMapping("/auth/user_address")
    UserDeliveryAddressDto getUserAddress(@RequestParam("id") Long id);
}
