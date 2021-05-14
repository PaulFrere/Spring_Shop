package ru.zsa.msdelivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zsa.msdelivery.DeliveryPrice;
import ru.zsa.msdelivery.exceptions.ShopNotFoundException;
import ru.zsa.msdelivery.model.*;
import ru.zsa.msdelivery.repositories.DeliveryRedisRepository;
import ru.zsa.msdelivery.repositories.PickUpPointRepository;
import ru.zsa.msdelivery.repositories.ShopRepository;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    private PickUpPointRepository pickUpPointRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private DeliveryRedisRepository deliveryRedisRepository;

    @Autowired
    private DeliveryPrice deliveryPrice;

    public List<Shop> getAllShops() {
        List<Shop> shops = deliveryRedisRepository.getShops();
        if (shops != null) {
            return shops;
        }
        shops = shopRepository.findAll();
        if (!shops.isEmpty()) {
            deliveryRedisRepository.putShops(shops);
        }
        return shops;
    }

    public List<PickUpPoint> getAllPoints() {
        List<PickUpPoint> pickUpPoints = deliveryRedisRepository.getPickUpPoints();
        if (pickUpPoints != null) {
            return pickUpPoints;
        }
        pickUpPoints = pickUpPointRepository.findAll();
        if (!pickUpPoints.isEmpty()) {
            deliveryRedisRepository.putPickUpPoints(pickUpPoints);
        }
        return pickUpPoints;
    }

    public Shop addOrUpdateShop(Shop shopDto) {
        Shop shop = shopRepository.save(shopDto);
        deliveryRedisRepository.deleteShops();
        return shop;
    }

    public DeliveryPriceResponseDto getDeliveryPrice(DeliveryPriceRequestDto deliveryPriceRequestDto) {
        DeliveryPriceResponseDto deliveryPriceResponseDto = new DeliveryPriceResponseDto();
        for (DeliveryPriceConditions conditions : deliveryPrice.getPriceConfig().getPriceConfig().get(deliveryPriceRequestDto.getDeliveryType())) {
            if (conditions.getMinTotalPrice() <= deliveryPriceRequestDto.getTotalPrice()) {
                deliveryPriceResponseDto.setPrice((float) conditions.getPrice());
                return deliveryPriceResponseDto;
            }
        }
        return deliveryPriceResponseDto;
    }

    public Shop getShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new ShopNotFoundException("There is no shop with id " + id));
    }

    public PickUpPoint getPickUpPoint(Long id) {
        return pickUpPointRepository.findById(id).orElseThrow(() -> new ShopNotFoundException("There is no shop with id " + id));
    }
}
