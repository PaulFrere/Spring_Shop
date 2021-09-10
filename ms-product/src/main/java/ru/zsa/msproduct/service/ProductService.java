package ru.zsa.msproduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.zsa.mscore.exceptions.ResourceNotFoundException;
import ru.zsa.mscore.model.ProductBasketDto;
import ru.zsa.msproduct.model.Product;
import ru.zsa.msproduct.model.ProductDto;
import ru.zsa.msproduct.model.ProductMapper;
import ru.zsa.msproduct.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Page<ProductDto> getAll(Specification<Product> spec, int page, int size, Sort sort) {
        return productRepository.findAll(spec, PageRequest.of(page, size, sort)).map(mapper::toDto);
    }

    public ProductDto getById(Long id) {
        Optional<ProductDto> product = productRepository.findById(id).map(mapper::toDto);
        return product.orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));
    }

    public ProductDto add(ProductDto student) {
        return mapper.toDto(productRepository.save(mapper.toEntity(student)));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductBasketDto> getProductsByIds(List<Integer> listProductId) {
        return productRepository.findAllById(listProductId).stream().map(p -> {
            ProductBasketDto productBasketDto = new ProductBasketDto();
            productBasketDto.setId((int) p.getId());
            productBasketDto.setTitle(p.getTitle());
            productBasketDto.setPrice(p.getPrice());
            return productBasketDto;
        }).collect(Collectors.toList());
    }
}
