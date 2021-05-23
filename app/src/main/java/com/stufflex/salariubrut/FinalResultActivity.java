package com.stufflex.salariubrut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;

public class FinalResultActivity extends AppCompatActivity {

    // Declarations
    private Button btn_romania;
    private Button btn_diamond;
    private Button btn_play_final;
    private Button btn_euro;
    private Button btn_lei;
    private Button btn_sheqel;
    private Button btn_dollar;
    private Button btn_pound;
    private Button btn_rupee;
    private Button btn_ruble;

    private TextView txt_title;
    private TextView txt_play_final;
    private TextView txt_salariat;
    private TextView txt_salariu;
    private TextView txt_salariu_brut;
    private TextView txt_salariu_brut_rezultat;
    private TextView txt_asigurari_cas;
    private TextView txt_asigurari_cas_rezultat;
    private TextView txt_asigurari_cass;
    private TextView txt_asigurari_cass_rezultat;
    private TextView txt_impozit_pe_venit;
    private TextView txt_impozit_pe_venit_rezultat;
    private TextView txt_salariu_net;
    private TextView txt_salariu_net_rezultat;
    private TextView txt_afisaj_moneda;

    private TextInputLayout txt_input_layout;
    private TextInputEditText txt_input_edit_text;

    private ConstraintLayout salaryLayout;

    private AnimatorSet setLeiDownAndUp;
    private AnimatorSet setPoundDownAndUp;
    private AnimatorSet setRupeeDownAndUp;
    private AnimatorSet setEuroDownAndUp;
    private AnimatorSet setSheqelDownAndUp;
    private AnimatorSet setDollarDownAndUp;
    private AnimatorSet setRubleDownAndUp;

    private Animator scaleLeiDown;
    private Animator scalePoundDown;
    private Animator scaleRupeeDown;
    private Animator scaleEuroDown;
    private Animator scaleSheqelDown;
    private Animator scaleDollarDown;
    private Animator scaleRubleDown;

    private Animator scaleLeiUp;
    private Animator scalePoundUp;
    private Animator scaleRupeeUp;
    private Animator scaleEuroUp;
    private Animator scaleSheqelUp;
    private Animator scaleDollarUp;
    private Animator scaleRubleUp;

    private AnimationDrawable animationDrawableLei;
    private AnimationDrawable animationDrawablePound;
    private AnimationDrawable animationDrawableRupee;
    private AnimationDrawable animationDrawableEuro;
    private AnimationDrawable animationDrawableSheqel;
    private AnimationDrawable animationDrawableDollar;
    private AnimationDrawable animationDrawableRuble;

    private Handler handler_btn_currency_1;
    private Handler handler_btn_currency_2;
    private Handler handler_btn_currency_3;
    private Handler handler_btn_currency_4;

    private Runnable runnable_btn_currency_1;
    private Runnable runnable_btn_currency_2;
    private Runnable runnable_btn_currency_3;
    private Runnable runnable_btn_currency_4;

    private float salar_brut, cas_lei, cass_lei, impozit_lei, salar_net;
    private float venit_baza, deducere, nr_persoane_intretinere;

    private int salar_net_int;

    private ObjectAnimator colorAnimSalariuNet;
    private ObjectAnimator colorAnimSalariuNetRezultat;
    private ObjectAnimator colorAnimSalariuBrut;
    private ObjectAnimator colorAnimSalariuBrutRezultat;

    private static final long TOAST_TIMEOUT_MS = 2000;
    private static long lastToastTime = 0;

    private boolean isFunctieBaza_YES;
    private boolean isFunctieBaza_NO;
    private boolean isImpozitScutit_YES;
    private boolean isImpozitScutit_NO;

    private Animation anim_btn_romania;
    private Animation anim_btn_diamond;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        // Navbar-fullscreen
        hideNavigationBar();

        // Initializations
        btn_romania = findViewById(R.id.btn_romania);
        btn_diamond = findViewById(R.id.btn_diamond);
        btn_play_final = findViewById(R.id.btn_play_final);
        btn_euro = findViewById(R.id.btn_euro);
        btn_lei = findViewById(R.id.btn_lei);
        btn_sheqel = findViewById(R.id.btn_sheqel);
        btn_dollar = findViewById(R.id.btn_dollar);
        btn_pound = findViewById(R.id.btn_pound);
        btn_rupee = findViewById(R.id.btn_rupee);
        btn_ruble = findViewById(R.id.btn_ruble);

