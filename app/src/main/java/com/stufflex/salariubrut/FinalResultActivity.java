package com.stufflex.salariubrut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FinalResultActivity extends AppCompatActivity {

    // Declarations
    private Button btn_romania, btn_diamond, btn_play_final, btn_euro, btn_lei, btn_franc, btn_dollar, btn_pound, btn_rupee, btn_ruble;

    private TextView txt_title, txt_play_final, txt_salariat, txt_salariu, txt_salariu_brut, txt_salariu_brut_rezultat, txt_asigurari_cas, txt_asigurari_cas_rezultat, txt_asigurari_cass,
            txt_asigurari_cass_rezultat, txt_impozit_pe_venit, txt_impozit_pe_venit_rezultat, txt_salariu_net, txt_salariu_net_rezultat, txt_afisaj_moneda, txt_info_change;

    private TextInputLayout txt_input_layout;
    private TextInputEditText txt_input_edit_text;

    private ConstraintLayout salaryLayout;

    private AnimatorSet setLeiDownAndUp, setPoundDownAndUp, setRupeeDownAndUp, setEuroDownAndUp, setFrancDownAndUp, setDollarDownAndUp, setRubleDownAndUp;

    private Animator scaleLeiDown, scalePoundDown, scaleRupeeDown, scaleEuroDown, scaleFrancDown, scaleDollarDown, scaleRubleDown;

    private Animator scaleLeiUp, scalePoundUp, scaleRupeeUp, scaleEuroUp, scaleFrancUp, scaleDollarUp, scaleRubleUp;

    private AnimationDrawable animationDrawableLei, animationDrawablePound, animationDrawableRupee, animationDrawableEuro, animationDrawableFranc, animationDrawableDollar, animationDrawableRuble;

    private Handler handler_btn_currency_1, handler_btn_currency_2, handler_btn_currency_3, handler_btn_currency_4;

    private Runnable runnable_btn_currency_1, runnable_btn_currency_2, runnable_btn_currency_3, runnable_btn_currency_4;

    private float salar_brut, cas_lei, cass_lei, impozit_lei, salar_net;
    private float salar_brut_in_euro, cas_in_euro, cass_in_euro, impozit_in_euro, salar_net_in_euro;
    private float salar_brut_in_dolari, cas_in_dolari, cass_in_dolari, impozit_in_dolari, salar_net_in_dolari;
    private float salar_brut_in_franci, cas_in_franci, cass_in_franci, impozit_in_franci, salar_net_in_franci;
    private float salar_brut_in_ruble, cas_in_ruble, cass_in_ruble, impozit_in_ruble, salar_net_in_ruble;
    private float salar_brut_in_lire, cas_in_lire, cass_in_lire, impozit_in_lire, salar_net_in_lire;
    private float salar_brut_in_rupii, cas_in_rupii, cass_in_rupii, impozit_in_rupii, salar_net_in_rupii;

    private float venit_baza, deducere, nr_persoane_intretinere;

    private int salar_net_int;

    private ObjectAnimator colorAnimSalariuNet, colorAnimSalariuNetRezultat, colorAnimSalariuBrut, colorAnimSalariuBrutRezultat;

    private static final long TOAST_TIMEOUT_MS = 1500;
    private static long lastToastTime = 0;

    private boolean isFunctieBaza_YES, isFunctieBaza_NO, isImpozitScutit_YES, isImpozitScutit_NO;

    private Animation anim_btn_romania, anim_btn_diamond;

    private String url_bnr = "https://www.cursbnr.ro/";
    private String etalonInDolari, etalonInEuro, etalonInLei, etalonInFranci, etalonInRuble, etalonInLire, etalonInRupii;
    private float float_etalonInDolari, float_etalonInEuro, float_etalonInLei, float_etalonInFranci, float_etalonInRuble, float_etalonInLire, float_etalonInRupii;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        // Navbar-fullscreen
        hideNavigationBar();

        // Disable screenshot option by using FLAG_SECURE
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        // Initializations
        btn_romania = findViewById(R.id.btn_romania);
        btn_diamond = findViewById(R.id.btn_diamond);
        btn_play_final = findViewById(R.id.btn_play_final);
        btn_euro = findViewById(R.id.btn_euro);
        btn_lei = findViewById(R.id.btn_lei);
        btn_franc = findViewById(R.id.btn_franc);
        btn_dollar = findViewById(R.id.btn_dollar);
        btn_pound = findViewById(R.id.btn_pound);
        btn_rupee = findViewById(R.id.btn_rupee);
        btn_ruble = findViewById(R.id.btn_ruble);

        txt_title = findViewById(R.id.txt_title);
        txt_play_final = findViewById(R.id.txt_play_final);
        txt_salariat = findViewById(R.id.txt_salariat);
        txt_salariu = findViewById(R.id.txt_salariu);
        txt_salariu_brut = findViewById(R.id.txt_salariul_brut);
        txt_salariu_brut_rezultat = findViewById(R.id.txt_salariu_brut_rezultat);
        txt_asigurari_cas = findViewById(R.id.txt_asigurari_cas);
        txt_asigurari_cas_rezultat = findViewById(R.id.txt_asigurari_cas_rezultat);
        txt_asigurari_cass = findViewById(R.id.txt_asigurari_cass);
        txt_asigurari_cass_rezultat = findViewById(R.id.txt_asigurari_cass_rezultat);
        txt_impozit_pe_venit = findViewById(R.id.txt_impozit_pe_venit);
        txt_impozit_pe_venit_rezultat = findViewById(R.id.txt_impozit_pe_venit_rezultat);
        txt_salariu_net = findViewById(R.id.txt_salariul_net);
        txt_salariu_net_rezultat = findViewById(R.id.txt_salariu_net_rezultat);
        txt_afisaj_moneda = findViewById(R.id.txt_afisaj_moneda);
        txt_info_change = findViewById(R.id.txt_info_change);

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

        // Click on info buttons
        btn_romania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_romania.animate().rotation(btn_romania.getRotation()-45).start();

                AlertDialog.Builder romania = new AlertDialog.Builder(FinalResultActivity.this);

                romania.setTitle("\uD83C\uDF0D Pentru românii din România");
                romania.setMessage("\t\tUtilitatea aplicației este pentru calcularea salariilor bazate pe legislația din România.\n" +
                        "\n\t\tPrecizia aplicației este de 100%.\n" +
                        "\n\t\tCalculatorul nu are aplicabilitate pentru persoanele care-și desfășoară activitatea în domeniul construcțiilor. Dacă e nevoie, pe viitor, la o nouă actualizare a aplicației va fi și opțiunea pentru calculul salariilor din domeniul construcțiilor.\n" +
                        "\n\t\tAplicația respectă legislația GDPR, datele nu sunt stocate, orice salariu veți introduce va fi calculat numai și numai pentru dumneavoastră ca utilitate proprie.");
                romania.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        hideNavigationBar();

                        btn_romania.animate().rotation(btn_romania.getRotation()+45).start();
                    }
                }).setCancelable(false).show();

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });

        btn_diamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_diamond.animate().rotation(btn_diamond.getRotation()+45).start();

                AlertDialog.Builder diamond = new AlertDialog.Builder(FinalResultActivity.this);

                diamond.setTitle("\uD83C\uDF81 Informații prețioase");
                diamond.setMessage("\t\tPentru calcularea salariului cu funcția de bază activă, s-au folosit limitele valorice din Ordonanța de Urgență nr. 79/2017 din 8 Noiembrie, 2017.\n" +
                        "\n\t\tFormula pentru calculul impozitului:\n" +
                        "\n\t\timpozit = venitBaza * procent / 100\n" +
                        "\t\t# unde procentul e de 10%, iar formula pentru venitul de bază este următoarea:\n" +
                        "\t\t# venitBaza = salariulBrut - CAS - CASS - deducere\n" +
                        "\t\t# datele pentru deducere sunt cele din OUG 79/2017 din 8 Noiembrie, 2017\n" +
                        "\n\t\tPentru salariile mai mari decât 3600 de lei, nu mai contează deducerea pentru funcția de bază și nici câte persoane sunt în întreținerea salariatului.");
                diamond.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        hideNavigationBar();

                        btn_diamond.animate().rotation(btn_diamond.getRotation()-45).start();
                    }
                }).setCancelable(false).show();

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });

        // Async instantiation
        AsyncBNRinEuro asyncBNRinEuro = new AsyncBNRinEuro();
        AsyncBNRinDolari asyncBNRinDolari = new AsyncBNRinDolari();
        AsyncBNRinFranci asyncBNRinFranci = new AsyncBNRinFranci();
        AsyncBNRinRuble asyncBNRinRuble = new AsyncBNRinRuble();
        AsyncBNRinLire asyncBNRinLire = new AsyncBNRinLire();
        AsyncBNRinRupii asyncBNRinRupii = new AsyncBNRinRupii();

        asyncBNRinEuro.execute();
        asyncBNRinDolari.execute();
        asyncBNRinFranci.execute();
        asyncBNRinRuble.execute();
        asyncBNRinLire.execute();
        asyncBNRinRupii.execute();

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

        // Special guest | Animation for btn_franc
        scaleFrancDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleFrancDown.setTarget(btn_franc);

        scaleFrancUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setFrancDownAndUp = new AnimatorSet();
        setFrancDownAndUp.playSequentially(scaleFrancDown, scaleFrancUp);

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

        animationDrawableFranc = (AnimationDrawable) btn_franc.getBackground();
        animationDrawableFranc.setEnterFadeDuration(250);
        animationDrawableFranc.setExitFadeDuration(500);

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
                btn_franc.setVisibility(View.VISIBLE);

                setEuroDownAndUp.start();
                setFrancDownAndUp.start();

                animationDrawableEuro.start();
                animationDrawableFranc.start();
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
        txt_info_change.setText(R.string.str_click_on_dollar);

        txt_salariu.setText(R.string.str_salariu_dolari);

        float_etalonInDolari = Float.parseFloat(etalonInDolari);

        CalculDolari();
    }

    public void ClickOnEuro(View v){
        txt_info_change.setText(R.string.str_click_on_euro);

        txt_salariu.setText(R.string.str_salariu_euro);

        float_etalonInEuro = Float.parseFloat(etalonInEuro);

        CalculEuro();
    }

    public void ClickOnPound(View v){
        txt_info_change.setText(R.string.str_click_on_pound);

        txt_salariu.setText(R.string.str_salariu_lire);

        float_etalonInLire = Float.parseFloat(etalonInLire);

        CalculLire();
    }

    public void ClickOnLei(View v){
        txt_info_change.setText(R.string.str_click_on_lei);

        txt_salariu.setText(R.string.str_salariu_lei);

        CalculLei();
    }

    public void ClickOnRupee(View v){
        txt_info_change.setText(R.string.str_click_on_rupee);

        txt_salariu.setText(R.string.str_salariu_rupii);

        float_etalonInRupii = Float.parseFloat(etalonInRupii);

        CalculRupii();
    }

    public void ClickOnFranc(View v){
        txt_info_change.setText(R.string.str_click_on_franc);

        txt_salariu.setText(R.string.str_salariu_franc);

        float_etalonInFranci = Float.parseFloat(etalonInFranci);

        CalculFranci();
    }

    public void ClickOnRuble(View v) throws IOException {
        txt_info_change.setText(R.string.str_click_on_ruble);

        txt_salariu.setText(R.string.str_salariu_ruble);

        float_etalonInRuble = Float.parseFloat(etalonInRuble);

        CalculRuble();
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

    @SuppressLint("DefaultLocale")
    public void CalculEuro(){
        // Calculus
        salar_brut_in_euro = salar_brut / float_etalonInEuro;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_euro));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_euro = cas_lei / float_etalonInEuro;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_euro));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_euro = cass_lei / float_etalonInEuro;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_euro));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_euro = salar_net / float_etalonInEuro;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_euro));

        impozit_in_euro = impozit_lei / float_etalonInEuro;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_euro));
    }

    @SuppressLint("DefaultLocale")
    public void CalculDolari(){
        // Calculus
        salar_brut_in_dolari = salar_brut / float_etalonInDolari;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_dolari));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_dolari = cas_lei / float_etalonInDolari;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_dolari));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_dolari = cass_lei / float_etalonInDolari;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_dolari));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_dolari = salar_net / float_etalonInDolari;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_dolari));

        impozit_in_dolari = impozit_lei / float_etalonInDolari;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_dolari));
    }

    @SuppressLint("DefaultLocale")
    public void CalculFranci(){
        // Calculus
        salar_brut_in_franci = salar_brut / float_etalonInFranci;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_franci));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_franci = cas_lei / float_etalonInFranci;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_franci));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_franci = cass_lei / float_etalonInFranci;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_franci));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_franci = salar_net / float_etalonInFranci;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_franci));

        impozit_in_franci = impozit_lei / float_etalonInFranci;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_franci));
    }

    @SuppressLint("DefaultLocale")
    public void CalculRuble(){
        // Calculus
        salar_brut_in_ruble = salar_brut / float_etalonInRuble;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_ruble));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_ruble = cas_lei / float_etalonInRuble;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_ruble));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_ruble = cass_lei / float_etalonInRuble;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_ruble));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_ruble = salar_net / float_etalonInRuble;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_ruble));

        impozit_in_ruble = impozit_lei / float_etalonInRuble;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_ruble));
    }

    @SuppressLint("DefaultLocale")
    public void CalculLire(){
        // Calculus
        salar_brut_in_lire = salar_brut / float_etalonInLire;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_lire));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_lire = cas_lei / float_etalonInLire;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_lire));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_lire = cass_lei / float_etalonInLire;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_lire));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_lire = salar_net / float_etalonInLire;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_lire));

        impozit_in_lire = impozit_lei / float_etalonInLire;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_lire));
    }

    @SuppressLint("DefaultLocale")
    public void CalculRupii(){
        // Calculus
        salar_brut_in_rupii = salar_brut / float_etalonInRupii;
        txt_salariu_brut_rezultat.setText(String.format("%.2f", salar_brut_in_rupii));

        cas_lei = salar_brut * (25.0f / 100);
        cas_in_rupii = cas_lei / float_etalonInRupii;
        txt_asigurari_cas_rezultat.setText(String.format("%.2f", cas_in_rupii));

        cass_lei = salar_brut * (10.0f / 100);
        cass_in_rupii = cass_lei / float_etalonInRupii;
        txt_asigurari_cass_rezultat.setText(String.format("%.2f", cass_in_rupii));

        // Conform OUG Nr. 79.2017 din 8 Noiembrie 2017
        FunctieBaza_DA_ScutitImpozit_DA();
        FunctieBaza_DA_ScutitImpozit_NU();
        FunctieBaza_NU_ScutitImpozit_DA();
        FunctieBaza_NU_ScutitImpozit_NU();

        salar_net_in_rupii = salar_net / float_etalonInRupii;
        txt_salariu_net_rezultat.setText(String.format("%.2f", salar_net_in_rupii));

        impozit_in_rupii = impozit_lei / float_etalonInRupii;
        txt_impozit_pe_venit_rezultat.setText(String.format("%.2f", impozit_in_rupii));
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

    public void OnTxtSalariuBrutClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_salariu_brut,"Din salariul brut se scad taxele");

        txt_salariu_brut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_salariu_brut.performLongClick();
            }
        });

    }

    public void OnTxtSalariuBrutRezultatClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_salariu_brut_rezultat,"Din salariul brut se scad taxele");

        txt_salariu_brut_rezultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_salariu_brut_rezultat.performLongClick();
            }
        });

    }

    public void OnTxtAsigurariCASClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_asigurari_cas,"Asigurări Sociale");

        txt_asigurari_cas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_asigurari_cas.performLongClick();
            }
        });

    }

    public void OnTxtAsigurariCASRezultatClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_asigurari_cas_rezultat,"25% din salariul brut");

        txt_asigurari_cas_rezultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_asigurari_cas_rezultat.performLongClick();
            }
        });

    }

    public void OnTxtAsigurariCASSClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_asigurari_cass,"Asigurări Sociale de Sănătate");

        txt_asigurari_cass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_asigurari_cass.performLongClick();
            }
        });

    }

    public void OnTxtAsigurariCASSRezultatClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_asigurari_cass_rezultat,"10% din salariul brut");

        txt_asigurari_cass_rezultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_asigurari_cass_rezultat.performLongClick();
            }
        });

    }

    public void OnTxtSalariuNetClick(View view) {

        // Set Tooltip
        TooltipCompat.setTooltipText(txt_salariu_net,"Salariatul rămâne în mână cu salariul net");

        txt_salariu_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_salariu_net.performLongClick();
            }
        });

    }

    public void OnTxtSalariuNetRezultatClick(View view) {

        //Set Tooltip
        TooltipCompat.setTooltipText(txt_salariu_net_rezultat,"Salariatul rămâne în mână cu salariul net");

        txt_salariu_net_rezultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_salariu_net_rezultat.performLongClick();
            }
        });

    }

    // Need special attention!
    private class AsyncBNRinEuro extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(2);

                etalonInEuro = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }

    private class AsyncBNRinDolari extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(10);

                etalonInDolari = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }

    private class AsyncBNRinFranci extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(18);

                etalonInFranci = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }

    private class AsyncBNRinRuble extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(42);

                etalonInRuble = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }

    private class AsyncBNRinLire extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(26);

                etalonInLire = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }

    private class AsyncBNRinRupii extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                document = Jsoup.connect(url_bnr).get();
                Elements table = document.select("table#table-currencies"); // select by id

                Element row = table.select("td").get(74);

                etalonInRupii = row.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void voids) {
            super.onPostExecute(voids);

        }
    }


}