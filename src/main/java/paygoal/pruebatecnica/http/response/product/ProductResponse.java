package paygoal.pruebatecnica.http.response.product;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Long cantidad;
}
