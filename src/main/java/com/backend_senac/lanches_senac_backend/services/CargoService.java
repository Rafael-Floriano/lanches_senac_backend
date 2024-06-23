package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.enums.CargoEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {

    public List<String> getAllCargos() {
        List<String> cargos = new ArrayList<>();
        for (CargoEnum cargo : CargoEnum.values()) {
            cargos.add(cargo.toString());
        }
        return cargos;
    }

}
