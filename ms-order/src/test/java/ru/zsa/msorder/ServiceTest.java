package ru.zsa.msorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.zsa.mscore.config.RedisConfig;
import ru.zsa.msorder.model.Order;
import ru.zsa.msorder.repositories.OrderItemRepository;
import ru.zsa.msorder.repositories.OrderRepository;
import ru.zsa.msorder.services.OrderService;
import ru.zsa.router.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {OrderService.class, RedisConfig.class})
public class ServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderItemRepository orderItemRepository;




@Test
    public void findOrderByOrderIdTest(){
    Order order = new Order();
    order.setId(1L);
    order.setUserId(1L);
    order.setPrice(100L);

    Mockito.doReturn(Optional.of(order)).when(orderRepository).findById(1L);

    Optional<OrderDto> orderDtoTest2 = orderService.findOrderByOrderId(1L);
    Assertions.assertEquals(100,orderDtoTest2.get().getPrice());

}

@Test
    public void findAllOrderTest(){
    Order order3 = new Order();
    order3.setId(1L);
    order3.setUserId(1L);
    order3.setPrice(100L);

    Order order2 = new Order();
    order2.setId(2L);
    order2.setUserId(4L);
    order2.setPrice(200L);

    List<Order> orderList = new ArrayList<>();
    orderList.add(order3);
    orderList.add(order2);

    Mockito.doReturn(orderList).when(orderRepository).findAll();

    List<OrderDto> orderDtoList =  orderService.findAllOrder();
    Assertions.assertEquals(2,orderDtoList.size());


}



}
