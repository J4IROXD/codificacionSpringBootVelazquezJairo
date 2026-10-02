package mx.edu.utez.codificacionSpringBoot.renta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRentaDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @Positive(message = "La edad debe de ser mayor a 0")
    private int edadConductor;

    @NotBlank(message = "El tipo de coche es obligatorio")
    private String tipoVehiculo;

    @Positive(message = "Los dia de renta deben de ser mayor a 0")
    private int diasRenta;

    @PositiveOrZero(message = "Los kilometros no puedes ser negativos")
    private double kilometrosEstimados;

    private boolean seguroCompleto;
}
