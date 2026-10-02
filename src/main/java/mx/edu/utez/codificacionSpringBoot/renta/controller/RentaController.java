package mx.edu.utez.codificacionSpringBoot.renta.controller;

import jakarta.validation.Valid;
import mx.edu.utez.codificacionSpringBoot.renta.dto.RequestRentaDTO;
import mx.edu.utez.codificacionSpringBoot.renta.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("renta")
public class RentaController {

    private final RentaService service;

    public RentaController(RentaService service) {
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Double>> cotizar(@RequestBody @Valid RequestRentaDTO payload) {
        double total = service.cotizar(payload);
        return ResponseEntity.ok(Map.of("costoTotal", total));
    }
}
