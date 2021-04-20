package ru.zsa.msproduct.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.zsa.msproduct.model.Product;

public class ProductSpecifications {

    private static final String MIN_COST = "min_cost";
    private static final String MAX_COST = "max_cost";
    private static final String TITLE = "title";

    private static Specification<Product> costIsLessThanAndEqual(float maxCost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

    private static Specification<Product> costIsGreaterThanAndEqual(float minCost) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCost);
    }

    private static Specification<Product> titleIsLike(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + title.toLowerCase() + "%"
        );
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);

        if (params.containsKey(MAX_COST)) {
            for (String value : params.get(MAX_COST)) {
                if (!value.isBlank()) {
                    spec = spec.and(costIsLessThanAndEqual(Float.parseFloat(value)));
                }
            }
        }

        if (params.containsKey(MIN_COST)) {
            for (String value : params.get(MIN_COST)) {
                if (!value.isBlank()) {
                    spec = spec.and(costIsGreaterThanAndEqual(Float.parseFloat(value)));
                }
            }
        }

        if (params.containsKey(TITLE)) {
            for (String value : params.get(TITLE)) {
                if (!value.isBlank()) {
                    spec = spec.and(titleIsLike(value));
                }
            }
        }

        return spec;
    }
}
