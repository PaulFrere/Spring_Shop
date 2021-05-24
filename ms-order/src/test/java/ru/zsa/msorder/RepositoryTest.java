package ru.zsa.msorder;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.zsa.msorder.model.Order;
import ru.zsa.msorder.model.OrderItem;
import ru.zsa.msorder.repository.OrderItemRepository;
import ru.zsa.msorder.repository.OrderRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {

@Autowired
    private OrderItemRepository orderItemRepository;

@Autowired
    private OrderRepository orderRepository;

@Test
    public void findAllOrderItemTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAll();
    Assertions.assertEquals(7,orderItemList.size());
}


@Test
    public void findAllByOrderItemIdTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(1L);
    Assertions.assertEquals(2,orderItemList.size());
}
@Test
    public void findAllOrderItemByProductIdTest(){
    List<OrderItem> orderItemList = orderItemRepository.findAllByProductId(3L);
    Assertions.assertEquals(2,orderItemList.size());
}

@Test
    public void findAllOrderByUserIdTest(){
    List<Order> orderList = orderRepository.findAllByUserId(1L);
    Assertions.assertEquals(2,orderList.size());
}


}
