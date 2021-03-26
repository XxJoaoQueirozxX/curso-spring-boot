package com.cursospring.controllers.exceptions;

import com.cursospring.services.exceptions.NotFoundException;
import com.cursospring.services.exceptions.PedidoSemItemsException;
import com.cursospring.services.exceptions.StatusPedidoInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError("Resource not found", e.getMessage(), status.value(), Instant.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PedidoSemItemsException.class)
    public ResponseEntity<StandardError> pedidoSemItems(PedidoSemItemsException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError("Pedido inválido", e.getMessage(), status.value(), Instant.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(StatusPedidoInexistenteException.class)
    public ResponseEntity<StandardError> statusDoPedidoDesconhecido(StatusPedidoInexistenteException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError("Status de pedido inválido", e.getMessage(), status.value(), Instant.now(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
