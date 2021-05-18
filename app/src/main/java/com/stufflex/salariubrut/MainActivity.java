package com.stufflex.salariubrut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // Declarations
    private Button btn_play_complete;
    private Button btn_play_final;
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
    private Button btn_plus;
    private Button btn_minus;

    private TextView txt_title;
    private TextView txt_play_complete;
    private TextView txt_play_final;
    private TextView txt_copyright;

    private TextView txt_numar_persoane_in_intretinere;

    private ConstraintLayout mainLayout;
    private ConstraintLayout optionsToCompleteLayout;
    private ConstraintLayout bar_1;
    private ConstraintLayout bar_2;
    private ConstraintLayout bar_3;

    private int n = 12;

    private Animation anim_txt_title;
    private Animation anim_btn_romania;
    private Animation anim_btn_diamond;
    private Animation anim_btn_play_complete;
    private Animation anim_txt_copyright;
    private Animation anim_txt_play_complete;

    private AnimatorSet setGiftDownAndUp;

    private Animator scaleGiftDown;

    private Animator scaleGiftUp;

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

    private boolean giftIsClicked = false;

    private CheckBox checkBox_functie_de_baza_DA;
    private CheckBox checkBox_functie_de_baza_NU;
    private CheckBox checkBox_scutit_de_impozit_DA;
    private CheckBox checkBox_scutit_de_impozit_NU;

    private int nrPersoaneInIntretinere;

    private float salariu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navbar-fullscreen
        hideNavigationBar();

        // Initializations
        txt_title = findViewById(R.id.txt_title);
        txt_numar_persoane_in_intretinere = findViewById(R.id.txt_numar_persoane_in_intretinere);

        txt_input_layout = findViewById(R.id.txt_input_layout);
        txt_input_edit_text = findViewById(R.id.txt_input_edit_text);

        btn_play_complete = findViewById(R.id.btn_play_complete);
        btn_play_final = findViewById(R.id.btn_play_final);
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
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);

        txt_play_complete = findViewById(R.id.txt_play_complete);
        txt_play_final = findViewById(R.id.txt_play_final);
        txt_copyright = findViewById(R.id.txt_copyright);

        mainLayout = findViewById(R.id.mainLayout);
        optionsToCompleteLayout = findViewById(R.id.optionsToCompleteLayout);

        bar_1 = findViewById(R.id.bar_1);
        bar_2 = findViewById(R.id.bar_2);
        bar_3 = findViewById(R.id.bar_3);

        checkBox_functie_de_baza_DA = findViewById(R.id.checkBox_functie_de_baza_DA);
        checkBox_functie_de_baza_NU = findViewById(R.id.checkBox_functie_de_baza_NU);
        checkBox_scutit_de_impozit_DA = findViewById(R.id.checkBox_scutit_de_impozit_DA);
        checkBox_scutit_de_impozit_NU = findViewById(R.id.checkBox_scutit_de_impozit_NU);

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
                    new AlertDialog.Builder(MainActivity.this).setTitle("\uD83D\uDC40 Ați ajuns la limită! ⌛").setMessage("Vă rog, nu mințiți! Mulțumesc! ☺").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();
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
        anim_btn_play_complete = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_txt_copyright = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up);
        anim_txt_play_complete = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        anim_btn_diamond = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_btn_romania = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        txt_copyright.setAnimation(anim_txt_copyright);
        btn_play_complete.setAnimation(anim_btn_play_complete);
        txt_play_complete.setAnimation(anim_txt_play_complete);
        btn_diamond.setAnimation(anim_btn_diamond);
        btn_romania.setAnimation(anim_btn_romania);

        // Hide these
        btn_play_final.setVisibility(View.INVISIBLE);
        txt_play_final.setVisibility(View.INVISIBLE);

        optionsToCompleteLayout.setVisibility(View.INVISIBLE);

        // Special guest | Animation for btn_gift
        scaleGiftDown = AnimatorInflater.loadAnimator(this, R.animator.scale_down);
        scaleGiftDown.setTarget(btn_gift);

        scaleGiftUp = AnimatorInflater.loadAnimator(this, R.animator.scale_up);

        setGiftDownAndUp = new AnimatorSet();
        setGiftDownAndUp.playSequentially(scaleGiftDown, scaleGiftUp);
        setGiftDownAndUp.start();

        // Cloning
        constraintSetActivityOLD.clone(mainLayout);
        constraintSetActivityNEW.clone(this, R.layout.clone_activity_main_options_to_complete);

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
                        btn_freestyle_3.setRotation(0);
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

        // Checkboxes enable style
        checkBox_functie_de_baza_DA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    checkBox_functie_de_baza_NU.setEnabled(false);
                }
                else {
                    checkBox_functie_de_baza_NU.setEnabled(true);
                }

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });
        checkBox_functie_de_baza_NU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    checkBox_functie_de_baza_DA.setEnabled(false);
                }
                else {
                    checkBox_functie_de_baza_DA.setEnabled(true);
                }

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });

        checkBox_scutit_de_impozit_DA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    checkBox_scutit_de_impozit_NU.setEnabled(false);
                }
                else {
                    checkBox_scutit_de_impozit_NU.setEnabled(true);
                }

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });
        checkBox_scutit_de_impozit_NU.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    checkBox_scutit_de_impozit_DA.setEnabled(false);
                }
                else {
                    checkBox_scutit_de_impozit_DA.setEnabled(true);
                }

                // Navbar-fullscreen
                hideNavigationBar();
            }
        });

        // Playing with buttons for nrPersoaneInIntretinere; increment/decrement
        nrPersoaneInIntretinere = Integer.parseInt(txt_numar_persoane_in_intretinere.getText().toString());

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nrPersoaneInIntretinere == 30) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("\uD83D\uDC40 Ați ajuns la limită! ⌛").setMessage("Vă rog, nu mințiți! Mulțumesc! ☺").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();

                    // Navbar-fullscreen
                    hideNavigationBar();

                } else {
                    nrPersoaneInIntretinere++;

                    txt_numar_persoane_in_intretinere.setText(String.valueOf(nrPersoaneInIntretinere));
                }

                // Navbar-fullscreen
                hideNavigationBar();

            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nrPersoaneInIntretinere == 0) {
                    nrPersoaneInIntretinere = 0;
                    txt_numar_persoane_in_intretinere.setText(String.valueOf(nrPersoaneInIntretinere));
                    IntretinereLaMinim(view);
                } else {
                    nrPersoaneInIntretinere--;
                    txt_numar_persoane_in_intretinere.setText(String.valueOf(nrPersoaneInIntretinere));
                }

                // Navbar-fullscreen
                hideNavigationBar();

            }
        });

        // Some freestyle improvement because when a pop-up shows, the navbar shows also and is not ok...
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navbar-fullscreen
                hideNavigationBar();
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

    // Change the layout to clone_activity_main_options_to_complete.xml
    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ChangeActivityOptionsToComplete(View v){

        //navbar-fullscreen
        hideNavigationBar();

        TransitionManager.beginDelayedTransition(mainLayout);
        if (txt_input_edit_text.getVisibility() == View.VISIBLE && txt_input_edit_text.length() == 0) {
            new AlertDialog.Builder(MainActivity.this).setTitle("⚠ Puțină atenție, vă rog!").setMessage("Introduceți salariul în căsuța din mijloc. Mulțumesc! \uD83D\uDD75️\u200D♂️").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();

            // Navbar-fullscreen
            hideNavigationBar();
        }
        else {

            salariu = Float.parseFloat(txt_input_edit_text.getText().toString());

            if (!playIsClicked) {
                constraintSetActivityNEW.applyTo(mainLayout);
                playIsClicked = true;

                txt_play_complete.animate().rotation(txt_play_complete.getRotation()+360).start();
                txt_play_complete.setText(R.string.str_multiple);

                txt_title.setVisibility(View.INVISIBLE);
                txt_copyright.setVisibility(View.INVISIBLE);
                btn_romania.setVisibility(View.INVISIBLE);
                btn_diamond.setVisibility(View.INVISIBLE);

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

                optionsToCompleteLayout.setVisibility(View.VISIBLE);

            } else {

                constraintSetActivityOLD.applyTo(mainLayout);

                txt_title.setVisibility(View.VISIBLE);
                txt_copyright.setVisibility(View.VISIBLE);
                btn_romania.setVisibility(View.VISIBLE);
                btn_diamond.setVisibility(View.VISIBLE);

                playIsClicked = false;
                txt_input_edit_text.setEnabled(true);

                txt_play_complete.animate().rotation(txt_play_complete.getRotation()-360).start();
                txt_play_complete.setText(R.string.str_play_complete);

                btn_play_final.setVisibility(View.INVISIBLE);
                txt_play_final.setVisibility(View.INVISIBLE);

                // Same problem like above, I fix it with this patch
                bar_1.setVisibility(View.INVISIBLE);
                bar_2.setVisibility(View.INVISIBLE);
                bar_3.setVisibility(View.INVISIBLE);

                optionsToCompleteLayout.setVisibility(View.INVISIBLE);

                btn_gift.setVisibility(View.VISIBLE);
                btn_gift.setText(R.string.str_gift);
                setGiftDownAndUp.start();
            }
        }

    }

    public void CaleaSpreSucces(View v) {
         Toast toast = Toast.makeText(this, "Calea spre succes...", Toast.LENGTH_LONG);
         toast.setGravity(Gravity.BOTTOM, 0, 0);

         toast.show();

     }

     public void IntretinereLaMinim(View v){
         Toast toast = Toast.makeText(this, "Bine, am înțeles...", Toast.LENGTH_SHORT);
         toast.setGravity(Gravity.BOTTOM, 0, 0);

         // Toast improvement, never click twice, just once after each 3s
         long now = System.currentTimeMillis();

         if (lastToastTime + TOAST_TIMEOUT_MS < now) {
             toast.show();
             lastToastTime = now;
         }
     }

     public void GoToFinalResultActivity(View v){
         Intent goToFinalResultActivity = new Intent(MainActivity.this, FinalResultActivity.class);

         Pair[] pairs = new Pair[4];

         pairs[0] = new Pair<View, String>(btn_play_final, "buttonTransition");
         pairs[1] = new Pair<View, String>(txt_play_final, "textTransition");

         pairs[2] = new Pair<View, String>(btn_play_complete, "completeTransition");
         pairs[3] = new Pair<View, String>(txt_play_complete, "completeTransition");

         goToFinalResultActivity.putExtra("salariu", salariu);

         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

         if ((checkBox_functie_de_baza_DA.isChecked() || checkBox_functie_de_baza_NU.isChecked()) && (checkBox_scutit_de_impozit_DA.isChecked() || checkBox_scutit_de_impozit_NU.isChecked())) {
             startActivity(goToFinalResultActivity, options.toBundle());
         }
         else {
             new AlertDialog.Builder(MainActivity.this).setTitle("⚠ Puțină atenție, vă rog!").setMessage("Completați formularul. Mulțumesc! \uD83D\uDD75️\u200D♂️").setPositiveButton(android.R.string.ok, null).setCancelable(false).show();

             // Navbar-fullscreen
             hideNavigationBar();
         }

     }
}