package com.testeAEP.teste_projeto_aep.controller;

import com.testeAEP.teste_projeto_aep.model.Cadastro;
import com.testeAEP.teste_projeto_aep.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // NOVO IMPORT NECESSÁRIO
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // NOVO IMPORT NECESSÁRIO

@Controller
@RequestMapping("/") //Define a rota base como a raiz (para index e login)
public class CadastroController {

    @Autowired
    private CadastroRepository repository;

    // 1. Rota para a PÁGINA INICIAL (index.html)
    // URL: http:/http://localhost:9090///
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 2. Rota para a TELA DE LOGIN (login.html)
    // URL: http://localhost:8080/login
    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    // 3. Rota para RECUPERAR SENHA (recuperar-senha.html)
    // URL: http://localhost:8080/recuperar-senha
    @GetMapping("/recuperar-senha")
    public String exibirRecuperarSenha() {
        return "recuperar-senha";
    }

    // 4. Método para EXIBIR A LISTA DE CADASTROS (cadastro-lista.html)
    // URL: http://localhost:8080/cadastros
    @GetMapping("/lista")
    public String listarCadastros(Model model) {
        model.addAttribute("listaCadastros", repository.findAll());
        model.addAttribute("novoCadastro", new Cadastro());
        return "cadastro-lista";
    }

    // 3. Rota para SISTEMA (sistema.html)
    @GetMapping("/sistema")
    public String exibirSistema() {
        return "sistema"; // Retorna o arquivo sistema.html
    }

    // 5. Método para SALVAR UM NOVO CADASTRO
    @PostMapping("/salvar-cadastro")
    public String salvarCadastro(@ModelAttribute Cadastro cadastro) {
        repository.save(cadastro);
        return "redirect:/lista"; // Redireciona para a lista
    }

    // NOVO MÉTODO: Rota que processa o formulário de Login
    @PostMapping("/fazer-login")
    public String processarLogin(
            @RequestParam String email, // Captura o campo 'name="email"' do formulário
            @RequestParam String senha, // Captura o campo 'name="senha"' do formulário
            RedirectAttributes attributes) {

        // 1. Tenta encontrar o usuário no banco de dados (Repository)
        Cadastro usuarioLogado = repository.findByEmailAndSenha(email, senha);

        // 2. Verifica o resultado
        if (usuarioLogado != null) {
            // Se encontrou: Login Sucedido. Redireciona para a área de cadastros/sistema
            return "redirect:/sistema";
        } else {
            // Se NÃO encontrou: Login Falhou.

            // Adiciona uma mensagem de erro na URL para ser exibida na página de login
            attributes.addFlashAttribute("erroLogin", "E-mail ou senha incorretos.");

            // Redireciona de volta para a tela de login
            return "redirect:/login";
        }
    }

}