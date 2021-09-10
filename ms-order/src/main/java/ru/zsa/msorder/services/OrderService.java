package ru.zsa.msorder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zsa.msorder.dto.FullBasketDto;
import ru.zsa.msorder.dto.OrderResponseDto;
import ru.zsa.msorder.exceptions.PromoInvalidException;
import ru.zsa.msorder.domain.*;
import ru.zsa.msorder.repository.OrderRepository;

import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Integer PROMO_APPLIED = 1;


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BasketService basketService;



    public String generateRandomCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Transactional
    public OrderResponseDto createOrder(Integer userId, OrderRequestDto orderRequestDto) {
        Order order = new Order(orderRequestDto);
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.CREATED.getStatus());
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(orderRepository.save(order).getId());
        basketService.deleteByIds(orderRequestDto.getItems().stream().map(FullBasketDto::getId).collect(Collectors.toList()));
        return orderResponseDto;
    }
}
