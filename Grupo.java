package br.edu.ifpb.academico.nascimento.jonas;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.readAllLines;

public class Grupo {
    private List<MembroAssinante> listaMembros ;
    private File arquivoMembros;
    private String pathMembros;

    private Track tracker;

    private boolean wait = false;

    Grupo(){
        this.pathMembros = "Membros.txt";
        this.arquivoMembros = new File(pathMembros);
        this.listaMembros = new ArrayList<>();

        try{
            arquivoMembros.createNewFile();
            this.atualizaMembros();
        } catch (IOException e) { e.printStackTrace();}
    }


    public void printMembros(){
        if(listaMembros.isEmpty()) {
            System.out.println("Lista de Membros vazia!");
            return;
        }
        System.out.println("Membros Ativos:");
        for(MembroAssinante m : listaMembros){
            System.out.println("- " + m.getNome());
        }
    }

    private void atualizaMembros() throws IOException {

        List<String> membrosStrg = readAllLines(Paths.get(pathMembros), Charset.defaultCharset());

        if (membrosStrg.isEmpty()) return;
        for(String m : membrosStrg) {
            if (verificarMembro(m)) return;
            else {
                listaMembros.add(new Membro(m));
            }
        }

    }

    public boolean add(MembroAssinante membro, boolean track_flag){
        if(!validarEmail(membro.getNome())) {
            System.out.println("Email inválido!");
            System.out.println("Tente usar domínios como: \n.com  \n.br \n.com.br \n.gov.br \n.edu.br");
            return false;
        }

        if( verificarMembro(membro.getNome()) ){
            System.out.println(membro.getNome() + " já está no grupo! ");
            return false;
        }
        else {
            try(FileWriter fw = new FileWriter(pathMembros, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)){

                pw.println(membro.getNome());

                if(track_flag) {
                    tracker = Track.getInstancia();
                    tracker.CommandAdd(membro);
                }

                pw.close();
                bw.close();
                fw.close();

            } catch (IOException e ){e.printStackTrace();}

            listaMembros.add(membro);
            notificar("add", membro);


            if(!wait) {
                this.printMembros();
            }


            return true;
        }
    }

    public boolean del (String nome, boolean track_flag) {
        if (listaMembros.isEmpty()) {
            System.out.println("Nada a deletar!");
            return false;}
        else if (!verificarMembro(nome)) {
            System.out.println("Não há nenhum membro com o nome \"" + nome + "\" no grupo.");
            return false;
        } else {
            for (MembroAssinante m : listaMembros) {
                if (Objects.equals(m.getNome(), nome)) {
                    if(track_flag) {
                        tracker = Track.getInstancia();
                        tracker.CommandDel(m);
                    }
                    listaMembros.remove(m);
                    notificar("del", m);

                    arquivoMembros.delete();
                    arquivoMembros = new File(pathMembros);

                    try(FileWriter fw = new FileWriter(pathMembros, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);){

                            for(MembroAssinante n : listaMembros) {
                                pw.println(n.getNome());
                            }

                            pw.close();
                            bw.close();
                            fw.close();
                        arquivoMembros.createNewFile();
                    } catch (IOException e)  {e.printStackTrace();}

                    this.printMembros();



                return true;
                }
            }
        }
        return false;
    }

    public void msg(String remetente, String mensagem, boolean track_flag){
        if(listaMembros.isEmpty()) {
            System.out.println("Não existem membros no grupo!");
            return;
        }
        for(MembroAssinante m : listaMembros) {
            if(Objects.equals(m.getNome(), remetente)) {
                notificar("msg", remetente, mensagem);
                if(track_flag) {
                    tracker = Track.getInstancia();
                    tracker.CommandMsg(m, mensagem);}
            }
        }


    }

    public void importArquivo(String path, boolean track_flag){
        this.wait = true;

        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)){

            List<String> membrosStrg = readAllLines(Paths.get(path), Charset.defaultCharset());

            if(track_flag) {
                tracker = Track.getInstancia();
                tracker.CommandImport(path);
            }

            for (String s : membrosStrg) {
                this.add(new Membro(s), track_flag);
            }


            pw.close();
            bw.close();
            fw.close();

        } catch (IOException e ){e.printStackTrace();}

        notificar("import", null);

        this.printMembros();
        this.wait = false;

    }

    public void clean(boolean track_flag){
        listaMembros.clear();

        arquivoMembros.delete();
        arquivoMembros = new File(pathMembros);

        try{
            arquivoMembros.createNewFile();
        } catch (IOException e)  {e.printStackTrace();}

        notificar("clear", null);
        System.out.println("lista de usuários vazia!");

        if(track_flag) {
            tracker = Track.getInstancia();
            tracker.CommandClear();

        }

    }

    private boolean validarEmail(String email){
        ValidaEmail valida1 = new ProcuraArroba();
        ValidaEmail valida2 = new ProcuraDominio();
        ValidaDominio valida3 = new ValidaDominio();

        valida1.defineProx(valida2);
        valida2.defineProx(valida3);

        if(valida1.validar(email)) return true;

        return false;
    }

    private boolean verificarMembro(String nome){
        if(listaMembros.isEmpty()) return false;

        for(MembroAssinante m : listaMembros){
            if(Objects.equals(m.getNome(), nome)){
                return true;
            }
        }
        return false;
    }

    private void notificar(String Event, String remetente, String mensagem){
        if(listaMembros.isEmpty()) return;
        for(MembroAssinante m : listaMembros) {
            m.update(Event, remetente, mensagem);
        }
    }

    private void notificar(String Event, MembroAssinante membro){
        if(listaMembros.isEmpty()) return;
        for(MembroAssinante m : listaMembros) {
            m.update(Event, membro);
        }
    }

}
