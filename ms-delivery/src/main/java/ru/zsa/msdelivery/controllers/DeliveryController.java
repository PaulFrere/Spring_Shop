package ru.zsa.msdelivery.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.zsa.msdelivery.AuthClient;
import ru.zsa.msdelivery.exceptions.DeliveryTypeNotFoundException;
import ru.zsa.msdelivery.model.*;
import ru.zsa.msdelivery.services.DeliveryService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/types")
    public List<DeliveryTypeDto> getAllTypes() {
        return Arrays.stream(DeliveryType.values()).map(DeliveryTypeDto::new).collect(Collectors.toList());
    }

    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return deliveryService.getAllShops();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_shop")
    @ResponseStatus(HttpStatus.CREATED)
    public Shop addShop(@RequestBody Shop shop) {
        shop.setId(null);
        return deliveryService.addOrUpdateShop(shop);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update_shop")
    public Shop updateShop(@RequestBody Shop shop) {
        return deliveryService.addOrUpdateShop(shop);
    }

    @GetMapping("/pick_up_points")
    public List<PickUpPoint> getAllPoints() {
        return deliveryService.getAllPoints();
    }

    @GetMapping("/price")
    public DeliveryPriceResponseDto getDeliveryPrice(@RequestBody DeliveryPriceRequestDto deliveryPriceRequestDto) {
        return deliveryService.getDeliveryPrice(deliveryPriceRequestDto);
    }

    @PostMapping("/delivery_details")
    public DeliveryInfoResponseDto getDeliveryDetails(@RequestBody DeliveryInfoRequestDto deliveryInfoRequestDto) {
        DeliveryInfoResponseDto response = new DeliveryInfoResponseDto();
        if (deliveryInfoRequestDto.getDeliveryType().equals(DeliveryType.COURIER.getId())) {
            UserDeliveryAddressDto userDeliveryAddressDto = authClient.getUserAddress(deliveryInfoRequestDto.getDeliveryDetails());
            response.setUserDeliveryAddress(userDeliveryAddressDto);
        } else if (deliveryInfoRequestDto.getDeliveryType().equals(DeliveryType.SHOP.getId())) {
            Shop shop = deliveryService.getShop(deliveryInfoRequestDto.getDeliveryDetails());
            response.setShop(modelMapper.map(shop, ShopDto.class));
        } else if (deliveryInfoRequestDto.getDeliveryType().equals(DeliveryType.PICK_UP_POINT.getId())) {
            PickUpPoint pickUpPoint = deliveryService.getPickUpPoint(deliveryInfoRequestDto.getDeliveryDetails());
            response.setPickUpPoint(modelMapper.map(pickUpPoint, PickUpPointDto.class));
        } else {
            throw new DeliveryTypeNotFoundException("There is no type with id " + deliveryInfoRequestDto.getDeliveryType());
        }
        return response;
    }
}
