package br.edu.ifpb.academico.nascimento.jonas;

import java.util.Locale;
import java.util.Objects;

public class Membro implements MembroAssinante{
    private String nome;

    Membro(String nome){
        this.nome = nome.trim().toLowerCase(Locale.ROOT);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void update(String event, MembroAssinante membro) {
        switch(event) {
            case("add"):
                //System.out.println("Enviado para " + this.getNome() + " - " + membro.getNome() + " foi adicionado ao Grupo!");
                break;

            case("del"):
                //System.out.println("Enviado para " + this.getNome() + " - " + membro.getNome() + " foi removido do Grupo!");
                break;

            case("import"):
                //System.out.println("Uma nova lista de membros foi importada!");
                break;

            case("clear"):
                //System.out.println("Lista de usuários vazia!");
                break;

            default:
                System.out.println("Comando não reconhecido");
                break;
        }
    }

    @Override
    public void update(String Evento, String membro, String mensagem) {
        if(Objects.equals(Evento, "msg")) {
            if(!Objects.equals(this.nome, membro))
                System.out.println("Enviado para " + this.getNome() + " - " + membro + " diz: " + mensagem);
        } else System.out.println("Comando não reconhecido.");
    }
}
