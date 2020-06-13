package com.vidanaestrada.core.exception;

public class AutenticacaoException extends RuntimeException {

    public AutenticacaoException(String message) {
        super(message);
    }

    public AutenticacaoException() {
        super("failed to attempt to perform user authentication");
    }
}
