package jogobotanica.com.br.quizbotan.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WELLINGTON on 11/02/2017.
 */

public class BDHelper extends SQLiteOpenHelper {

    public static final String NOME_BD = "quiz.sqlite";
    public static final int VERSAO_BD = 1;

    //Tabela Supermercado
    public static final String TBL_QUESTOES = "questoes";
    public static final String COLUNA_ID_QUESTOES = "_id_questoes";
    public static final String COLUNA_IMAGE = "image";
    public static final String COLUNA_RESPOSTAA = "respostaA";
    public static final String COLUNA_RESPOSTAB = "respostaB";
    public static final String COLUNA_RESPOSTAC = "respostaC";
    public static final String COLUNA_RESPOSTAD = "respostaAD";

    //Tabela Ranking
    public static final String TBL_RANKING = "ranking";
    public static final String COLUNA_ID_RANKING = "_id_Ranking";
    public static final String COLUNA_SCORE = "score";

    //Tabela Sequencia_SQLite
    public static final String TBL_SEQUENCIA_SQLITE = "sequenciaSQlite";
    public static final String COLUNA_ID_SEQUENCIA_SQLITE = "_id_sequenciaSQlite";
    public static final String COLUNA_NOME_SEQUENCIA_SQLITE = "nome_sequenciaSQlite";
    public static final String COLUNA_SEQUENCIA_SQLITE = "numero_sequencia";

    //Tabela Contagem das Jogadas
    public static final String TBL_CONTAGEM_JOGADAS = "contagem_Jogadas";
    public static final String COLUNA_ID_CONTAGEM_JOGADASE = "_id_sequenciaSQlite";
    public static final String COLUNA_NIVEL_CONTAGEM_JOGADAS = "nivel_contagem_Jogadas";
    public static final String COLUNA_CONTAGEM_JOGADAS= "numeros_Contagem_Jogadas";


    public BDHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public final void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlScript.getTabelaQuestoes());
        db.execSQL(SqlScript.getTabelaRanking());
        db.execSQL(SqlScript.getTabelaSequenciaSQLite());
        db.execSQL(SqlScript.getTabelaContagem_Jogadas());
        Carregamento.carregarQuestoesBD(db);
        Carregamento.carregarRankingBD(db);

//        falta terminar
    }

    @Override
    public final void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        final String testeTabelaExiste = "DROP TABLE IF EXISTIS ";
        db.execSQL(testeTabelaExiste + TBL_QUESTOES);
        db.execSQL(testeTabelaExiste + TBL_RANKING);
        db.execSQL(testeTabelaExiste + TBL_SEQUENCIA_SQLITE);
        db.execSQL(testeTabelaExiste + TBL_CONTAGEM_JOGADAS);
        onCreate(db);
    }
}
