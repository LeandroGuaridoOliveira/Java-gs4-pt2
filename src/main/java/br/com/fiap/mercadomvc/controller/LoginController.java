package br.com.fiap.mercadomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("erroMensagem", "Usuário ou senha inválidos. Tente novamente.");
        }
        if (logout != null) {
            model.addAttribute("sucessoMensagem", "Você foi desconectado com sucesso.");
        }
        return "login";
    }
}