        txt_title = findViewById(R.id.txt_title);
        txt_play_final = findViewById(R.id.txt_play_final);
        txt_salariat = findViewById(R.id.txt_salariat);
        txt_salariu = findViewById(R.id.txt_salariu);
        txt_salariu_brut = findViewById(R.id.txt_salariu_brut);
        txt_salariu_brut_rezultat = findViewById(R.id.txt_salariu_brut_rezultat);
        txt_asigurari_cas = findViewById(R.id.txt_asigurari_cas);
        txt_asigurari_cas_rezultat = findViewById(R.id.txt_asigurari_cas_rezultat);
        txt_asigurari_cass = findViewById(R.id.txt_asigurari_cass);
        txt_asigurari_cass_rezultat = findViewById(R.id.txt_asigurari_cass_rezultat);
        txt_impozit_pe_venit = findViewById(R.id.txt_impozit_pe_venit);
        txt_impozit_pe_venit_rezultat = findViewById(R.id.txt_impozit_pe_venit_rezultat);
        txt_salariu_net = findViewById(R.id.txt_salariu_net);
        txt_salariu_net_rezultat = findViewById(R.id.txt_salariu_net_rezultat);
        txt_afisaj_moneda = findViewById(R.id.txt_afisaj_moneda);

        txt_input_layout = findViewById(R.id.txt_input_layout);
        txt_input_edit_text = findViewById(R.id.txt_input_edit_text);

        // Set animations
        anim_btn_diamond = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_btn_romania = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        btn_diamond.setAnimation(anim_btn_diamond);
        btn_romania.setAnimation(anim_btn_romania);

        // Calculus
        Intent getSalary = getIntent();
        salar_brut = getSalary.getFloatExtra("salariu", 0);

        txt_input_edit_text.setEnabled(false);
        txt_input_edit_text.setHint(String.format("%.0f", salar_brut));

        salaryLayout = findViewById(R.id.salaryLayout);

        // Boolean values
        isFunctieBaza_YES = getIntent().getExtras().getBoolean("isFunctieDeBaza_YES");
        isFunctieBaza_NO = getIntent().getExtras().getBoolean("isFunctieDeBaza_NO");
        isImpozitScutit_YES = getIntent().getExtras().getBoolean("isScutitDeImpozit_YES");
        isImpozitScutit_NO = getIntent().getExtras().getBoolean("isScutitDeImpozit_NO");

        nr_persoane_intretinere = getIntent().getIntExtra("nrPersoaneInIntretinere", 0);

        // Special guest | Animation for btn_lei
        scaleLeiDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleLeiDown.setTarget(btn_lei);

        scaleLeiUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setLeiDownAndUp = new AnimatorSet();
        setLeiDownAndUp.playSequentially(scaleLeiDown, scaleLeiUp);

        // Special guest | Animation for btn_pound
        scalePoundDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scalePoundDown.setTarget(btn_pound);

        scalePoundUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setPoundDownAndUp = new AnimatorSet();
        setPoundDownAndUp.playSequentially(scalePoundDown, scalePoundUp);

        // Special guest | Animation for btn_rupee
        scaleRupeeDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleRupeeDown.setTarget(btn_rupee);

        scaleRupeeUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setRupeeDownAndUp = new AnimatorSet();
        setRupeeDownAndUp.playSequentially(scaleRupeeDown, scaleRupeeUp);

        // Special guest | Animation for btn_euro
        scaleEuroDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleEuroDown.setTarget(btn_euro);

        scaleEuroUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setEuroDownAndUp = new AnimatorSet();
        setEuroDownAndUp.playSequentially(scaleEuroDown, scaleEuroUp);

        // Special guest | Animation for btn_sheqel
        scaleSheqelDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleSheqelDown.setTarget(btn_sheqel);

        scaleSheqelUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setSheqelDownAndUp = new AnimatorSet();
        setSheqelDownAndUp.playSequentially(scaleSheqelDown, scaleSheqelUp);

        // Special guest | Animation for btn_dollar
        scaleDollarDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleDollarDown.setTarget(btn_dollar);

        scaleDollarUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setDollarDownAndUp = new AnimatorSet();
        setDollarDownAndUp.playSequentially(scaleDollarDown, scaleDollarUp);

        // Special guest | Animation for btn_ruble
        scaleRubleDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleRubleDown.setTarget(btn_ruble);

        scaleRubleUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setRubleDownAndUp = new AnimatorSet();
        setRubleDownAndUp.playSequentially(scaleRubleDown, scaleRubleUp);

        // Awesome animations (for backgrounds)
        animationDrawableLei = (AnimationDrawable) btn_lei.getBackground();
        animationDrawableLei.setEnterFadeDuration(250);
        animationDrawableLei.setExitFadeDuration(500);

