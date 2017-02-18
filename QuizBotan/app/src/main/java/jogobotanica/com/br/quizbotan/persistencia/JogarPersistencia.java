package jogobotanica.com.br.quizbotan.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jogobotanica.com.br.quizbotan.dominio.Questoes;
import jogobotanica.com.br.quizbotan.infra.BDHelper;
import jogobotanica.com.br.quizbotan.infra.Enum;

/**
 * Created by WELLINGTON on 13/02/2017.
 */

public class JogarPersistencia {

    private BDHelper bdHelper;
    public JogarPersistencia(Context context) {
        bdHelper = new BDHelper(context);
    }

    public List<Questoes> buscar(String mode) {
        List<Questoes> listaQuestoes = new ArrayList<>();
        SQLiteDatabase db = bdHelper.getWritableDatabase();
        Cursor cursor;
        int limit = 0;
        if (mode.equals(Enum.NIVELJOGO.FACIL.toString()))
            limit = 30;
        else if (mode.equals(Enum.NIVELJOGO.MEDIO.toString()))
            limit = 50;
        else if (mode.equals(Enum.NIVELJOGO.AVANÇADO.toString()))
            limit = 100;
        else if (mode.equals(Enum.NIVELJOGO.HARDSET.toString()))
            limit = 200;
        try {
            cursor = db.rawQuery(String.format("SELECT * FROM Question ORDER BY Random() LIMIT %d", limit), null);
            if (cursor == null) return null;
            cursor.moveToFirst();
            do {
                int Id = cursor.getInt(cursor.getColumnIndex("ID"));
                String Image = cursor.getString(cursor.getColumnIndex("Image"));
                String AnswerA = cursor.getString(cursor.getColumnIndex("AnswerA"));
                String AnswerB = cursor.getString(cursor.getColumnIndex("AnswerB"));
                String AnswerC = cursor.getString(cursor.getColumnIndex("AnswerC"));
                String AnswerD = cursor.getString(cursor.getColumnIndex("AnswerD"));
                String CorrectAnswer = cursor.getString(cursor.getColumnIndex("CorrectAnswer"));

                Questoes questoes = new Questoes(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listaQuestoes.add(questoes);
            }
            while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
        return listaQuestoes;
    }


}


