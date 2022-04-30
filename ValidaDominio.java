package br.edu.ifpb.academico.nascimento.jonas;

import java.util.Objects;

public class ValidaDominio extends ValidaEmail{

    @Override
    public boolean validar(String text) {
        String[] split = text.split("@");
        String[] dominioSplt = split[1].split("\\.");
        char[] dominiochar = split[1].toCharArray();

        if(dominioSplt.length == 1) return false;
        if(dominiochar[0] == '.') return false;


        if(Objects.equals(dominioSplt[dominioSplt.length - 1], "br")){
            if(Objects.equals(dominioSplt[dominioSplt.length - 2], "com")){
                return existeProx(text);
            }
            else if(Objects.equals(dominioSplt[dominioSplt.length - 2], "gov")){
                return existeProx(text);
            }
            else if(Objects.equals(dominioSplt[dominioSplt.length - 2], "edu")){
                return existeProx(text);
            }
            else return false;
        }

        else if(Objects.equals(dominioSplt[dominioSplt.length - 1], "com")){
            return existeProx(text);
        }

        return false;
    }
}
