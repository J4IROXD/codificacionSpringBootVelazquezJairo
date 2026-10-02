package mx.edu.utez.codificacionSpringBoot.envios.controller;

import jakarta.validation.Valid;
import mx.edu.utez.codificacionSpringBoot.envios.dto.RequestEnvioDTO;
import mx.edu.utez.codificacionSpringBoot.envios.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("envios")
public class EnvioController {
    private final EnvioService service;

    public EnvioController(EnvioService service){
        this.service = service;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<Map<String, Double>> cotizar(@RequestBody @Valid RequestEnvioDTO payload) {
        double total = service.cotizar(payload);
        return ResponseEntity.ok(Map.of("costoTotal", total));
    }
}
