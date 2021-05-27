package ru.zsa.springshop.msorder.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.zsa.springshop.corelib.interfaces.ITokenService;
import ru.zsa.springshop.corelib.models.UserInfo;
import ru.zsa.springshop.msorder.models.Order;
import ru.zsa.springshop.msorder.services.CartService;
import ru.zsa.springshop.msorder.services.OrderService;
import ru.zsa.springshop.routing.dtos.OrderDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final CartService cartService;

    private final ITokenService tokenService;

    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam UUID cartUuid, @RequestParam String address) {
        UserInfo userInfo = tokenService.parseToken(token);
        Order order = orderService.createFromUserCart(userInfo.getUserId(), cartUuid, address);
        cartService.clearCart(cartUuid);
        return modelMapper.map(order, OrderDto.class);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        UserInfo userInfo = tokenService.parseToken(token);
        return orderService.findAllOrdersByUserId(userInfo.getUserId());
    }
}
