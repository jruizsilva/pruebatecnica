package paygoal.pruebatecnica.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paygoal.pruebatecnica.entity.ProductEntity;
import paygoal.pruebatecnica.http.request.product.CreateProductRequest;
import paygoal.pruebatecnica.http.request.product.UpdateProductRequest;
import paygoal.pruebatecnica.http.response.product.ProductResponse;
import paygoal.pruebatecnica.mapper.ProductMapper;
import paygoal.pruebatecnica.repository.ProductRepository;
import paygoal.pruebatecnica.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        ProductEntity productEntityToSave = productMapper.requestToEntity(createProductRequest);
        ProductEntity productEntitySaved = productRepository.save(productEntityToSave);
        return productMapper.entityToResponse(productEntitySaved);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        this.findProductEntityById(updateProductRequest.getId());
        ProductEntity productEntityToUpdate = productMapper.requestToEntity(updateProductRequest);
        ProductEntity productEntityUpdated = productRepository.save(productEntityToUpdate);
        return productMapper.entityToResponse(productEntityUpdated);
    }

    @Override
    public ProductResponse findProductById(Long productId) {
        ProductEntity productEntity = this.findProductEntityById(productId);
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public ProductResponse findProductByName(String name) {
        ProductEntity productEntity = this.findProductEntityByName(name);
        return productMapper.entityToResponse(productEntity);
    }

    @Override
    public void deleteProductById(Long productId) {
        this.findProductEntityById(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceAsc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceAsc();
        return productEntityList.stream()
                                .map(productEntity -> productMapper.entityToResponse(productEntity))
                                .toList();
    }

    @Override
    public List<ProductResponse> findAllProductsSortedByPriceDesc() {
        List<ProductEntity> productEntityList = productRepository.findAllProductsSortedByPriceDesc();
        return productEntityList.stream()
                                .map(productEntity -> productMapper.entityToResponse(productEntity))
                                .toList();
    }

    private ProductEntity findProductEntityById(Long productId) {
        return productRepository.findById(productId)
                                .orElseThrow(() -> new EntityNotFoundException("product not found"));
    }

    private ProductEntity findProductEntityByName(String name) {
        return productRepository.findProductByName(name)
                                .orElseThrow(() -> new EntityNotFoundException("product not found"));
    }
}
