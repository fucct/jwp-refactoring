package kitchenpos.application;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import kitchenpos.domain.Product;
import kitchenpos.dto.request.ProductCreateRequest;
import kitchenpos.dto.response.ProductResponse;
import kitchenpos.repository.ProductRepository;

@Service
@Validated
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(@Valid final ProductCreateRequest request) {
        return ProductResponse.of(productRepository.save(request.toEntity()));
    }

    public List<Product> list() {
        return productRepository.findAll();
    }
}
