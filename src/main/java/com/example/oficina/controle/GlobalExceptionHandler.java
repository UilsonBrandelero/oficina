package com.example.oficina.controle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.oficina.util.ExcepitonQuantidade;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcepitonQuantidade.class)
    public ResponseEntity<String> erroQuantidadeEstoque(ExcepitonQuantidade ex) {
        return new ResponseEntity<>("Quantidade em estoque inssuficiente da peça " , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
