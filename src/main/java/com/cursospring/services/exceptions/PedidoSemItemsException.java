package com.cursospring.services.exceptions;

public class PedidoSemItemsException extends RuntimeException {
    public PedidoSemItemsException(String message) {
        super(message);
    }
}
