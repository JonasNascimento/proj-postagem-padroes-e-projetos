# Para executar a minha aplicação o professor deve digitar:

# 1) fazer download do arquivo postagem.jar para uma pasta/ diretório.

# 2) executar um dos três comandos básicos:

# add - adiciona um novo membro ao grupo
# Por exemplo
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar add joao.jose@ifpb.edu.br

# del - remove um membro existente no grupo
# Por exemplo
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar del joao.jose@ifpb.edu.br

# msg - envia uma mensagem de um dado membro
# Por exemplo
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar msg tereza.maria@ifpb.edu.br "mensagem a ser enviada"

# clean - limpa a lista de membros.
# Por exemplo
# java -jar -jar /usr/katyusco/pasta_201911230010/postagem.jar clean

# import - importa um arquivo .txt contendo os membros a serem adicionados ao Grupo.
# Por exemplo
# java -jar -jar /usr/katyusco/pasta_201911230010/postagem.jar import Arquivo_a_ser_importado.txt

# track - utilizado antes de qualquer comando para registrar a ação em um arquivo de Log.
# Por exemplo
#
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar track add joao.jose@ifpb.edu.br
# 
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar track del joao.jose@ifpb.edu.br
# 
# java -jar /usr/katyusco/pasta_201911230010/postagem.jar track msg tereza.maria@ifpb.edu.br "mensagem a ser enviada"


# OBS 1) Note que um arquivo de texto (.txt) chamado "Membros.txt" irá ser criado na primeira instrução executada.
#      Nesse arquivo serão salvos todos os membros do grupo.

# OBS 2) Note que um arquivo .log chamado "rastreio.log" será executado caso uma operação utilizando o comando "track" seja executada.
#      Nesse arquivo serão salvos todos os logs referentes às chamadas track do programa.

# OBS 3) Note que só serão aceitos dominios com os formatos abaixo:
#
# .com
# .br
# .com.br
# .edu.br
# .gov.br