        animationDrawablePound = (AnimationDrawable) btn_pound.getBackground();
        animationDrawablePound.setEnterFadeDuration(250);
        animationDrawablePound.setExitFadeDuration(500);

        animationDrawableRupee = (AnimationDrawable) btn_rupee.getBackground();
        animationDrawableRupee.setEnterFadeDuration(250);
        animationDrawableRupee.setExitFadeDuration(500);

        animationDrawableEuro = (AnimationDrawable) btn_euro.getBackground();
        animationDrawableEuro.setEnterFadeDuration(250);
        animationDrawableEuro.setExitFadeDuration(500);

        animationDrawableSheqel = (AnimationDrawable) btn_sheqel.getBackground();
        animationDrawableSheqel.setEnterFadeDuration(250);
        animationDrawableSheqel.setExitFadeDuration(500);

        animationDrawableDollar = (AnimationDrawable) btn_dollar.getBackground();
        animationDrawableDollar.setEnterFadeDuration(250);
        animationDrawableDollar.setExitFadeDuration(500);

        animationDrawableRuble = (AnimationDrawable) btn_ruble.getBackground();
        animationDrawableRuble.setEnterFadeDuration(250);
        animationDrawableRuble.setExitFadeDuration(500);

        // Animations on start
        handler_btn_currency_1 = new  Handler();
        runnable_btn_currency_1 = new Runnable() {
            @Override
            public void run() {
                btn_lei.setVisibility(View.VISIBLE);

                setLeiDownAndUp.start();

                animationDrawableLei.start();
            }
        };
        handler_btn_currency_1.postDelayed(runnable_btn_currency_1, 300);

        handler_btn_currency_2 = new  Handler();
        runnable_btn_currency_2 = new Runnable() {
            @Override
            public void run() {
                btn_pound.setVisibility(View.VISIBLE);
                btn_rupee.setVisibility(View.VISIBLE);

                setPoundDownAndUp.start();
                setRupeeDownAndUp.start();

                animationDrawablePound.start();
                animationDrawableRupee.start();
            }
        };
        handler_btn_currency_2.postDelayed(runnable_btn_currency_2, 600);

        handler_btn_currency_3 = new  Handler();
        runnable_btn_currency_3 = new Runnable() {
            @Override
            public void run() {
                btn_euro.setVisibility(View.VISIBLE);
                btn_sheqel.setVisibility(View.VISIBLE);

                setEuroDownAndUp.start();
                setSheqelDownAndUp.start();

                animationDrawableEuro.start();
                animationDrawableSheqel.start();
            }
        };
        handler_btn_currency_3.postDelayed(runnable_btn_currency_3, 900);

        handler_btn_currency_4 = new  Handler();
        runnable_btn_currency_4 = new Runnable() {
            @Override
            public void run() {
                btn_dollar.setVisibility(View.VISIBLE);
                btn_ruble.setVisibility(View.VISIBLE);

                setDollarDownAndUp.start();
                setRubleDownAndUp.start();

                animationDrawableDollar.start();
                animationDrawableRuble.start();
            }
        };
        handler_btn_currency_4.postDelayed(runnable_btn_currency_4, 1200);

        // Text animation (beta version)
        colorAnimSalariuNet = ObjectAnimator.ofInt(txt_salariu_net, "textColor",
                Color.BLACK, Color.GREEN);
        colorAnimSalariuNet.setEvaluator(new ArgbEvaluator());
        colorAnimSalariuNet.setDuration(4000);
        colorAnimSalariuNet.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimSalariuNet.setRepeatCount(Animation.INFINITE);
        colorAnimSalariuNet.setInterpolator(new LinearInterpolator());
        colorAnimSalariuNet.start();

        colorAnimSalariuNetRezultat = ObjectAnimator.ofInt(txt_salariu_net_rezultat, "textColor",
                Color.BLACK, Color.GREEN);
        colorAnimSalariuNetRezultat.setEvaluator(new ArgbEvaluator());
        colorAnimSalariuNetRezultat.setDuration(4000);
        colorAnimSalariuNetRezultat.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimSalariuNetRezultat.setRepeatCount(Animation.INFINITE);
        colorAnimSalariuNetRezultat.setInterpolator(new LinearInterpolator());
        colorAnimSalariuNetRezultat.start();

        colorAnimSalariuBrut = ObjectAnimator.ofInt(txt_salariu_brut, "textColor",
                Color.BLACK, Color.RED);
        colorAnimSalariuBrut.setEvaluator(new ArgbEvaluator());
        colorAnimSalariuBrut.setDuration(4000);
        colorAnimSalariuBrut.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimSalariuBrut.setRepeatCount(Animation.INFINITE);
        colorAnimSalariuBrut.setInterpolator(new LinearInterpolator());
        colorAnimSalariuBrut.start();

