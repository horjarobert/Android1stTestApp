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
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    private ObjectAnimator colorAnimSalariuNet;
    private ObjectAnimator colorAnimSalariuNetRezultat;
    private ObjectAnimator colorAnimSalariuBrut;
    private ObjectAnimator colorAnimSalariuBrutRezultat;

    private static final long TOAST_TIMEOUT_MS = 2000;
    private static long lastToastTime = 0;

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

        // Calculus
        Intent getSalary = getIntent();
        salar_brut = getSalary.getFloatExtra("salariu", 0);

        CalculLei();

        txt_input_edit_text.setEnabled(false);
        txt_input_edit_text.setHint(String.format("%.0f", salar_brut));

        salaryLayout = findViewById(R.id.salaryLayout);

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

    public void ClickOnRuble(View v){
        Toast toast = Toast.makeText(this, "RUB (rubla rusească)", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

        txt_salariu.setText(R.string.str_salariu_ruble);
    }

    @SuppressLint("DefaultLocale")
    public void CalculLei(){
        // Calculus
        txt_salariu_brut_rezultat.setText(String.format("%.0f", salar_brut));

        cas_lei = salar_brut * (25.0f / 100);
        txt_asigurari_cas_rezultat.setText(String.format("%.0f", cas_lei));

        cass_lei = salar_brut * (10.0f / 100);
        txt_asigurari_cass_rezultat.setText(String.format("%.0f", cass_lei));

        impozit_lei = salar_brut * (10.0f / 100);
        txt_impozit_pe_venit_rezultat.setText(String.format("%.0f", impozit_lei));

        salar_net = salar_brut - (cas_lei + cass_lei + impozit_lei);
        txt_salariu_net_rezultat.setText(String.format("%.0f", salar_net));

    }

    public void GoToMainActivity(View v){
        super.onBackPressed();
        txt_title.setTextColor(Color.WHITE);
    }
}