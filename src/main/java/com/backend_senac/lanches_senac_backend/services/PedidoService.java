package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Pedido;
import com.backend_senac.lanches_senac_backend.domain.dto.PedidoDto;
import com.backend_senac.lanches_senac_backend.enums.StatusPedido;
import com.backend_senac.lanches_senac_backend.repositories.PedidoRepository;
import com.backend_senac.lanches_senac_backend.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public PedidoDto buscarPorId(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido " + id + " não encontrado!"));
        return new PedidoDto(pedido);
    }

    public PedidoDto salvar(Pedido pedido) {
        usuarioService.buscarPorId(pedido.getUsuario().getId());

        if(Objects.isNull(pedido.getId())) {
            pedido.setDataCriacao(LocalDate.now());
        }

        Pedido pedidoSalvo = repository.save(pedido);
        pedido.getItensPedido().stream()
                .filter(itemPedido -> Objects.isNull(itemPedido.getId()))
                .forEach(itemPedido -> {
                    itemPedido.setPedido(pedidoSalvo);
                    itemPedidoService.salvar(itemPedido);
                });
        return new PedidoDto(pedidoSalvo);
    }

    public PedidoDto alterar(Pedido pedido, Long id) {
        buscarPorId(id);
        pedido.setId(id);
        return salvar(pedido);
    }

    public List<PedidoDto> listarPorUsuario(Long usuarioId) {
        usuarioService.buscarPorId(usuarioId);
        return repository.findByUsuarioId(usuarioId).stream().map(PedidoDto::new).toList();
    }

    public PedidoDto buscarUltimoPedidoAberto() {
        Pedido pedido = repository.findFirstByStatusPedidoOrderByDataCriacaoDesc(StatusPedido.ABERTO)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Não existem pedidos abertos!"));
        return new PedidoDto(pedido);
    }
}
