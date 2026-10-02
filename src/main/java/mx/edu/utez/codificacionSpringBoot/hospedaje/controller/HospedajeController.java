package mx.edu.utez.codificacionSpringBoot.hospedaje.controller;

import jakarta.validation.Valid;
import mx.edu.utez.codificacionSpringBoot.hospedaje.dto.RequestHospedajeDTO;
import mx.edu.utez.codificacionSpringBoot.hospedaje.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("hospedaje")
public class HospedajeController {
    private final HospedajeService service;

    public HospedajeController(HospedajeService service) {
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Double>> cotizar(@RequestBody @Valid RequestHospedajeDTO payload) {
        return ResponseEntity.ok(service.cotizar(payload));
    }
}
