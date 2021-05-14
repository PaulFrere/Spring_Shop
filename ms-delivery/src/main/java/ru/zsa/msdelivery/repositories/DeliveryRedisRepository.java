package ru.zsa.msdelivery.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.zsa.msdelivery.model.PickUpPoint;
import ru.zsa.msdelivery.model.Shop;

import java.util.List;

@Component
public class DeliveryRedisRepository {
    @Autowired
    private RedisTemplate<String, List<Shop>> shopRedisTemplate;

    @Autowired
    RedisTemplate<String, List<PickUpPoint>> pickUpPointRedisTemplate;

    public void putShops(List<Shop> shops) {
        shopRedisTemplate.opsForValue().set("shops", shops);
    }

    public List<Shop> getShops() {
        return shopRedisTemplate.opsForValue().get("shops");
    }

    public void deleteShops() {
        shopRedisTemplate.opsForValue().getOperations().delete("shops");
    }

    public void putPickUpPoints(List<PickUpPoint> pickUpPoints) {
        pickUpPointRedisTemplate.opsForValue().set("pickUpPoints", pickUpPoints);
    }

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpPointRedisTemplate.opsForValue().get("pickUpPoints");
    }

}
