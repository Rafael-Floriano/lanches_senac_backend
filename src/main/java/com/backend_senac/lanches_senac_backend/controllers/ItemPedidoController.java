package com.backend_senac.lanches_senac_backend.controllers;

import com.backend_senac.lanches_senac_backend.domain.ItemPedido;
import com.backend_senac.lanches_senac_backend.domain.dto.ItemPedidoDto;
import com.backend_senac.lanches_senac_backend.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itenspedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ItemPedidoDto salvar(@RequestBody ItemPedido itemPedido) {
        return itemPedidoService.salvar(itemPedido);
    }

    @GetMapping
    public List<ItemPedidoDto> listarTodos() {
        return itemPedidoService.listarTodos();
    }
}
