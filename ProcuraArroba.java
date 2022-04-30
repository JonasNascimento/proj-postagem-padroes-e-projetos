package br.edu.ifpb.academico.nascimento.jonas;

public class ProcuraArroba extends ValidaEmail {

    @Override
    public boolean validar(String text) {
        char [] array = text.toCharArray();

        for(char c : array){
            if(c == '@'){
                return existeProx(text);
            }
        }
        return false;
    }
}
