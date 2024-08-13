package br.com.marcosilva.gestao_de_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuario ja registrado");
    }
}
