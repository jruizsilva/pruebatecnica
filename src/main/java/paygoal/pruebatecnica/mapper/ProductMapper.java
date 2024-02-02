package paygoal.pruebatecnica.mapper;

import paygoal.pruebatecnica.entity.ProductEntity;
import paygoal.pruebatecnica.http.request.product.CreateProductRequest;
import paygoal.pruebatecnica.http.request.product.UpdateProductRequest;
import paygoal.pruebatecnica.http.response.product.ProductResponse;

public interface ProductMapper {
    ProductEntity requestToEntity(CreateProductRequest createProductRequest);

    ProductEntity requestToEntity(UpdateProductRequest updateProductRequest);

    ProductResponse entityToResponse(ProductEntity productEntity);
}
