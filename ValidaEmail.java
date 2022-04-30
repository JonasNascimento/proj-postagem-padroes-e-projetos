package br.edu.ifpb.academico.nascimento.jonas;

public abstract class ValidaEmail {
    private ValidaEmail prox;

    public abstract boolean validar(String text);

    protected ValidaEmail defineProx(ValidaEmail prox){
        this.prox = prox;
        return prox;
    }

    protected boolean existeProx(String text) {
        if (prox == null) {
            return true;
        }
        return prox.validar(text);
    }
}
