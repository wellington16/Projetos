package jogobotanica.com.br.quizbotan.negocio;

import android.content.Context;

import java.util.List;

import jogobotanica.com.br.quizbotan.dominio.Questoes;
import jogobotanica.com.br.quizbotan.persistencia.JogarPersistencia;

/**
 * Created by WELLINGTON on 13/02/2017.
 */

public class JogarNegocio {

    private Context context;

    public JogarNegocio(Context contexto)
    {
        this.context = contexto;
    }

    public final List<Questoes> buscar(String nivelJ ){
            JogarPersistencia jogarPersistencia = new JogarPersistencia(context);
            return jogarPersistencia.buscar(nivelJ);
    }

}
