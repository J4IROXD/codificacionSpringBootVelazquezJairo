package mx.edu.utez.codificacionSpringBoot.envios.service;

import mx.edu.utez.codificacionSpringBoot.envios.dto.RequestEnvioDTO;
import mx.edu.utez.codificacionSpringBoot.exception.customException.BadRequestException;
import org.springframework.stereotype.Service;


@Service
public class EnvioService {
    public double cotizar(RequestEnvioDTO p){
        String tipo = p.getTipoEnvio();
        if (!tipo.equals("ESTANDAR") && !tipo.equals("EXPRESS") && !tipo.equals("MISMO_DIA")){
            throw new BadRequestException("El tipo de envio no es valido");
        }

        double volumen = p.getLargoCm() * p.getAnchoCm() * p.getAltoCm();

        if (p.getPesoKg() > 50) {
            throw new BadRequestException("El paquete no debe de pesar más de 50 kg");
        }

        if (p.getLargoCm() > 150 || p.getAnchoCm() > 150 || p.getAltoCm() > 150){
            throw new BadRequestException("La dimension no debe superar los 150 cm");
        }

        if (volumen > 1000000){
            throw new BadRequestException("El volumen no debe ser mayor a 1000000 cm^3");
        }

        double costo = 80 + (12 * p.getPesoKg());

        if (volumen > 50000){
            costo += 100;
        }

        if (tipo.equals("EXPRESS")){
            costo = costo * 1.40;
        } else if (tipo.equals("MISMO_DIA")) {
            costo = costo * 1.70;
        }
        if (p.getValorDeclarado() > 10000) {
            costo += p.getValorDeclarado() * 0.02;
        }

        return costo;
    }
}
