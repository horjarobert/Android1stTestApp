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

    private void CalculeazaFunctieBaza_DA_ScutitImpozit_NU(int deducere_personala){
        venit_baza = salar_brut - cas_lei - cass_lei - deducere_personala;

        impozit_lei = venit_baza * 10.0f / 100;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        if (impozit_lei < 0) {
            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        } else {
            salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        }

    }

    public void FunctieBaza_DA_ScutitImpozit_NU(){
        /*

        FUNCTIE DE BAZA = DA | SCUTIT DE IMPOZIT = NU

         */

        // Plafon 575 - 1950 lei | functie_de_baza = DA | scutit_de_impozit = NU
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(510);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(670);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(830);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(990);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1310);
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1310);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(655);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(815);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(975);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1295);
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(480);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(640);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(800);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(960);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1280);
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(465);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(625);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(785);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(945);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1265);
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(450);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(610);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(770);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(930);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1250);
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(435);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(595);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(755);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(915);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1235);
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(420);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(580);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(740);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(900);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1220);
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(405);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(565);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(725);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(885);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1205);
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(390);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(550);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(710);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(870);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1190);
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(375);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(535);

        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(695);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(855);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1175);
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(360);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(520);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(680);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(840);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1160);
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(345);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(505);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(665);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(825);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1145);
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(330);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(490);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(650);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(810);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1130);
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(315);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(475);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(635);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(795);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(115);
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(300);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(460);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(620);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(780);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1100);
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(285);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(445);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(605);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(765);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1085);
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(270);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(430);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(590);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(750);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1070);
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(255);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(415);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(575);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(735);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1055);
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(240);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(400);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(560);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(720);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1040);
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(225);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(385);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(545);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(705);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1025);
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(210);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(370);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(530);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(690);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(1010);
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(195);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(355);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(515);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(675);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(995);
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(180);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(340);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(500);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(660);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(980);
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(165);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(325);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(485);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(645);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(965);
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(150);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(310);

        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(470);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(630);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(950);
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(135);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(295);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(455);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(615);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(935);
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(120);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(280);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(440);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(600);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(920);
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(105);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(265);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(425);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(585);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(905);
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(90);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(250);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(410);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(570);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(890);
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(75);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(235);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(395);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(555);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(875);
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(60);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(220);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(380);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(540);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(860);
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(45);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(205);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(365);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(525);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(845);
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(30);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(190);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(350);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(510);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(830);
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(15);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(175);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(335);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(495);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(815);
        }

        // Plafon > 3600 lei | functie_de_baza = DA | scutit_de_impozit = NU
        else if ((salar_brut > 3600) && isFunctieBaza_YES && isImpozitScutit_NO) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_NU(0);
        }

    }

    private void CalculeazaFunctieBaza_DA_ScutitImpozit_DA(int deducere_personala){
        venit_baza = salar_brut - cas_lei - cass_lei - deducere_personala;

        impozit_lei = 0;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
        salar_net_int = (int) salar_net;

        txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));

    }

    public void FunctieBaza_DA_ScutitImpozit_DA(){
        /*

        FUNCTIE DE BAZA = DA | SCUTIT DE IMPOZIT = DA

         */

        // Plafon 575 - 1950 lei | functie_de_baza = DA | scutit_de_impozit = DA
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(510);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(670);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(830);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(990);
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1310);
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(495);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(655);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(815);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(975);
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1295);
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(480);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(640);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(800);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(960);
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1280);
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(465);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(625);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(785);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(945);
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1265);
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(450);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(610);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(770);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(930);
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1250);
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(435);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(595);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(755);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(915);
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1235);
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(420);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(580);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(740);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(900);
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1220);
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(405);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(565);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(725);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(885);
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1205);
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(390);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(550);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(710);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(870);
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1190);
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(375);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(535);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(695);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(855);
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1175);
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(360);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(520);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(680);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(840);
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1160);
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(345);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(505);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(665);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(825);
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1145);
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(330);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(490);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(650);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(810);
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1130);
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(315);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(475);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(635);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(795);
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1115);
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(300);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(460);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(620);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(780);
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1100);
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(285);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(445);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(605);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(765);
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1085);
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(270);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(430);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(590);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(750);
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1070);
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(255);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(415);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(575);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(735);
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1055);
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(240);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(400);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(560);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(720);
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1040);
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(225);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(385);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(545);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(705);
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1025);
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(210);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(370);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(530);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(690);
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(1010);
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(195);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(355);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(515);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(675);
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(995);
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(180);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(340);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(500);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(660);
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(980);
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(165);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(352);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(485);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(645);
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(965);
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(150);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(310);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(470);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(630);
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(950);
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(135);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(295);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(455);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(615);
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(935);
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(120);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(280);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(440);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(600);
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(920);
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(105);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(265);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(425);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(585);
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(905);
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(90);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(250);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(410);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(570);
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(890);
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(75);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(235);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(395);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(555);
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(875);
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(60);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(220);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(380);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(540);
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(860);
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(45);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(205);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(365);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(525);
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(845);
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(30);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(190);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(350);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(510);
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(830);
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(15);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(175);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(335);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(495);
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_YES && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(815);
        }

        // Plafon > 3600 lei | functie_de_baza = DA | scutit_de_impozit = DA
        else if ((salar_brut >= 3600) && isFunctieBaza_YES && isImpozitScutit_YES) {
            CalculeazaFunctieBaza_DA_ScutitImpozit_DA(0);
        }

    }

    private void CalculeazaFunctieBaza_NU_ScutitImpozit_DA(){

        venit_baza = salar_brut - cas_lei - cass_lei;

        impozit_lei = 0;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
        salar_net_int = (int) salar_net;

        txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));

    }

    public void FunctieBaza_NU_ScutitImpozit_DA(){
        /*

        FUNCTIE DE BAZA = NU | SCUTIT DE IMPOZIT = DA

         */

        // Plafon 575 - 1950 lei | functie_de_baza = NU | scutit_de_impozit = DA
        if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }

        // Plafon > 3600 lei | functie_de_baza = NU | scutit_de_impozit = DA
        else if ((salar_brut >= 3600) && isFunctieBaza_NO && isImpozitScutit_YES) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_DA();
        }
    }

    private void CalculeazaFunctieBaza_NU_ScutitImpozit_NU(){
        venit_baza = salar_brut - cas_lei - cass_lei;

        impozit_lei = venit_baza * 10.0f / 100;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        if (impozit_lei < 0) {
            impozit_lei = 0;
            txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

            salar_net = salar_brut - (cas_lei + cass_lei);
            salar_net_int = (int) salar_net;

            txt_salariu_net_rezultat.setText(String.valueOf(salar_net_int));
        } else {
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
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 575 && salar_brut <= 1950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 1951 - 2000 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 1951 && salar_brut <= 2000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2001 - 2050 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2001 && salar_brut <= 2050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2051 - 2100 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2051 && salar_brut <= 2100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2101 - 2150 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2101 && salar_brut <= 2150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2151 - 2200 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2151 && salar_brut <= 2200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2201 - 2250 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2201 && salar_brut <= 2250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2251 - 2300 lei | functie_de_baza = NU | scutit_de_impozit = NO
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2251 && salar_brut <= 2300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2301 - 2350 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2301 && salar_brut <= 2350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2351 - 2400 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2351 && salar_brut <= 2400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2401 - 2450 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2401 && salar_brut <= 2450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2451 - 2500 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2451 && salar_brut <= 2500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2501 - 2550 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2501 && salar_brut <= 2550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2551 - 2600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2551 && salar_brut <= 2600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2601 - 2650 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2601 && salar_brut <= 2650) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2651 - 2700 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2651 && salar_brut <= 2700) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2701 - 2750 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2701 && salar_brut <= 2750) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2751 - 2800 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2751 && salar_brut <= 2800) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2801 - 2850 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2801 && salar_brut <= 2850) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2851 - 2900 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2851 && salar_brut <= 2900) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2901 - 2950 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2901 && salar_brut <= 2950) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 2951 - 3000 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 2951 && salar_brut <= 3000) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3001 - 3050 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3001 && salar_brut <= 3050) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3051 - 3100 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3051 && salar_brut <= 3100) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3101 - 3150 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3101 && salar_brut <= 3150) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3151 - 3200 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3151 && salar_brut <= 3200) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3201 - 3250 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3201 && salar_brut <= 3250) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3251 - 3300 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3251 && salar_brut <= 3300) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3301 - 3350 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3301 && salar_brut <= 3350) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3351 - 3400 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3351 && salar_brut <= 3400) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3401 - 3450 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3401 && salar_brut <= 3450) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3451 - 3500 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3451 && salar_brut <= 3500) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3501 - 3550 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_YES && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3501 && salar_brut <= 3550) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon 3551 - 3600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 0)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 1)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 2)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere == 3)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }
        else if ((salar_brut >= 3551 && salar_brut <= 3600) && isFunctieBaza_NO && isImpozitScutit_NO && (nr_persoane_intretinere >= 4)) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
        }

        // Plafon > 3600 lei | functie_de_baza = NU | scutit_de_impozit = NU
        else if ((salar_brut >= 3600) && isFunctieBaza_NO && isImpozitScutit_NO) {
            CalculeazaFunctieBaza_NU_ScutitImpozit_NU();
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