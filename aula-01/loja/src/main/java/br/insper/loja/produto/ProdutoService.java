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

    // URL base da API de produtos (ajuste a porta e caminho conforme sua configuração)
    private static final String PRODUCT_API_BASE_URL = "http://localhost:8080/api/produto";
    public Produto getProduto(String id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Produto> response = restTemplate.getForEntity(PRODUCT_API_BASE_URL + "/" + id, Produto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }
    public Produto diminuirEstoque(String id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Endpoint da API de produtos que diminui o estoque (configurado para diminuir sempre 1)
            String url = PRODUCT_API_BASE_URL + "/" + id + "/diminuir";
            ResponseEntity<Produto> response = restTemplate.exchange(url, HttpMethod.PUT, null, Produto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para diminuir o estoque");
        }
    }
}
