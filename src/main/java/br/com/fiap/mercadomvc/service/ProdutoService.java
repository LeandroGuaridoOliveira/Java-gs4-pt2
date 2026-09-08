package br.com.fiap.mercadomvc.service;

import br.com.fiap.mercadomvc.model.Produto;
import br.com.fiap.mercadomvc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> buscarPorNome(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodos();
        }
        return produtoRepository.findByNomeContainingIgnoreCase(termo.trim());
    }

    public long totalProdutos() {
        return produtoRepository.count();
    }

    public double precoMedio() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return 0.0;
        }
        return produtos.stream()
                .mapToDouble(p -> p.getPreco() != null ? p.getPreco() : 0.0)
                .average()
                .orElse(0.0);
    }
}
