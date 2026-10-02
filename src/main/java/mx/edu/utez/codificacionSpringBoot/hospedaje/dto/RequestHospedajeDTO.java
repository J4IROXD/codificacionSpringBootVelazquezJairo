package mx.edu.utez.codificacionSpringBoot.hospedaje.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedajeDTO {
    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @Positive(message = "El número de noches debe ser mayor a 0")
    private int numeroNoches;

    @Positive(message = "El número de huéspedes debe ser mayor a 0")
    private int numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    private boolean incluyeDesayuno;

    private boolean incluyeEstacionamiento;
}
