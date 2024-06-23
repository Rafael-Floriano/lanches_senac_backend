package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.dto.FuncionarioDto;
import com.backend_senac.lanches_senac_backend.services.FuncionarioService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public void salvar(@RequestBody FuncionarioDto funcionarioDto) {
        funcionarioService.salvar(funcionarioDto);
    }

    @PutMapping("/{id}")
    public void alterar(@PathVariable("id") Long id, @RequestBody FuncionarioDto funcionarioDto) {
        funcionarioService.alterar(id, funcionarioDto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") @NotNull Long id) {
        funcionarioService.excluir(id);
    }

    @GetMapping
    public Page<FuncionarioDto> paginationOfFuncionarioDto(@RequestParam(value = "search", required = false) String search,
                                                        @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "12") int pageSize) {
        return funcionarioService.createPaginationOfFuncionarioDto(search, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public FuncionarioDto getFuncionarioById(@PathVariable("id") Long funcionarioId) {
        return funcionarioService.getFuncionarioDtoById(funcionarioId);
    }
}
