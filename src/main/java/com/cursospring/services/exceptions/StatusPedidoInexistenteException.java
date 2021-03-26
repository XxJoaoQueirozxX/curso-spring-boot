package com.cursospring.services.exceptions;

public class StatusPedidoInexistenteException extends RuntimeException{

    public StatusPedidoInexistenteException(String message) {
        super(message);
    }
}
