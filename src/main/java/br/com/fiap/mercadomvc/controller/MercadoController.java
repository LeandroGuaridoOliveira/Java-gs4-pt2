package br.com.fiap.mercadomvc.controller;

import br.com.fiap.mercadomvc.model.Produto;
import br.com.fiap.mercadomvc.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class MercadoController {

    private final ProdutoService produtoService;

    @Autowired
    public MercadoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // READ: Listagem pública com suporte a busca
    @GetMapping
    public String listar(@RequestParam(value = "termo", required = false) String termo, Model model) {
        List<Produto> lista = produtoService.buscarPorNome(termo);
        model.addAttribute("produtos", lista);
        model.addAttribute("termo", termo);
        model.addAttribute("totalItens", lista.size());
        return "produtos/lista";
    }

    // READ: Detalhes do produto
    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Produto> produtoOpt = produtoService.buscarPorId(id);
        if (produtoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado para o ID: " + id);
            return "redirect:/produtos";
        }
        model.addAttribute("produto", produtoOpt.get());
        return "produtos/detalhes";
    }

    // CREATE: Exibir formulário de cadastro (Privado)
    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("acao", "Cadastrar");
        return "produtos/formulario";
    }

    // CREATE: Processar salvamento de novo produto (Privado)
    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("acao", "Cadastrar");
            return "produtos/formulario";
        }
        produtoService.salvar(produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produtos";
    }

    // UPDATE: Exibir formulário de edição (Privado)
    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Produto> produtoOpt = produtoService.buscarPorId(id);
        if (produtoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado para edição.");
            return "redirect:/produtos";
        }
        model.addAttribute("produto", produtoOpt.get());
        model.addAttribute("acao", "Atualizar");
        return "produtos/formulario";
    }

    // UPDATE: Processar atualização de produto existente (Privado)
    @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("acao", "Atualizar");
            return "produtos/formulario";
        }
        produto.setId(id);
        produtoService.salvar(produto);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto atualizado com sucesso!");
        return "redirect:/produtos";
    }

    // DELETE: Excluir produto (Privado - apenas ADMIN)
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Produto> produtoOpt = produtoService.buscarPorId(id);
        if (produtoOpt.isPresent()) {
            produtoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto '" + produtoOpt.get().getNome() + "' excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado para exclusão.");
        }
        return "redirect:/produtos";
    }
}
