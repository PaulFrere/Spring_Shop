package ru.zsa.msorder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zsa.msorder.domain.*;
import ru.zsa.msorder.dto.OrderItemDTO;
import ru.zsa.msorder.repository.CustomerRepository;
import ru.zsa.msorder.repository.OrderItemRepository;
import ru.zsa.msorder.repository.OrderRepository;
import ru.zsa.msproduct.model.Product;
import ru.zsa.msproduct.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderItemDTO> getCutomerOrder(User user) {
        return orderItemRepository.getOrderItemsByOrder(getCreatedUserOrder(user)).stream().map(orderItem -> new OrderItemDTO(orderItem)).collect(Collectors.toList());
    }

    private Order getCreatedUserOrder(User user) {
        Customer currentCustomer = customerRepository.getCustomerByUser(user).orElseGet(()->customerRepository.save(new Customer("",user)));
//        return orderRepository.getOrderByCustomerAndStatus(currentCustomer, OrderStatus.CREATE.name()).orElseGet(()->
//                orderRepository.save(new Order(currentCustomer, OrderStatus.CREATE))
//
//        );
        return orderRepository.getOrderByCustomer(currentCustomer).orElseGet(()->
                orderRepository.save(new Order(currentCustomer, OrderStatus.CREATE))

        );
    }

    @Transactional
    public OrderItemDTO addProduct(Long productId, User user) {
        Order currentOrder = getCreatedUserOrder(user);
        Product currentProduct = productRepository.findById(productId).get();
        OrderItem orderItem = orderItemRepository.getOrderItemByProductAndOrder(currentProduct,currentOrder).orElseGet(()->
                orderItemRepository.save(new OrderItem(currentProduct,currentOrder))
        );
        orderItem.setCount(orderItem.getCount()+1);
        return new OrderItemDTO(orderItemRepository.save(orderItem));
    }

}
