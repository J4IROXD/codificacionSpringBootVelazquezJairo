package mx.edu.utez.codificacionSpringBoot.hospedaje.service;

import mx.edu.utez.codificacionSpringBoot.exception.customException.BadRequestException;
import mx.edu.utez.codificacionSpringBoot.hospedaje.dto.RequestHospedajeDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HospedajeService {

    public Map<String, Double> cotizar(RequestHospedajeDTO p) {
        String habitacion = p.getTipoHabitacion();
        String temporada = p.getTemporada();
        double costoNoche;

        switch (habitacion) {
            case "INDIVIDUAL":
                costoNoche = 700;
                break;
            case "DOBLE":
                costoNoche = 1100;
                break;
            case "SUITE":
                costoNoche = 1800;
                break;
            default:
                throw new BadRequestException("El tipo de habitación no es válido");
        }

        if (!temporada.equals("BAJA") && !temporada.equals("REGULAR") && !temporada.equals("ALTA")) {
            throw new BadRequestException("La temporada no es válida");
        }

        if (p.getNumeroNoches() > 30) {
            throw new BadRequestException("La reservación no puede superar 30 noches");
        }
        if (habitacion.equals("INDIVIDUAL") && p.getNumeroHuespedes() > 1) {
            throw new BadRequestException("La habitación individual es para una sola persona");
        }
        if (habitacion.equals("DOBLE") && p.getNumeroHuespedes() > 2) {
            throw new BadRequestException("La habitación doble es para máximo dos personas");
        }
        if (habitacion.equals("SUITE") && p.getNumeroHuespedes() > 4) {
            throw new BadRequestException("La suite es para máximo cuatro personas");
        }

        double costoHospedaje = costoNoche * p.getNumeroNoches();
        double ajusteTemporada = 0;

        if (temporada.equals("BAJA")) {
            ajusteTemporada = -(costoHospedaje * 0.10);
        } else if (temporada.equals("ALTA")) {
            ajusteTemporada = costoHospedaje * 0.25;
        }

        double descuentoEstancia = 0;
        if (p.getNumeroNoches() >= 7) {
            descuentoEstancia = costoHospedaje * 0.08;
        }

        double desayuno = 0;
        if (p.isIncluyeDesayuno()) {
            desayuno = p.getNumeroHuespedes() * p.getNumeroNoches() * 150;
        }

        double estacionamiento = 0;
        if (p.isIncluyeEstacionamiento()) {
            estacionamiento = p.getNumeroNoches() * 100;
        }

        double subtotal = costoHospedaje + ajusteTemporada - descuentoEstancia + desayuno + estacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        Map<String, Double> resultado = new LinkedHashMap<>();
        resultado.put("subtotal", subtotal);
        resultado.put("impuestoHospedaje", impuesto);
        resultado.put("total", total);
        return resultado;
    }
}
