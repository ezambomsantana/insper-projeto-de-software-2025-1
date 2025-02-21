package br.insper.loja.produto;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

    // URL base da API de produtos (ajuste conforme necessário)
    private static final String PRODUCT_API_BASE_URL = "http://localhost:8080/api/produto";

    private final RestTemplate restTemplate = new RestTemplate();

    // Obtém um produto e verifica se ele existe
    public Produto getProduto(String id) {
        try {
            ResponseEntity<Produto> response = restTemplate.getForEntity(PRODUCT_API_BASE_URL + "/" + id, Produto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    // Verifica se um produto tem estoque disponível antes da compra
    public void validarProdutoComEstoque(String id) {
        Produto produto = getProduto(id);
        if (produto == null || produto.getEstoque() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto " + id + " está sem estoque disponível.");
        }
    }

    // Diminui o estoque de um produto após a compra
    public Produto diminuirEstoque(String id) {
        validarProdutoComEstoque(id); // Garante que só reduz estoque de produtos válidos
        try {
            String url = PRODUCT_API_BASE_URL + "/" + id + "/diminuir";
            ResponseEntity<Produto> response = restTemplate.exchange(url, HttpMethod.PUT, null, Produto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao diminuir o estoque do produto " + id);
        }
    }
}
