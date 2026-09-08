package br.com.fiap.mercadomvc.controller;

import br.com.fiap.mercadomvc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProdutoService produtoService;

    @Autowired
    public HomeController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalProdutos", produtoService.totalProdutos());
        model.addAttribute("precoMedio", produtoService.precoMedio());
        model.addAttribute("produtosDestaque", produtoService.listarTodos().stream().limit(4).toList());
        return "index";
    }
}
