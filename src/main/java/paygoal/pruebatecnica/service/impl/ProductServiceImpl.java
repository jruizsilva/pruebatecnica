package paygoal.pruebatecnica.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paygoal.pruebatecnica.entity.ProductEntity;
import paygoal.pruebatecnica.exceptions.CustomEntityNotFoundException;
import paygoal.pruebatecnica.exceptions.CustomFieldValueNotAllowedException;
import paygoal.pruebatecnica.http.request.product.CreateProductRequest;
import paygoal.pruebatecnica.http.request.product.UpdateProductRequest;
import paygoal.pruebatecnica.http.response.product.ProductResponse;
import paygoal.pruebatecnica.mapper.ProductMapper;
import paygoal.pruebatecnica.repository.ProductRepository;
import paygoal.pruebatecnica.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        Optional<ProductEntity> productEntity = productRepository.findProductByName(createProductRequest.getNombre());

        if (productEntity.isPresent()) {
            throw new CustomFieldValueNotAllowedException("product name in use");
        }

        ProductEntity productEntityToSave = productMapper.requestToEntity(createProductRequest);
        ProductEntity productEntitySaved = productRepository.save(productEntityToSave);
        return productMapper.entityToResponse(productEntitySaved);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        Optional<ProductEntity> productEntity = productRepository.findById(updateProductRequest.getId());

        if (productEntity.isEmpty()) {
            throw new CustomEntityNotFoundException("product to edit not found");
        }
        ProductEntity productEntityToUpdate = productMapper.requestToEntity(updateProductRequest);
        ProductEntity productEntityUpdated = productRepository.save(productEntityToUpdate);
        return productMapper.entityToResponse(productEntityUpdated);
    }

    @Override
    public ProductResponse findProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product not found"));
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public ProductResponse findProductByName(String name) {
        ProductEntity productEntity = productRepository.findProductByName(name)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product not found"));
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public void deleteProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                                                       .orElseThrow(() -> new CustomEntityNotFoundException("product to delete not found"));

        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceAsc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceAsc();
        return productEntityList.stream()
                                .map(productMapper::entityToResponse)
                                .toList();
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceDesc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceDesc();
        return productEntityList.stream()
                                .map(productMapper::entityToResponse)
                                .toList();
    }
}
