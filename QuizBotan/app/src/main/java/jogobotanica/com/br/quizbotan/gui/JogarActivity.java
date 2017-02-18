package jogobotanica.com.br.quizbotan.gui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jogobotanica.com.br.quizbotan.R;
import jogobotanica.com.br.quizbotan.dominio.Questoes;
import jogobotanica.com.br.quizbotan.negocio.JogarNegocio;

public class JogarActivity extends AppCompatActivity implements View.OnClickListener {

    final static long TEMPOSAIDA = 7000;// 7 segundos
    final static long INTERVALO = 1000;// 1 segundo
    private int valorProgresso = 0;

    //Controle
    ProgressBar progressBar;
    ImageView imageView;
    Button btnResp1, btnResp2, btnResp3, btnResp4;
    TextView txtQuestoes, txtPontos;


    private CountDownTimer contagemRegressiva;
    private List<Questoes> questoesJogo = new ArrayList<>(); //Todas questões
    private int indece = 0, pontos = 0, questoesAtual = 0, totalQuestoes,repostaCorreta;
    String nivelJogo = "";
//    BDHelper bdHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar);

        Bundle bundleExtra = getIntent().getExtras();
        if (!(bundleExtra.equals(null))){
            nivelJogo = bundleExtra.getString("NIVELJOGO");
        }
//
//        db = new DbHelper(this);

        txtPontos = (TextView)findViewById(R.id.txtPontos);
        txtQuestoes = (TextView)findViewById(R.id.txtQuestoes);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        imageView = (ImageView)findViewById(R.id.questoes_fotos);
        btnResp1 =(Button)findViewById(R.id.btnResposta1);
        btnResp2 =(Button)findViewById(R.id.btnResposta2);
        btnResp3 =(Button)findViewById(R.id.btnResposta3);
        btnResp4 =(Button)findViewById(R.id.btnResposta4);

        btnResp1.setOnClickListener(this);
        btnResp2.setOnClickListener(this);
        btnResp3.setOnClickListener(this);
        btnResp4.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Buscar repostas aleatórias
        JogarNegocio jogarNegocio = new JogarNegocio(this);
        questoesJogo = jogarNegocio.buscar(nivelJogo);

        //Contagem Regressiva das perguntas
        contagemRegressiva = new CountDownTimer(TEMPOSAIDA,INTERVALO) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(valorProgresso);
                valorProgresso++;
            }

            @Override
            public void onFinish() {
                contagemRegressiva.cancel();
                showQuestoes(++indece);
            }
        };
        showQuestoes(indece);

    }

    private void showQuestoes(int index) {
        if(index < totalQuestoes){
            questoesAtual++;
            txtQuestoes.setText(String.format("%d/%d",questoesAtual,totalQuestoes));
            progressBar.setProgress(0);
            valorProgresso = 0;

            int ImageId = this.getResources().getIdentifier(questoesJogo.get(index).getImage().toLowerCase(),"drawable",getPackageName());
            imageView.setBackgroundResource(ImageId);
            btnResp1.setText(questoesJogo.get(index).getResposta1());
            btnResp2.setText(questoesJogo.get(index).getResposta2());
            btnResp3.setText(questoesJogo.get(index).getResposta3());
            btnResp4.setText(questoesJogo.get(index).getResposta4());

            contagemRegressiva.start();
        }
        else{
            Intent intent = new Intent(this,FimJogoActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",pontos);
            dataSend.putInt("TOTAL",totalQuestoes);
            dataSend.putInt("CORRECT",repostaCorreta);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent irTelaPrincipal = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(irTelaPrincipal);
        finish();

    }

    @Override
    public void onClick(View view) {

        contagemRegressiva.cancel();
        if(indece < totalQuestoes){
            Button clickedButton = (Button)view;
            if(clickedButton.getText().equals(questoesJogo.get(indece).getRespostaCorreta()))
            {
                pontos+=10; // increase score
                repostaCorreta++ ; //increase correct answer
                showQuestoes(++indece);
            }
            else
                showQuestoes(++indece); // If choose right , just go to next question

            txtPontos.setText(String.format("%d",pontos));
        }

    }
}
