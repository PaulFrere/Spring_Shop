package ru.zsa.msproduct.model;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zsa.mscore.exceptions.ResourceNotFoundException;
import ru.zsa.msproduct.repository.CategoryRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProductMapper(ModelMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Product.class, ProductDto.class)
                .addMappings(m -> m.skip(ProductDto::setCategoryId))
                .addMappings(m -> m.skip(ProductDto::setCategoryName))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(ProductDto.class, Product.class)
                .addMappings(m -> m.skip(Product::setCategory)).setPostConverter(toEntityConverter());
    }

    public Product toEntity(ProductDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Product.class);
    }

    public ProductDto toDto(Product product) {
        return Objects.isNull(product) ? null : mapper.map(product, ProductDto.class);
    }

    public Converter<ProductDto, Product> toEntityConverter() {
        return context -> {
            ProductDto source = context.getSource();
            Product destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    public Converter<Product, ProductDto> toDtoConverter() {
        return context -> {
            Product source = context.getSource();
            ProductDto destination = context.getDestination();
            mapCategory(source, destination);
            return context.getDestination();
        };
    }

    private void mapCategory(ProductDto source, Product destination) {
        if (source.getCategoryId() != null) {
            Category category = categoryRepository.findById(source.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %d not exist", source.getCategoryId())));
            destination.setCategory(category);
        }
    }

    private void mapCategory(Product source, ProductDto destination) {
        Category category = source.getCategory();
        if (category != null) {
            destination.setCategoryId(category.getId());
            destination.setCategoryName(category.getTitle());
        }
    }

}
