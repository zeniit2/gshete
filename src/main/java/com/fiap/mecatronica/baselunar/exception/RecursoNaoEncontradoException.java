package com.fiap.mecatronica.baselunar.exception;

/**
 * Lancada quando um recurso solicitado nao e encontrado no banco de dados.
 * Tratada pelo GlobalExceptionHandler, que devolve HTTP 404.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
