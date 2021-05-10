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
    private Button btn_romania;
    private Button btn_gift;
    private Button btn_diamond;
    private Button btn_freestyle_1;
    private Button btn_freestyle_2;
    private Button btn_freestyle_3;
    private Button btn_freestyle_4;
    private Button btn_freestyle_5;
    private Button btn_freestyle_6;
    private Button btn_freestyle_7;
    private Button btn_freestyle_8;
    private Button btn_freestyle_9;
    private Button btn_freestyle_10;
    private Button btn_freestyle_11;
    private Button btn_freestyle_12;
    private Button btn_money_1;
    private Button btn_money_2;
    private Button btn_money_3;
    private Button btn_money_4;
    private Button btn_money_5;

    private TextView txt_arrow;
    private TextView txt_copyright;

    private ConstraintLayout mainLayout;
    private ConstraintLayout bar_1;
    private ConstraintLayout bar_2;
    private ConstraintLayout bar_3;

    private int n = 12;

    private Animation anim_txt_title;
    private Animation anim_btn_romania;
    private Animation anim_btn_diamond;
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
    private AnimatorSet setGiftDownAndUp;

    private Animator scaleLeiDown;
    private Animator scalePoundDown;
    private Animator scaleRupeeDown;
    private Animator scaleEuroDown;
    private Animator scaleSheqelDown;
    private Animator scaleDollarDown;
    private Animator scaleRubleDown;
    private Animator scaleGiftDown;

    private Animator scaleLeiUp;
    private Animator scalePoundUp;
    private Animator scaleRupeeUp;
    private Animator scaleEuroUp;
    private Animator scaleSheqelUp;
    private Animator scaleDollarUp;
    private Animator scaleRubleUp;
    private Animator scaleGiftUp;

    private AnimationDrawable animationDrawableLei;
    private AnimationDrawable animationDrawablePound;
    private AnimationDrawable animationDrawableRupee;
    private AnimationDrawable animationDrawableEuro;
    private AnimationDrawable animationDrawableSheqel;
    private AnimationDrawable animationDrawableDollar;
    private AnimationDrawable animationDrawableRuble;
    private AnimationDrawable animationDrawableGif;

    private static final long TOAST_TIMEOUT_MS = 2000;
    private static long lastToastTime = 0;

    private ConstraintSet constraintSetActivityOLD = new ConstraintSet();
    private ConstraintSet constraintSetActivityNEW = new ConstraintSet();

    private boolean playIsClicked;

    private TextInputLayout txt_input_layout;

    private TextInputEditText txt_input_edit_text;

    private Handler handler1;
    private Handler handler2;
    private Handler handler3;
    private Handler handler4;
    private Handler handler5;
    private Handler handler6;
    private Handler handler7;
    private Handler handler8;
    private Handler handler9;
    private Handler handler10;
    private Handler handler11;
    private Handler handler12;
    private Handler handler13;
    private Handler handler14;
    private Handler handler15;
    private Handler handler16;
    private Handler handler17;
    private Handler handler18;
    private Handler handler19;
    private Handler handler20;
    private Handler handler21;
    private Handler handler22;
    private Handler handler23;
    private Handler handler24;
    private Handler handler25;
    private Handler handler26;
    private Handler handler27;
    private Handler handler28;
    private Handler handler29;
    private Handler handler30;
    private Handler handler31;
    private Handler handler32;
    private Handler handler33;
    private Handler handler34;
    private Handler handler35;
    private Handler handler36;
    private Handler handler37;
    private Handler handler38;

    private Handler handler_btn_currency_1;
    private Handler handler_btn_currency_2;
    private Handler handler_btn_currency_3;
    private Handler handler_btn_currency_4;

    private Runnable runnable1;
    private Runnable runnable2;
    private Runnable runnable3;
    private Runnable runnable4;
    private Runnable runnable5;
    private Runnable runnable6;
    private Runnable runnable7;
    private Runnable runnable8;
    private Runnable runnable9;
    private Runnable runnable10;
    private Runnable runnable11;
    private Runnable runnable12;
    private Runnable runnable13;
    private Runnable runnable14;
    private Runnable runnable15;
    private Runnable runnable16;
    private Runnable runnable17;
    private Runnable runnable18;
    private Runnable runnable19;
    private Runnable runnable20;
    private Runnable runnable21;
    private Runnable runnable22;
    private Runnable runnable23;
    private Runnable runnable24;
    private Runnable runnable25;
    private Runnable runnable26;
    private Runnable runnable27;
    private Runnable runnable28;
    private Runnable runnable29;
    private Runnable runnable30;
    private Runnable runnable31;
    private Runnable runnable32;
    private Runnable runnable33;
    private Runnable runnable34;
    private Runnable runnable35;
    private Runnable runnable36;
    private Runnable runnable37;
    private Runnable runnable38;

    private Runnable runnable_btn_currency_1;
    private Runnable runnable_btn_currency_2;
    private Runnable runnable_btn_currency_3;
    private Runnable runnable_btn_currency_4;

    private boolean giftIsClicked = false;

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
        btn_romania = findViewById(R.id.btn_romania);
        btn_diamond = findViewById(R.id.btn_diamond);
        btn_gift = findViewById(R.id.btn_gift);
        btn_freestyle_1 = findViewById(R.id.btn_freestyle_1);
        btn_freestyle_2 = findViewById(R.id.btn_freestyle_2);
        btn_freestyle_3 = findViewById(R.id.btn_freestyle_3);
        btn_freestyle_4 = findViewById(R.id.btn_freestyle_4);
        btn_freestyle_5 = findViewById(R.id.btn_freestyle_5);
        btn_freestyle_6 = findViewById(R.id.btn_freestyle_6);
        btn_freestyle_7 = findViewById(R.id.btn_freestyle_7);
        btn_freestyle_8 = findViewById(R.id.btn_freestyle_8);
        btn_freestyle_9 = findViewById(R.id.btn_freestyle_9);
        btn_freestyle_10 = findViewById(R.id.btn_freestyle_10);
        btn_freestyle_11 = findViewById(R.id.btn_freestyle_11);
        btn_freestyle_12 = findViewById(R.id.btn_freestyle_12);
        btn_money_1 = findViewById(R.id.btn_money_1);
        btn_money_2 = findViewById(R.id.btn_money_2);
        btn_money_3 = findViewById(R.id.btn_money_3);
        btn_money_4 = findViewById(R.id.btn_money_4);
        btn_money_5 = findViewById(R.id.btn_money_5);

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
        anim_btn_diamond = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_btn_romania = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        txt_copyright.setAnimation(anim_txt_copyright);
        btn_play.setAnimation(anim_btn_play);
        txt_arrow.setAnimation(anim_txt_arrow);
        btn_diamond.setAnimation(anim_btn_diamond);
        btn_romania.setAnimation(anim_btn_romania);

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

        // Special guest | Animation for btn_ruble
        scaleGiftDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleGiftDown.setTarget(btn_gift);

        scaleGiftUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setGiftDownAndUp = new AnimatorSet();
        setGiftDownAndUp.playSequentially(scaleGiftDown, scaleGiftUp);
        setGiftDownAndUp.start();

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
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bar_3.setVisibility(View.INVISIBLE);

            }
        }, 1600);

        // Set currency buttons visibility
        btn_euro.setVisibility(View.INVISIBLE);
        btn_lei.setVisibility(View.INVISIBLE);
        btn_sheqel.setVisibility(View.INVISIBLE);
        btn_dollar.setVisibility(View.INVISIBLE);
        btn_pound.setVisibility(View.INVISIBLE);
        btn_rupee.setVisibility(View.INVISIBLE);
        btn_ruble.setVisibility(View.INVISIBLE);

        // Set freestyle buttons to invisible
        btn_freestyle_1.setVisibility(View.INVISIBLE);
        btn_freestyle_2.setVisibility(View.INVISIBLE);
        btn_freestyle_3.setVisibility(View.INVISIBLE);
        btn_freestyle_4.setVisibility(View.INVISIBLE);
        btn_freestyle_5.setVisibility(View.INVISIBLE);
        btn_freestyle_6.setVisibility(View.INVISIBLE);
        btn_freestyle_7.setVisibility(View.INVISIBLE);
        btn_freestyle_8.setVisibility(View.INVISIBLE);
        btn_freestyle_9.setVisibility(View.INVISIBLE);
        btn_freestyle_10.setVisibility(View.INVISIBLE);
        btn_freestyle_11.setVisibility(View.INVISIBLE);
        btn_freestyle_12.setVisibility(View.INVISIBLE);
        btn_money_1.setVisibility(View.INVISIBLE);
        btn_money_2.setVisibility(View.INVISIBLE);
        btn_money_3.setVisibility(View.INVISIBLE);
        btn_money_4.setVisibility(View.INVISIBLE);
        btn_money_5.setVisibility(View.INVISIBLE);

        // Freestyle animations on click to btn_gift
        btn_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CaleaSpreSucces(view);
                giftIsClicked = true;

                btn_gift.setVisibility(View.INVISIBLE);

                handler1 = new  Handler();
                runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setVisibility(View.VISIBLE);

                    }
                };
                handler1.postDelayed(runnable1, 100);

                handler2 = new  Handler();
                runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_frowning);

                    }
                };
                handler2.postDelayed(runnable2, 400);

                handler3 = new  Handler();
                runnable3 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_worried);

                    }
                };
                handler3.postDelayed(runnable3, 800);

                handler4 = new  Handler();
                runnable4 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_sad);

                    }
                };
                handler4.postDelayed(runnable4, 1200);

                handler5 = new  Handler();
                runnable5 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_crying);

                    }
                };
                handler5.postDelayed(runnable5, 1600);

                handler6 = new  Handler();
                runnable6 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_2.setVisibility(View.VISIBLE);

                    }
                };
                handler6.postDelayed(runnable6, 2000);

                handler7 = new  Handler();
                runnable7 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_little_smile);

                    }
                };
                handler7.postDelayed(runnable7, 2400);

                handler8 = new  Handler();
                runnable8 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_wink);

                    }
                };
                handler8.postDelayed(runnable8, 2800);

                handler9 = new  Handler();
                runnable9 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_wink);

                    }
                };
                handler9.postDelayed(runnable9, 3200);

                handler10 = new  Handler();
                runnable10 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setText(R.string.str_sunglasses);

                    }
                };
                handler10.postDelayed(runnable10, 3600);

                handler11 = new  Handler();
                runnable11 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setVisibility(View.VISIBLE);

                    }
                };
                handler11.postDelayed(runnable11, 4000);

                handler12 = new  Handler();
                runnable12 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_4.setVisibility(View.VISIBLE);

                    }
                };
                handler12.postDelayed(runnable12, 4400);

                handler13 = new  Handler();
                runnable13 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(45);
                        btn_freestyle_5.setVisibility(View.VISIBLE);

                    }
                };
                handler13.postDelayed(runnable13, 4800);

                handler14 = new  Handler();
                runnable14 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(90);
                        btn_freestyle_6.setVisibility(View.VISIBLE);

                    }
                };
                handler14.postDelayed(runnable14, 5200);

                handler15 = new  Handler();
                runnable15 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(0);
                        btn_freestyle_4.setText(R.string.str_judge);

                    }
                };
                handler15.postDelayed(runnable15, 5600);

                handler16 = new  Handler();
                runnable16 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(45);
                        btn_freestyle_5.setText(R.string.str_farmer);

                    }
                };
                handler16.postDelayed(runnable16, 6000);

                handler17 = new  Handler();
                runnable17 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(90);
                        btn_freestyle_6.setText(R.string.str_cook);

                    }
                };
                handler17.postDelayed(runnable17, 6400);

                handler18 = new  Handler();
                runnable18 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(0);
                        btn_freestyle_4.setText(R.string.str_mechanic);

                    }
                };
                handler18.postDelayed(runnable18, 6800);

                handler19 = new  Handler();
                runnable19 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(45);
                        btn_freestyle_5.setText(R.string.str_factory_worker);

                    }
                };
                handler19.postDelayed(runnable19, 7200);

                handler20 = new  Handler();
                runnable20 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(90);
                        btn_freestyle_6.setText(R.string.str_office_worker);

                    }
                };
                handler20.postDelayed(runnable20, 7600);

                handler21 = new  Handler();
                runnable21 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(0);
                        btn_freestyle_4.setText(R.string.str_scientist);

                    }
                };
                handler21.postDelayed(runnable21, 8000);

                handler22 = new  Handler();
                runnable22 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(45);
                        btn_freestyle_5.setText(R.string.str_technologist);

                    }
                };
                handler22.postDelayed(runnable22, 8400);

                handler23 = new  Handler();
                runnable23 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setRotation(90);
                        btn_freestyle_6.setText(R.string.str_astronaut);

                    }
                };
                handler23.postDelayed(runnable23, 8800);

                handler24 = new  Handler();
                runnable24 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_3.setVisibility(View.INVISIBLE);
                        btn_freestyle_7.setVisibility(View.VISIBLE);

                    }
                };
                handler24.postDelayed(runnable24, 9200);

                handler25 = new  Handler();
                runnable25 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_8.setVisibility(View.VISIBLE);

                    }
                };
                handler25.postDelayed(runnable25, 9600);

                handler26 = new  Handler();
                runnable26 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_9.setVisibility(View.VISIBLE);

                    }
                };
                handler26.postDelayed(runnable26, 10000);

                handler27 = new  Handler();
                runnable27 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_10.setVisibility(View.VISIBLE);

                    }
                };
                handler27.postDelayed(runnable27, 10400);

                handler28 = new  Handler();
                runnable28 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_11.setVisibility(View.VISIBLE);

                    }
                };
                handler28.postDelayed(runnable28, 10800);

                handler29 = new  Handler();
                runnable29 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_12.setVisibility(View.VISIBLE);

                    }
                };
                handler29.postDelayed(runnable29, 11200);

                handler30 = new  Handler();
                runnable30 = new Runnable() {
                    @Override
                    public void run() {
                        btn_freestyle_1.setVisibility(View.INVISIBLE);
                        btn_freestyle_1.setText(R.string.str_confused);
                        btn_freestyle_2.setVisibility(View.INVISIBLE);
                        btn_freestyle_3.setVisibility(View.INVISIBLE);
                        btn_freestyle_4.setVisibility(View.INVISIBLE);
                        btn_freestyle_5.setVisibility(View.INVISIBLE);
                        btn_freestyle_6.setVisibility(View.INVISIBLE);
                        btn_freestyle_7.setVisibility(View.INVISIBLE);
                        btn_freestyle_8.setVisibility(View.INVISIBLE);
                        btn_freestyle_9.setVisibility(View.INVISIBLE);
                        btn_freestyle_10.setVisibility(View.INVISIBLE);
                        btn_freestyle_11.setVisibility(View.INVISIBLE);
                        btn_freestyle_12.setVisibility(View.INVISIBLE);
                        btn_money_1.setVisibility(View.VISIBLE);
                    }
                };
                handler30.postDelayed(runnable30, 11600);

                handler31 = new  Handler();
                runnable31 = new Runnable() {
                    @Override
                    public void run() {
                        btn_money_2.setVisibility(View.VISIBLE);

                    }
                };
                handler31.postDelayed(runnable31, 12000);

                handler32 = new  Handler();
                runnable32 = new Runnable() {
                    @Override
                    public void run() {
                        btn_money_5.setVisibility(View.VISIBLE);

                    }
                };
                handler32.postDelayed(runnable32, 12400);

                handler33 = new  Handler();
                runnable33 = new Runnable() {
                    @Override
                    public void run() {
                        btn_money_4.setVisibility(View.VISIBLE);

                    }
                };
                handler33.postDelayed(runnable33, 12800);

                handler34 = new  Handler();
                runnable34 = new Runnable() {
                    @Override
                    public void run() {
                        btn_money_3.setVisibility(View.VISIBLE);

                    }
                };
                handler34.postDelayed(runnable34, 13200);

                handler35 = new  Handler();
                runnable35 = new Runnable() {
                    @Override
                    public void run() {
                        btn_money_1.setVisibility(View.INVISIBLE);
                        btn_money_2.setVisibility(View.INVISIBLE);
                        btn_money_3.setVisibility(View.INVISIBLE);
                        btn_money_4.setVisibility(View.INVISIBLE);
                        btn_money_5.setVisibility(View.INVISIBLE);

                        btn_gift.setVisibility(View.VISIBLE);
                        btn_gift.setText(R.string.str_grinning);

                        setGiftDownAndUp.start();
                    }
                };
                handler35.postDelayed(runnable35, 14000);

                handler36 = new  Handler();
                runnable36 = new Runnable() {
                    @Override
                    public void run() {
                        btn_gift.setText(R.string.str_grinning);

                    }
                };
                handler36.postDelayed(runnable36, 14500);

                handler37 = new  Handler();
                runnable37 = new Runnable() {
                    @Override
                    public void run() {
                        btn_gift.setText(R.string.str_grinning_eyes);

                    }
                };
                handler37.postDelayed(runnable37, 15000);

                handler38 = new  Handler();
                runnable38 = new Runnable() {
                    @Override
                    public void run() {
                        btn_gift.setText(R.string.str_gift);
                        btn_gift.animate().rotation(btn_gift.getRotation()-360).start();

                    }
                };
                handler38.postDelayed(runnable38, 15500);
            }
        });

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

                // Remove handlers
                if(giftIsClicked){
                    handler1.removeCallbacksAndMessages(null);
                    handler2.removeCallbacksAndMessages(null);
                    handler3.removeCallbacksAndMessages(null);
                    handler4.removeCallbacksAndMessages(null);
                    handler5.removeCallbacksAndMessages(null);
                    handler6.removeCallbacksAndMessages(null);
                    handler7.removeCallbacksAndMessages(null);
                    handler8.removeCallbacksAndMessages(null);
                    handler9.removeCallbacksAndMessages(null);
                    handler10.removeCallbacksAndMessages(null);
                    handler11.removeCallbacksAndMessages(null);
                    handler12.removeCallbacksAndMessages(null);
                    handler13.removeCallbacksAndMessages(null);
                    handler14.removeCallbacksAndMessages(null);
                    handler15.removeCallbacksAndMessages(null);
                    handler16.removeCallbacksAndMessages(null);
                    handler17.removeCallbacksAndMessages(null);
                    handler18.removeCallbacksAndMessages(null);
                    handler19.removeCallbacksAndMessages(null);
                    handler20.removeCallbacksAndMessages(null);
                    handler21.removeCallbacksAndMessages(null);
                    handler22.removeCallbacksAndMessages(null);
                    handler23.removeCallbacksAndMessages(null);
                    handler24.removeCallbacksAndMessages(null);
                    handler25.removeCallbacksAndMessages(null);
                    handler26.removeCallbacksAndMessages(null);
                    handler27.removeCallbacksAndMessages(null);
                    handler28.removeCallbacksAndMessages(null);
                    handler29.removeCallbacksAndMessages(null);
                    handler30.removeCallbacksAndMessages(null);
                    handler31.removeCallbacksAndMessages(null);
                    handler32.removeCallbacksAndMessages(null);
                    handler33.removeCallbacksAndMessages(null);
                    handler34.removeCallbacksAndMessages(null);
                    handler35.removeCallbacksAndMessages(null);
                    handler36.removeCallbacksAndMessages(null);
                    handler37.removeCallbacksAndMessages(null);
                    handler38.removeCallbacksAndMessages(null);
                }

                btn_freestyle_1.setVisibility(View.INVISIBLE);
                btn_freestyle_2.setVisibility(View.INVISIBLE);
                btn_freestyle_3.setVisibility(View.INVISIBLE);
                btn_freestyle_4.setVisibility(View.INVISIBLE);
                btn_freestyle_5.setVisibility(View.INVISIBLE);
                btn_freestyle_6.setVisibility(View.INVISIBLE);
                btn_freestyle_7.setVisibility(View.INVISIBLE);
                btn_freestyle_8.setVisibility(View.INVISIBLE);
                btn_freestyle_9.setVisibility(View.INVISIBLE);
                btn_freestyle_10.setVisibility(View.INVISIBLE);
                btn_freestyle_11.setVisibility(View.INVISIBLE);
                btn_freestyle_12.setVisibility(View.INVISIBLE);

                txt_input_edit_text.setEnabled(false);
                btn_gift.setVisibility(View.INVISIBLE);

                ClickToShow(v);

            } else {
                // Remove currency button animations even when they are not fully loaded...
                handler_btn_currency_1.removeCallbacksAndMessages(null);
                handler_btn_currency_2.removeCallbacksAndMessages(null);
                handler_btn_currency_3.removeCallbacksAndMessages(null);
                handler_btn_currency_4.removeCallbacksAndMessages(null);

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

                btn_gift.setVisibility(View.VISIBLE);
                setGiftDownAndUp.start();
            }
        }

    }

    public void CaleaSpreSucces(View v) {
         Toast toast = Toast.makeText(this, "Calea spre succes...", Toast.LENGTH_LONG);
         toast.setGravity(Gravity.BOTTOM, 0, 0);

         toast.show();

     }
}