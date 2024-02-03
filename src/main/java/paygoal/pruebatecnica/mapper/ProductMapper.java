package paygoal.pruebatecnica.mapper;

import paygoal.pruebatecnica.entity.ProductEntity;
import paygoal.pruebatecnica.http.request.product.CreateProductRequest;
import paygoal.pruebatecnica.http.request.product.UpdateProductRequest;
import paygoal.pruebatecnica.http.response.product.ProductResponse;

public interface ProductMapper {
    ProductEntity createProductRequestToEntity(CreateProductRequest createProductRequest);

    ProductEntity updateProductRequestToEntity(UpdateProductRequest updateProductRequest,
                                               ProductEntity productEntity);

    ProductResponse entityToResponse(ProductEntity productEntity);
}
