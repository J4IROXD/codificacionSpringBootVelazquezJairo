package mx.edu.utez.codificacionSpringBoot.renta.service;

import mx.edu.utez.codificacionSpringBoot.exception.customException.BadRequestException;
import mx.edu.utez.codificacionSpringBoot.renta.dto.RequestRentaDTO;
import org.springframework.stereotype.Service;

@Service
public class RentaService {
    public double cotizar(RequestRentaDTO p){
        String tipo = p.getTipoVehiculo();
        double costoDiario;

        switch (tipo){
            case "COMPACTO":
                costoDiario = 550;
                break;
            case "SEDAN":
                costoDiario = 700;
                break;
            case "SUV":
                costoDiario = 950;
                break;
            case "CAMIONETA":
                costoDiario = 1200;
                break;
            default:
                throw new BadRequestException("El tipo de vehículo no es valido");
        }

        if (p.getEdadConductor() <= 18) {
            throw new BadRequestException("La edad minima es de 18 años");
        }

        if (p.getDiasRenta() > 30){
            throw new BadRequestException("la renta no puede ser mayor a 30 días");
        }

        if (p.getKilometrosEstimados() > 5000){
            throw new BadRequestException("Los kilometros estimados no pueden ser mayores a 5000");
        }

        if (tipo.equals("CAMIONETA") && p.getEdadConductor() < 25){
            throw new BadRequestException("Para rentar una camioneta el conductor tiene que tener una edad de al menos de 25 años");
        }

        double costoRenta = costoDiario * p.getDiasRenta();

        double kmIncluidos = p.getDiasRenta() * 100;
        double cargoKm = 0;

        if (p.getKilometrosEstimados() > kmIncluidos) {
            cargoKm = (p.getKilometrosEstimados() - kmIncluidos) * 4;
        }

        double cargoEdad = 0;
        if (p.getEdadConductor() > 18 && p.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKm) * 0.15;
        }

        double seguro = 0;
        if (p.isSeguroCompleto()) {
            seguro = 180 * p.getDiasRenta();
        }

        double descuento = 0;
        if (p.getDiasRenta() >= 7) {
            descuento = costoRenta * 0.10;
        }

        return costoRenta + cargoKm + cargoEdad + seguro - descuento;
    }
}
