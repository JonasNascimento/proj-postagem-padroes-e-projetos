package br.edu.ifpb.academico.nascimento.jonas;

import java.util.Objects;

public class App {
    public static void main ( String[] args ){
        int inc = 0;
        boolean track = false;
        Grupo grupo = new Grupo();


        if(args.length == 0) {
            System.out.println("Voce precisa usar um dos comandos para Adicionar(add), Deletar(del), Enviar mensagem(msg), Limpar o Grupo(clean), Importar uma lista de membros (import) e Gerar um Log(track)");
        }
        else {
            if(Objects.equals(args[0], "track")) {
                track = true;
                inc++;
            }
            try {
                switch (args[inc]) {
                    case "add":
                        grupo.add(new Membro(args[1+inc]), track);

                        break;
                    case "del":
                        grupo.del(args[1+inc], track);
                        break;

                    case "msg":
                        grupo.msg(args[1+inc], args[2+inc], track);
                        break;

                    case "import":
                        grupo.importArquivo(args[1+inc], track);
                        break;

                    case "clean":
                        grupo.clean(track);
                        break;

                    default:
                        System.out.println(" comando \"" + args[inc] + "\" nao reconhecido!");
                        break;
                }
            } catch (java.lang.IllegalStateException e) {
                System.out.println("Argumento não aceito!");
                System.out.println("\n tente usar apenas os argumentos válidos( add, msg e del");
            }
        }
    }
}
