package jogobotanica.com.br.quizbotan.infra;

/**
 * Created by WELLINGTON on 11/02/2017.
 */

public final class SqlScript {

    private SqlScript() {
    }

    static String getTabelaQuestoes() {

        StringBuilder questoesBuilder = new StringBuilder();
        questoesBuilder.append("CREATE TABLE  questoes ( ");
        questoesBuilder.append("_id_questoes   integer primary key autoincrement unique,   ");
        questoesBuilder.append("image  text not null,  ");
        questoesBuilder.append("respostaA  text ,  ");
        questoesBuilder.append("respostaB  text ,  ");
        questoesBuilder.append("respostaC  text ,  ");
        questoesBuilder.append("respostaD  text ,  ");
        questoesBuilder.append("respostaCorreta  text );" );
        return questoesBuilder.toString();
    }

    static String getTabelaRanking() {
        StringBuilder rankingBuilder = new StringBuilder();
        rankingBuilder.append("CREATE TABLE  ranking ( ");
        rankingBuilder.append("_id_Ranking   integer primary key autoincrement,   ");
        rankingBuilder.append("score  real );" );
        return rankingBuilder.toString();
    }

    static String getTabelaSequenciaSQLite() {
        StringBuilder sequencia_SQLite = new StringBuilder();
        sequencia_SQLite.append("CREATE TABLE  sequenciaSQlite ( ");
        sequencia_SQLite.append("_id_sequenciaSQlite   integer primary key autoincrement,   ");
        sequencia_SQLite.append("nome_sequenciaSQlite  text ,  ");
        sequencia_SQLite.append("numero_sequencia  integer );" );
        return sequencia_SQLite.toString();
    }
    static String getTabelaContagem_Jogadas() {
        StringBuilder contagem_Jogadas = new StringBuilder();
        contagem_Jogadas.append("CREATE TABLE  contagem_Jogadas ( ");
        contagem_Jogadas.append("_id_contagem_Jogadas   integer primary key autoincrement,   ");
        contagem_Jogadas.append("nivel_contagem_Jogadas  integer ,  ");
        contagem_Jogadas.append("numeros_Contagem_Jogadas  integer ,  ");
        contagem_Jogadas.append("primary key( nivel_contagem_Jogadas );" );
        return contagem_Jogadas.toString();
    }

}
