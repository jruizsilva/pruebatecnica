package paygoal.pruebatecnica.http.request.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductRequest {
    @NotNull
    @Length(min = 2)
    private String nombre;
    @NotNull
    @Length(min = 2)
    private String descripcion;
    @NotNull
    @DecimalMin(value = "0.01",
                message = "field precio must be greater or equals then 0.01")
    private BigDecimal precio;
    @NotNull
    @Min(1)
    private Long cantidad;
}
