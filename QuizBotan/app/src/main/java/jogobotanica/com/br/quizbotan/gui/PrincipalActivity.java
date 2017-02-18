package jogobotanica.com.br.quizbotan.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import jogobotanica.com.br.quizbotan.R;
import jogobotanica.com.br.quizbotan.infra.Enum;

public class PrincipalActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView nivelJogo;
    Button btnjogar,btnPontos,btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        nivelJogo = (TextView) findViewById(R.id.txtNivelJogo);
        btnjogar = (Button) findViewById(R.id.btnJogar);
        btnPontos = (Button) findViewById(R.id.btnPontos);
        btnSair = (Button) findViewById(R.id.btnSair);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                        nivelJogo.setText(Enum.NIVELJOGO.FACIL.toString());
                        break;
                    case 1:
                        nivelJogo.setText(Enum.NIVELJOGO.MEDIO.toString());
                        break;
                    case 2:
                        nivelJogo.setText(Enum.NIVELJOGO.AVANÇADO.toString());
                        break;
                    case 3:
                        nivelJogo.setText(Enum.NIVELJOGO.HARDSET.toString());
                        break;
                    default:
                        nivelJogo.setText(Enum.NIVELJOGO.FACIL.toString());
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnjogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irJogar = new Intent(getApplicationContext(), JogarActivity.class);
                irJogar.putExtra("NIVELJOGO", getNivelJogoAtual()); // Send Mode to Playing page
                startActivity(irJogar);
                finish();

            }
        });
    }

    private String getNivelJogoAtual() {
        if(seekBar.getProgress()==0)
            return Enum.NIVELJOGO.FACIL.toString();
        else if(seekBar.getProgress()==1)
            return Enum.NIVELJOGO.MEDIO.toString();
        else if(seekBar.getProgress()==2)
            return Enum.NIVELJOGO.AVANÇADO.toString();
        else
            return Enum.NIVELJOGO.HARDSET.toString();

    }
    public final void finalizar(View v){
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