        colorAnimSalariuBrutRezultat = ObjectAnimator.ofInt(txt_salariu_brut_rezultat, "textColor",
                Color.BLACK, Color.RED);
        colorAnimSalariuBrutRezultat.setEvaluator(new ArgbEvaluator());
        colorAnimSalariuBrutRezultat.setDuration(4000);
        colorAnimSalariuBrutRezultat.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimSalariuBrutRezultat.setRepeatCount(Animation.INFINITE);
        colorAnimSalariuBrutRezultat.setInterpolator(new LinearInterpolator());
        colorAnimSalariuBrutRezultat.start();

        // Calculus call; can find a better place than bottom...
        CalculLei();

        // If impozit is negative, put 0 because otherwise it will be irrelevant
        if (impozit_lei < 0) {
            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));
        }

    }

    // Hide the navigation bar and make full screen all app
    private void hideNavigationBar() {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }

    // When I exit for a moment from the app and I'll come back, the same effect must be continue
    @Override
    protected void onResume() {
        super.onResume();

        hideNavigationBar();
    }

    // Reset the app
    public void Reset(View v){
        Intent appReset = new Intent(this, MainActivity.class);
        startActivity(appReset);

        Toast toast = Toast.makeText(this, "Restart", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }
    }

    public void ClickOnDollar(View v){
        Toast toast = Toast.makeText(this, "USD (dolar american)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_dolari);
    }

    public void ClickOnEuro(View v){
        Toast toast = Toast.makeText(this, "EUR (euro)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_euro);
    }

    public void ClickOnPound(View v){
        Toast toast = Toast.makeText(this, "GBP (lira sterlină)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_lire);
    }

    public void ClickOnLei(View v){
        Toast toast = Toast.makeText(this, "Leu (românesc)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_lei);

        CalculLei();
    }

    public void ClickOnRupee(View v){
        Toast toast = Toast.makeText(this, "INR (rupia indiană)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_rupii);
    }

    public void ClickOnSheqel(View v){
        Toast toast = Toast.makeText(this, "ILS (sheqel israelian)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_sheqeli);
    }

    public void ClickOnRuble(View v) throws IOException {
        Toast toast = Toast.makeText(this, "RUB (rubla rusească)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_ruble);

//        getWebsite();
    }

    @SuppressLint("DefaultLocale")
    public void CalculLei(){
        // Calculus
        txt_salariu_brut_rezultat.setText(String.format("%.0f", salar_brut));

        cas_lei = salar_brut * (25.0f / 100);
        txt_asigurari_cas_rezultat.setText(String.format("%.0f", cas_lei));

        cass_lei = salar_brut * (10.0f / 100);
        txt_asigurari_cass_rezultat.setText(String.format("%.0f", cass_lei));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

    }
    private void Calculeaza(int persoane, int nuStiuCeInseamna){
        venit_baza = salar_brut - cas_lei - cass_lei - 510;

        impozit_lei = venit_baza * 10.0f / 100;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
        salar_net_int = (int) salar_net;

        txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
    };
    public void FunctieBaza_DA_ScutitImpozit_NU(){
        /*

        FUNCTIE DE BAZA = DA | SCUTIT DE IMPOZIT = NU

         */

        // Plafon 575 - 1950 lei | functie_de_baza = DA | scutit_de_impozit = NU
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
Calculeaza(0, 510);
// linii urmatoare au fost inlocuite de functia de mai sus
          // tot asa trebuie procedat cu toate liniile urmatoare existente in ELSE
//            venit_baza = salar_brut - cas_lei - cass_lei - 510;
//
 //           impozit_lei = venit_baza * 10.0f / 100;
 //           txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

      //      salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
     //       salar_net_int = (int) salar_net;

       //     txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 670;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 830;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 990;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1310;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 495;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 655;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 815;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 975;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1295;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 480;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 640;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 800;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 960;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1280;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 465;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 625;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 785;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 945;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1265;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 450;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 610;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 770;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 930;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1250;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 435;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 595;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 755;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 915;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1235;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 420;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 580;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 740;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 900;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1220;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 405;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 565;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 725;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 885;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1205;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 390;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 550;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 710;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 870;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1190;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 375;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 535;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 695;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 855;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1175;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 360;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 520;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 680;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 840;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1160;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 345;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 505;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 665;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 825;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1145;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 330;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 490;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 650;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 810;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1130;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 315;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 475;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 635;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 795;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1115;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 300;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 460;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 620;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 780;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1100;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 285;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 445;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 605;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 765;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1085;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 270;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 430;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 590;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 750;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1070;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 255;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 415;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 575;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 735;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1055;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 240;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 400;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 560;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 720;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1040;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 225;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 385;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 545;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 705;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1025;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 210;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 370;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 530;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 690;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1010;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 195;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 355;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 515;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 675;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 995;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 180;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 340;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 500;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 660;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 980;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 165;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 325;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 485;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 645;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 965;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 150;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 310;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 470;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 630;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 950;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 135;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 295;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 455;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 615;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 935;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 120;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 280;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 440;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 600;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 920;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 105;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 265;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 425;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 585;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 905;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 90;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 250;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 410;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 570;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 890;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 75;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 235;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 395;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 555;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 875;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 60;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 220;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 380;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 540;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 860;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 45;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 205;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 365;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 525;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 845;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 30;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 190;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 350;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 510;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 830;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 15;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 175;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 335;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 495;
            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 815;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon > 3600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3600) && isFunctieBaza_YES && isImpozitScutit_NO) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

    }

    public void FunctieBaza_DA_ScutitImpozit_DA(){
        /*

        FUNCTIE DE BAZA = DA | SCUTIT DE IMPOZIT = DA

         */

        // Plafon 575 - 1950 lei | functie_de_baza = DA | scutit_de_impozit = DA
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 510;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 670;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 830;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 990;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1310;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 495;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 655;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 815;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 975;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1295;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 480;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 640;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 800;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 960;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1280;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 465;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 625;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 785;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 945;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1265;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 450;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 610;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 770;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 930;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1250;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 435;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 595;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 755;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 915;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1235;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 420;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 580;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 740;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 900;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1220;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 405;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 565;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 725;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 885;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1205;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 390;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 550;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 710;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 870;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1190;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 375;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 535;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 695;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 855;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1175;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 360;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 520;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 680;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 840;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1160;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 345;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 505;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 665;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 825;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1145;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 330;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 490;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 650;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 810;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1130;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 315;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 475;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 635;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 795;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1115;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 300;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 460;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 620;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 780;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1100;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 285;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 445;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 605;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 765;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1085;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 270;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 430;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 590;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 750;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1070;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 255;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 415;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 575;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 735;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1055;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 240;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 400;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 560;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 720;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1040;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 225;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 385;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 545;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 705;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1025;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 210;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 370;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 530;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 690;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 1010;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 195;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 355;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 515;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 675;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 995;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 180;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 340;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 500;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 660;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 980;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 165;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 325;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 485;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 645;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 965;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 150;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 310;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 470;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 630;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 950;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 135;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 295;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 455;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 615;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 935;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 120;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 280;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 440;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 600;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 920;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 105;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 265;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 425;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 585;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 905;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 90;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 250;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 410;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 570;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 890;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 75;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 235;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 395;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 555;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 875;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 60;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 220;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 380;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 540;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 860;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 45;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 205;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 365;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 525;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 845;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 30;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 190;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 350;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 510;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 830;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 15;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 175;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 335;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 495;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei - 815;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon > 3600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3600) && isFunctieBaza_YES && isImpozitScutit_YES) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

    }

    public void FunctieBaza_NU_ScutitImpozit_DA(){
        /*

        FUNCTIE DE BAZA = NU | SCUTIT DE IMPOZIT = DA

         */

        // Plafon 575 - 1950 lei | functie_de_baza = NU | scutit_de_impozit = DA
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei ;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon > 3600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3600) && isFunctieBaza_NO && isImpozitScutit_YES) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
    }

    public void FunctieBaza_NU_ScutitImpozit_NU(){
        /*

        FUNCTIE DE BAZA = NU | SCUTIT DE IMPOZIT = NU

         */

        // Plafon 575 - 1950 lei | functie_de_baza = NU | scutit_de_impozit = NU
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = NU | scutit_de_impozit = NO
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei ;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

        // Plafon > 3600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3600) && isFunctieBaza_NO && isImpozitScutit_NO) {

            venit_baza = salar_brut - cas_lei - cass_lei;

            impozit_lei = venit_baza * 10.0f / 100;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

//            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }
    }

    public void GoToMainActivity(View v){
        super.onBackPressed();
        txt_title.setTextColor(Color.WHITE);

    }

//    public void getWebsite() {
//        String url = "https://www.cursbnr.ro/";
//
//
//        Document doc = (Document) Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10").get();
//        Elements euroCurrency = (Elements) doc.getElementById("calendar");
//
//        String title = doc.title();
//
//        txt_salariu_brut.setText(String.valueOf(euroCurrency));
//
//
//
//    }
}