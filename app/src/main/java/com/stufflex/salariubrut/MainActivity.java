package com.stufflex.salariubrut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Placeholder;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Declarations
    private TextView txt_title;
    private EditText editTextNumberDecimal;
    private Button btn_euro;
    private Button btn_dollar;
    private Button btn_play;
    private Button btn_lei;
    private Button btn_pound;
    private Button btn_sheqel;
    private Button btn_rupee;
    private Button btn_ruble;
    private Button btn_present;
    private Button btn_money;
    private TextView txt_arrow;
    private TextView txt_copyright;

    private ConstraintLayout mainLayout;
    private ConstraintLayout bar_1;
    private ConstraintLayout bar_2;
    private ConstraintLayout bar_3;

    private int n = 12;

    private Animation anim_txt_title;
    private Animation anim_editTextNumberDecimal;
    private Animation anim_btn_present;
    private Animation anim_btn_money;
    private Animation anim_btn_play;
    private Animation anim_txt_copyright;
    private Animation anim_txt_arrow;

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

    private static final long TOAST_TIMEOUT_MS = 2000;
    private static long lastToastTime = 0;

    private ConstraintSet constraintSetActivityOLD = new ConstraintSet();
    private ConstraintSet constraintSetActivityNEW = new ConstraintSet();

    private boolean playIsClicked;

    private TextInputLayout txt_input_layout;

    private TextInputEditText txt_input_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navbar-fullscreen
        hideNavigationBar();

        // Initializations
        txt_title = findViewById(R.id.txt_title);
        txt_input_layout = findViewById(R.id.txt_input_layout);
        txt_input_edit_text = findViewById(R.id.txt_input_edit_text);
        btn_euro = findViewById(R.id.btn_euro);
        btn_dollar = findViewById(R.id.btn_dollar);
        btn_play = findViewById(R.id.btn_play);
        btn_lei = findViewById(R.id.btn_lei);
        btn_pound = findViewById(R.id.btn_pound);
        btn_sheqel = findViewById(R.id.btn_sheqel);
        btn_rupee = findViewById(R.id.btn_rupee);
        btn_ruble = findViewById(R.id.btn_ruble);
        btn_present = findViewById(R.id.btn_present);
        btn_money = findViewById(R.id.btn_money);
        txt_arrow = findViewById(R.id.txt_arrow);
        txt_copyright = findViewById(R.id.txt_copyright);
        mainLayout = findViewById(R.id.mainLayout);
        bar_1 = findViewById(R.id.bar_1);
        bar_2 = findViewById(R.id.bar_2);
        bar_3 = findViewById(R.id.bar_3);

        // Alert user when EditText limit exxceeded
        txt_input_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Navbar-fullscreen
                hideNavigationBar();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == n)
                {
                    new AlertDialog.Builder(MainActivity.this).setTitle("\uD83D\uDC40 Ai ajuns la limită! ⌛").setMessage("Te rog, nu minți! Mulțumesc! ☺").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();
                    txt_input_edit_text.setText("");

                    // Navbar-fullscreen
                    hideNavigationBar();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Navbar-fullscreen
                hideNavigationBar();
            }

        });

        // Set animations
        anim_btn_play = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_txt_copyright = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up);
        anim_txt_arrow = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        anim_btn_money = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_btn_present = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        txt_copyright.setAnimation(anim_txt_copyright);
        btn_play.setAnimation(anim_btn_play);
        txt_arrow.setAnimation(anim_txt_arrow);
        btn_money.setAnimation(anim_btn_money);
        btn_present.setAnimation(anim_btn_present);

        // Hide these buttons
        btn_euro.setVisibility(View.INVISIBLE);
        btn_lei.setVisibility(View.INVISIBLE);
        btn_sheqel.setVisibility(View.INVISIBLE);
        btn_dollar.setVisibility(View.INVISIBLE);
        btn_pound.setVisibility(View.INVISIBLE);
        btn_rupee.setVisibility(View.INVISIBLE);
        btn_ruble.setVisibility(View.INVISIBLE);

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

        // Cloning
        constraintSetActivityOLD.clone(mainLayout);
        constraintSetActivityNEW.clone(this, R.layout.clone_activity_main);

        // Hide bars from txt_title
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar_1.setVisibility(View.INVISIBLE);
            }
        }, 800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar_2.setVisibility(View.INVISIBLE);

            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar_3.setVisibility(View.INVISIBLE);

            }
        }, 1200);

        // Set currency buttons visibility
        btn_euro.setVisibility(View.INVISIBLE);
        btn_lei.setVisibility(View.INVISIBLE);
        btn_sheqel.setVisibility(View.INVISIBLE);
        btn_dollar.setVisibility(View.INVISIBLE);
        btn_pound.setVisibility(View.INVISIBLE);
        btn_rupee.setVisibility(View.INVISIBLE);
        btn_ruble.setVisibility(View.INVISIBLE);

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
        Intent appReset = getIntent();
        finish();
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

    public void ClickToShow(View v){
        ButtonAnimations(v);
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
    }

    // Change the layout to activity_main_open_github.xml
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ChangeActivity(View v){
        //navbar-fullscreen
        hideNavigationBar();

        TransitionManager.beginDelayedTransition(mainLayout);
        if (txt_input_edit_text.getVisibility() == View.VISIBLE && txt_input_edit_text.length() == 0) {
            new AlertDialog.Builder(MainActivity.this).setTitle("⚠ Puțină atenție, te rog!").setMessage("Introdu salariul în căsuța din mijloc. Mulțumesc! \uD83D\uDD75️\u200D♂️").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();

            // Navbar-fullscreen
            hideNavigationBar();
        }
        else {

            if (!playIsClicked) {
                constraintSetActivityNEW.applyTo(mainLayout);
                playIsClicked = true;

                txt_input_edit_text.setEnabled(false);

                ClickToShow(v);

            } else {
                constraintSetActivityOLD.applyTo(mainLayout);
                playIsClicked = false;
                txt_input_edit_text.setEnabled(true);

                // Same problem like above, I fix it with this patch
                bar_1.setVisibility(View.INVISIBLE);
                bar_2.setVisibility(View.INVISIBLE);
                bar_3.setVisibility(View.INVISIBLE);

                btn_euro.setVisibility(View.INVISIBLE);
                btn_lei.setVisibility(View.INVISIBLE);
                btn_sheqel.setVisibility(View.INVISIBLE);
                btn_dollar.setVisibility(View.INVISIBLE);
                btn_pound.setVisibility(View.INVISIBLE);
                btn_rupee.setVisibility(View.INVISIBLE);
                btn_ruble.setVisibility(View.INVISIBLE);
            }
        }

    }

    public void ButtonAnimations(View v) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_lei.setVisibility(View.VISIBLE);

                setLeiDownAndUp.start();

                animationDrawableLei.start();
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_pound.setVisibility(View.VISIBLE);
                btn_rupee.setVisibility(View.VISIBLE);

                setPoundDownAndUp.start();
                setRupeeDownAndUp.start();

                animationDrawablePound.start();
                animationDrawableRupee.start();
            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_euro.setVisibility(View.VISIBLE);
                btn_sheqel.setVisibility(View.VISIBLE);

                setEuroDownAndUp.start();
                setSheqelDownAndUp.start();

                animationDrawableEuro.start();
                animationDrawableSheqel.start();
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_dollar.setVisibility(View.VISIBLE);
                btn_ruble.setVisibility(View.VISIBLE);

                setDollarDownAndUp.start();
                setRubleDownAndUp.start();

                animationDrawableDollar.start();
                animationDrawableRuble.start();
            }
        }, 1200);
     }
}