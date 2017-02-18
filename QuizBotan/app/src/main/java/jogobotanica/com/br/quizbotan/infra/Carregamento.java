package jogobotanica.com.br.quizbotan.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by WELLINGTON on 11/02/2017.
 */

public class Carregamento extends AppCompatActivity {

    private final Context context = getApplicationContext();

    private static String inserirQuestoesBD = "INSERT INTO " + BDHelper.TBL_QUESTOES + " (" +
            BDHelper.COLUNA_IMAGE +" , " +
            BDHelper.COLUNA_RESPOSTAA +" , " +
            BDHelper.COLUNA_RESPOSTAB +" , " +
            BDHelper.COLUNA_RESPOSTAC +" , " +
            BDHelper.COLUNA_RESPOSTAD +" , " +
            ") VALUES  ";

    private static String inserirRankingBD = "INSERT INTO " + BDHelper.TBL_RANKING + " (" +
            BDHelper.COLUNA_SCORE +" , " +
            ") VALUES  ";


    public static void carregarQuestoesBD(SQLiteDatabase db) {
        carregarQuestoes(db, "Afghanistan", "Bahamas","Afghanistan", "Benin", "Cayman_Islands", "Afghanistan");
    }

    private static void carregarQuestoes(SQLiteDatabase db, String image, String reposta1, String reposta2, String reposta3, String reposta4, String repostaCorreta) {
        db.execSQL(inserirQuestoesBD + "('" + image + "', '" + reposta1 + "',  '" + reposta2 + "',  '" + reposta3 + "',  '" + reposta4 + "',  '" + repostaCorreta + "');");
    }

    public static void carregarRankingBD(SQLiteDatabase db) {
        carregarRanking(db, 1.00);
    }
    private static void carregarRanking(SQLiteDatabase db, Double score) {
        db.execSQL(inserirQuestoesBD + "('" + score +"');");
    }

}
