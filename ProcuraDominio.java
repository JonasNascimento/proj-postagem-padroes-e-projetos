package br.edu.ifpb.academico.nascimento.jonas;

public class ProcuraDominio extends ValidaEmail{

    @Override
    public boolean validar(String text) {
        String[] split = text.split("@");
        if(split.length == 2) return existeProx(text);
        return false;
    }
}
