package br.com.marcosilva.gestao_de_vagas.exceptions;

public class userfoundException extends RuntimeException {
    public userfoundException() {
        super("Usuario ja registrado");
    }
}
