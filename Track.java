package br.edu.ifpb.academico.nascimento.jonas;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.*;

public class Track {
    private static Track instancia;
    private static final Logger logger = Logger.getLogger("br.edu.ifpb.academico.nascimento.jonas");
    private FileHandler handler;

    private Track(){
        init();
    };

    private void init(){
        try {
            handler = new FileHandler("rastreio.log", true);
            logger.addHandler(handler);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Track getInstancia(){
        if (instancia == null)
            instancia = new Track();

        return instancia;
    }

    public void escreve(String msg){

        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                SimpleDateFormat logTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Calendar cal = new GregorianCalendar();
                cal.setTimeInMillis(record.getMillis());

                return logTime.format(cal.getTime())
                        + " || "
                        + record.getMessage() + "\n";
            }
        });

        handler.publish(new LogRecord(Level.INFO, msg));

    }

    public void CommandMsg(MembroAssinante m, String msg){
        escreve(m.getNome() + " (" + m + ") diz: " + msg);
    }

    public void CommandAdd(MembroAssinante m){
        escreve(m.getNome() + " (" + m + ") foi adicionado ao grupo." );
    }

    public void CommandDel(MembroAssinante m){
        escreve(m.getNome() + " (" + m + ") foi excluido do grupo." );
    }

    public void CommandClear(){
        escreve(" O grupo foi limpo." );
    }

    public void CommandImport(String file){
        escreve(file + " Foi importado ao banco de dados." );
    }
}
