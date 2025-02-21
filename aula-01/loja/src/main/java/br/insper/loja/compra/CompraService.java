package br.insper.loja.compra;

import br.insper.loja.evento.EventoService;
import br.insper.loja.produto.ProdutoService;
import br.insper.loja.produto.Produto;
import br.insper.loja.usuario.Usuario;
import br.insper.loja.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ProdutoService produtoService;

    public Compra salvarCompra(Compra compra) {
        // Recupera o usuário e configura os dados iniciais da compra
        Usuario usuario = usuarioService.getUsuario(compra.getUsuario());
        compra.setNome(usuario.getNome());
        compra.setDataCompra(LocalDateTime.now());

        double totalPreco = 0.0;

        // Itera sobre os produtos informados na compra
        for (String produtoId : compra.getProdutos()) {
            // Obtém o produto; se não existir, lança exceção
            Produto produto = produtoService.getProduto(produtoId);

            // Verifica se há estoque disponível
            if (produto.getEstoque() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Produto " + produto.getId() + " sem estoque disponível.");
            }

            // Decrementa o estoque do produto
            produtoService.diminuirEstoque(produtoId);

            // Acumula o preço do produto para cálculo do total
            totalPreco += produto.getPreco();
        }

        // Define o preço total da compra
        compra.setTotalPreco(totalPreco);

        // Registra o evento da compra
        eventoService.salvarEvento(usuario.getEmail(), "Compra realizada com total de: " + totalPreco);

        // Salva e retorna a compra
        return compraRepository.save(compra);
    }

    public List<Compra> getCompras() {
        return compraRepository.findAll();
    }
}
