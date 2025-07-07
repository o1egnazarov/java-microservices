package ru.noleg.currencyrate.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.noleg.currencyrate.service.CbrService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cbr")
public class CbrController {

    private final CbrService cbrService;

    public CbrController(CbrService cbrService) {
        this.cbrService = cbrService;
    }

    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> fetchCurrencyRates(@RequestParam String code) {
        var result = this.cbrService.requestByCurrencyCode(code);
        return ResponseEntity.ok(result);
    }
}
