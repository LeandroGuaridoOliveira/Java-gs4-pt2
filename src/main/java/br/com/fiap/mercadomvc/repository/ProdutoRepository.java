package br.com.fiap.mercadomvc.repository;

import br.com.fiap.mercadomvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findBySetorIgnoreCase(String setor);
    List<Produto> findByTipoIgnoreCase(String tipo);
}
