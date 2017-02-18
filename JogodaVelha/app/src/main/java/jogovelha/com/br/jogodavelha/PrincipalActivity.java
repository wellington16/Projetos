package jogovelha.com.br.jogodavelha;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.prefs.Preferences;

import static java.lang.String.valueOf;

public class PrincipalActivity extends AppCompatActivity {


    private final String QUADRADO = "quadrado";
    private final String  BOLA = "O";
    private final String X = "X";
    private String ultimoJodado = "X";

    private View view;
    private Button sair;
    private Button play;
    private int contador = 0;

    int[][] estadoFinal = new int[][]{

            //linhas
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},

            //colunas
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},

            //diágonas
            {1, 5, 9},
            {3, 5, 7},
    };


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getUltimoJodado() {
        return ultimoJodado;
    }

    public void setUltimoJodado(String ultimoJodado) {
        this.ultimoJodado = ultimoJodado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_principal, null));
        setContentView(getView());

        RadioButton radioButton1= (RadioButton) findViewById(R.id.rbO);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.rbX);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getView().getContext(), "Clique em 'jogar' para iniciar", Toast.LENGTH_LONG).show();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getView().getContext(), "Clique em 'jogar' para iniciar", Toast.LENGTH_LONG).show();
            }
        });
    }


    public boolean fim(){

        for (int i = 0; i<=7; ++i) {
            String receberQ1 = getQuadrado(estadoFinal[i][0]).getText().toString();
            String receberQ2 = getQuadrado(estadoFinal[i][1]).getText().toString();
            String receberQ3 = getQuadrado(estadoFinal[i][2]).getText().toString();

            if ((!receberQ1.equals("")) &&
                    (!receberQ1.equals("")) &&
                    (!receberQ1.equals(""))) {
                if (receberQ1.equals(receberQ2) && (receberQ2.equals(receberQ3))) {

                    setCorQuadrados(estadoFinal[i][0], R.color.vermelho);
                    setCorQuadrados(estadoFinal[i][1], R.color.vermelho);
                    setCorQuadrados(estadoFinal[i][2], R.color.vermelho);

                    Toast.makeText(getView().getContext(), "Fim De Jogo", Toast.LENGTH_SHORT).show();
                    return true;

                }
            }

        }
        return false;
    }

    private final void empatou(int contador) {
        limparCampos();
        Toast.makeText(getView().getContext(), "Opa! Empatou, clique em jogar para reiniciar o jogo.", Toast.LENGTH_LONG).show();
    }

    public Button getQuadrado(int tagNumero){
        return (Button) getView().findViewWithTag(QUADRADO + tagNumero);
    }

    public void novoJogo(View view){
        RadioButton rx =(RadioButton)getView().findViewById(R.id.rbX);
        RadioButton ro =(RadioButton)getView().findViewById(R.id.rbO);

        if(!ro.isChecked() && !rx.isChecked()){
            Toast.makeText(getView().getContext(), "Escolha entre 'X' ou 'O' antes de clicar em 'JOGAR'.", Toast.LENGTH_LONG).show();
        }else{
            setHabiltarBotao(true);
            setColorPreto();
            limparCampos();
            contador = 0;
            checagemRadioButton(rx, ro);
        }

    }

    private boolean checagemRadioButton(RadioButton rx, RadioButton ro) {
        if(rx.isChecked()){
            setUltimoJodado(BOLA);
            return true;
        }else {
            if(ro.isChecked()){
                setUltimoJodado(X);
                return true;
            }
        }
        return false;
    }

    private void limparCampos() {
        for (int interador = 1; interador <= 9 ; ++interador){
            if (getQuadrado(interador) != null){
                getQuadrado(interador).setText("");
            }
        }
    }

    public void setHabiltarBotao(boolean b){
        for(int interador = 1; interador <= 9; ++interador){
            if (getQuadrado(interador)!= null) {
                getQuadrado(interador).setEnabled(b);
            }
        }
    }

    public final void setCorQuadrados(int btn, int corX){
        getQuadrado(btn).setTextColor(ContextCompat.getColor(getApplication(), corX));
    }

    public final void setColorPreto(){

        for (int i = 0; i <= 9; ++i){
            if (getQuadrado(i) != null){
                setCorQuadrados(i, R.color.preto);
            }
        }
    }

    public void clickQuadrado(View view){
        if (!fim()) {
            contador++;
            if (contador >= 9) {
                empatou(contador);
            }
            jogaBolaOuX((Button) view);
            fim();
        }
    }

    private void jogaBolaOuX(Button view) {
        if (view.getText().equals("")) {
            if (getUltimoJodado().equals(X)) {
                view.setText(BOLA);
                setUltimoJodado(BOLA);
            } else {
                view.setText(X);
                setUltimoJodado(X);
            }
        } else {
            Toast.makeText(getView().getContext(), "Escolha outro quadrado", Toast.LENGTH_LONG).show();
        }
    }

    public void sair(View view){
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
