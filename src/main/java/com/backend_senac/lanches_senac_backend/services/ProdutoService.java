package com.backend_senac.lanches_senac_backend.services;

import com.backend_senac.lanches_senac_backend.domain.Produto;
import com.backend_senac.lanches_senac_backend.domain.dto.ProdutoDto;
import com.backend_senac.lanches_senac_backend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDto salvar(Produto produto) {
        return new ProdutoDto(repository.save(produto));
    }

    public List<ProdutoDto> listarTodos() {
        return repository.findAll().stream().map(ProdutoDto::new).toList();
    }
}
