package br.com.cote.coteemprestimos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bacen")
public class BacenController {
    private final String BASE_URL = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/moedas")
    public ResponseEntity<String> getMoedas() {
        String url = BASE_URL + "/Moedas?$format=json";
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cotacao")
    public ResponseEntity<String> getCotacaoMoedaDia(@RequestParam String moeda, @RequestParam String data) {
        String url = BASE_URL + "/CotacaoMoedaDia(moeda='" + moeda + "',dataCotacao='" + data + "')?$format=json";
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}
