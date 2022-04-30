package br.edu.ifpb.academico.nascimento.jonas;

public interface MembroAssinante {
    void update(String Evento, MembroAssinante membro);
    void update(String Evento, String membro, String mensagem);
    String getNome();
}
