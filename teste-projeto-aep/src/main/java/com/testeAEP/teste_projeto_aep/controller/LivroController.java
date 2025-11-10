package com.testeAEP.teste_projeto_aep.controller;

import com.testeAEP.teste_projeto_aep.model.Livro; //
import com.testeAEP.teste_projeto_aep.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    // Local onde os livros estão armazenados (mudar se necessário!)
    // Este caminho DEVE apontar para a pasta que você criou no Passo 1
    private final Path caminhoDiretorio = Paths.get("livros_aep");

    // 1. Rota para EXIBIR A LISTA de Livros
    // URL: http://localhost:9090/livros
    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("listaLivros", repository.findAll());
        return "lista-livros"; // Novo template que criaremos
    }

    // 2. Rota para o DOWNLOAD do Arquivo
    // URL: http://localhost:9090/livros/download/{arquivoNome}
    @GetMapping("/download/{arquivoNome:.+}") // :.+ permite que o nome do arquivo tenha ponto (ex: .pdf)
    public ResponseEntity<Resource> downloadLivro(@PathVariable String arquivoNome) {
        try {
            Path caminhoArquivo = caminhoDiretorio.resolve(arquivoNome).normalize();
            Resource resource = new UrlResource(caminhoArquivo.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = "application/octet-stream"; // Tipo genérico de arquivo

                // Retorna o arquivo com cabeçalhos que forçam o download
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                // Se o arquivo não existir na pasta
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // Em caso de erro de leitura ou caminho
            return ResponseEntity.internalServerError().build();
        }
    }
}