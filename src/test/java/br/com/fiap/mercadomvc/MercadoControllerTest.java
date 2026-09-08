package br.com.fiap.mercadomvc;

import br.com.fiap.mercadomvc.model.Produto;
import br.com.fiap.mercadomvc.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MercadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void rotaHomePublicaDeveRetornarSucesso() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("totalProdutos", "precoMedio", "produtosDestaque"));
    }

    @Test
    void rotaProdutosPublicaDeveRetornarSucesso() throws Exception {
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/lista"))
                .andExpect(model().attributeExists("produtos", "totalItens"));
    }

    @Test
    void rotaDetalhesPublicaDeveRetornarProduto() throws Exception {
        Produto produto = produtoRepository.findAll().get(0);

        mockMvc.perform(get("/produtos/detalhes/" + produto.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/detalhes"))
                .andExpect(model().attributeExists("produto"));
    }

    @Test
    void rotaNovoProdutoNaoAutenticadoDeveRedirecionarParaLogin() throws Exception {
        mockMvc.perform(get("/produtos/novo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void rotaNovoProdutoAutenticadoComoAdminDeveRetornarSucesso() throws Exception {
        mockMvc.perform(get("/produtos/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().attributeExists("produto", "acao"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void cadastrarProdutoComSucessoDevePersistirERedirecionar() throws Exception {
        mockMvc.perform(post("/produtos/salvar")
                        .with(csrf())
                        .param("nome", "Esponja de Aço Bombril")
                        .param("tipo", "Produto de Limpeza")
                        .param("setor", "Limpeza")
                        .param("tamanho", "60g")
                        .param("preco", "4.50"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"))
                .andExpect(flash().attributeExists("mensagemSucesso"));

        assertThat(produtoRepository.findByNomeContainingIgnoreCase("Bombril")).isNotEmpty();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void cadastrarProdutoComDadosInvalidosDeveRetornarFormularioComErros() throws Exception {
        mockMvc.perform(post("/produtos/salvar")
                        .with(csrf())
                        .param("nome", "")
                        .param("tipo", "")
                        .param("setor", "")
                        .param("preco", "-5.00"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().hasErrors());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void editarProdutoExistenteDeveCarregarFormulario() throws Exception {
        Produto produto = produtoRepository.findAll().get(0);

        mockMvc.perform(get("/produtos/editar/" + produto.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().attribute("acao", "Atualizar"))
                .andExpect(model().attributeExists("produto"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void excluirProdutoComoAdminDeveRemoverERedirecionar() throws Exception {
        Produto produto = produtoRepository.save(new Produto("Item Teste Excluir", "Teste", "Teste", "1", 10.0));
        Long id = produto.getId();

        mockMvc.perform(get("/produtos/excluir/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"))
                .andExpect(flash().attributeExists("mensagemSucesso"));

        assertThat(produtoRepository.findById(id)).isEmpty();
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    void excluirProdutoComoUserComumDeveRetornar403Forbidden() throws Exception {
        Produto produto = produtoRepository.findAll().get(0);

        mockMvc.perform(get("/produtos/excluir/" + produto.getId()))
                .andExpect(status().isForbidden());
    }
}