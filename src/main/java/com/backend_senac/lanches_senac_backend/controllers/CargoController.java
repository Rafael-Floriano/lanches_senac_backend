package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.services.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cargo")
public class CargoController {

    private final CargoService cargoService;

    @GetMapping("/allCargos")
    public List<String> getAllCargos() {
        return cargoService.getAllCargos();
    }

}
