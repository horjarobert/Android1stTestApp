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
import android.content.DialogInterface;
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
    private Button btn_play_complete, btn_play_final, btn_romania, btn_gift, btn_diamond, btn_freestyle_1, btn_freestyle_2, btn_freestyle_3,
            btn_freestyle_4, btn_freestyle_5, btn_freestyle_6, btn_freestyle_7, btn_freestyle_8, btn_freestyle_9, btn_freestyle_10,
            btn_freestyle_11, btn_freestyle_12, btn_money_1, btn_money_2, btn_money_3, btn_money_4, btn_money_5, btn_plus, btn_minus;

    private TextView txt_title, txt_play_complete, txt_play_final, txt_copyright, txt_numar_persoane_in_intretinere;

    private TextInputLayout txt_input_layout;

    private TextInputEditText txt_input_edit_text;

    private ConstraintLayout mainLayout, optionsToCompleteLayout, bar_1, bar_2, bar_3, stopLayout;

    private Animation anim_txt_title, anim_btn_romania, anim_btn_diamond, anim_btn_play_complete, anim_txt_copyright, anim_txt_play_complete;

    private AnimatorSet setGiftDownAndUp;

    private Animator scaleGiftDown;

    private Animator scaleGiftUp;

    private ConstraintSet constraintSetActivityOLD = new ConstraintSet();
    private ConstraintSet constraintSetActivityNEW = new ConstraintSet();

    private Handler handler1, handler2, handler3, handler4, handler5, handler6, handler7, handler8, handler9, handler10, handler11,
            handler12, handler13, handler14, handler15, handler16, handler17, handler18, handler19, handler20, handler21,
            handler22, handler23, handler24, handler25, handler26, handler27, handler28, handler29, handler30, handler31,
            handler32, handler33, handler34, handler35, handler36, handler37, handler38;

    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7, runnable8, runnable9,
            runnable10, runnable11, runnable12, runnable13, runnable14, runnable15, runnable16, runnable17, runnable18,
            runnable19, runnable20, runnable21, runnable22, runnable23, runnable24, runnable25, runnable26, runnable27,
            runnable28, runnable29, runnable30, runnable31, runnable32, runnable33, runnable34, runnable35, runnable36,
            runnable37, runnable38;

    private CheckBox checkBox_functie_de_baza_DA, checkBox_functie_de_baza_NU, checkBox_scutit_de_impozit_DA, checkBox_scutit_de_impozit_NU;

    private boolean giftIsClicked = false, playIsClicked,
            isFunctieDeBaza_YES, isFunctieDeBaza_NO, isScutitDeImpozit_YES, isScutitDeImpozit_NO,
            isFreestyleAnimationClicked = false;

    private int nrPersoaneInIntretinere;
    private int n = 12;
    private int txt_salariu_brut;

    private float salariu;

    private static final long TOAST_TIMEOUT_MS = 2000;
    private static long lastToastTime = 0;

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
        stopLayout = findViewById(R.id.stopLayout);

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
                    AlertDialog.Builder limita = new AlertDialog.Builder(MainActivity.this);
                    limita.setTitle("\uD83D\uDC40 Ați ajuns la limită! ⌛");
                    limita.setMessage("\tVă rog, nu mințiți!\n\n\tMulțumesc! ☺");
                    limita.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            hideNavigationBar();
                        }
                    }).setCancelable(false).show();

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

        if (giftIsClicked) {
            stopLayout.setVisibility(View.VISIBLE);
        }
        else {
            stopLayout.setVisibility(View.INVISIBLE);
        }


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
        stopLayout.setVisibility(View.INVISIBLE);

        // Freestyle animations on click to btn_gift
        btn_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopLayout.setVisibility(View.VISIBLE);

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
                    isFunctieDeBaza_YES = true;
                }
                else {
                    checkBox_functie_de_baza_NU.setEnabled(true);
                    isFunctieDeBaza_YES = false;
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
                    isFunctieDeBaza_NO = true;
                }
                else {
                    checkBox_functie_de_baza_DA.setEnabled(true);
                    isFunctieDeBaza_NO = false;
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
                    isScutitDeImpozit_YES = true;
                }
                else {
                    checkBox_scutit_de_impozit_NU.setEnabled(true);
                    isScutitDeImpozit_YES = false;
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
                    isScutitDeImpozit_NO = true;

                }
                else {
                    checkBox_scutit_de_impozit_DA.setEnabled(true);
                    isScutitDeImpozit_NO = false;
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

                    AlertDialog.Builder limita = new AlertDialog.Builder(MainActivity.this);
                    limita.setTitle("\uD83D\uDC40 Ați ajuns la limită! ⌛");
                    limita.setMessage("\tVă rog, nu mințiți!\n\n\tMulțumesc! ☺");
                    limita.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            hideNavigationBar();
                        }
                    }).setCancelable(false).show();

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

            AlertDialog.Builder atentie = new AlertDialog.Builder(MainActivity.this);
            atentie.setTitle("⚠ Puțină atenție, vă rog!");
            atentie.setMessage("\tIntroduceți salariul în zona din mijloc.\n\n\tMulțumesc! \uD83D\uDD75️\u200D♂️");
            atentie.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    hideNavigationBar();
                }
            }).setCancelable(false).show();

            // Navbar-fullscreen
            hideNavigationBar();
        }

        else {
            txt_salariu_brut = Integer.parseInt(txt_input_edit_text.getText().toString());

            if (txt_salariu_brut == 0) {

                AlertDialog.Builder atentie = new AlertDialog.Builder(MainActivity.this);
                atentie.setTitle("⚠ Mare atenție, vă rog!");
                atentie.setMessage("\t\tNu se acceptă valoarea 0! Muncitorul trebuie răsplătit, nu umilit!\n\n\nMulțumesc! \uD83D\uDD75️\u200D♂️");
                atentie.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        hideNavigationBar();
                    }
                }).setCancelable(false).show();

                // Navbar-fullscreen
                hideNavigationBar();
            }
            else if (txt_salariu_brut <= 574) {

                AlertDialog.Builder atentie = new AlertDialog.Builder(MainActivity.this);
                atentie.setTitle("⚠ Mare atenție, vă rog!");
                atentie.setMessage("\t\tÎn România, pentru anul 2021, salariul minim pe economie este 2300 de lei, iar la cea mai mică normă (două ore lucrate pe zi), salariul brut este 575 de lei.\n\n\t\tNu se acceptă o valoare introdusă mai mică decât 575 de lei. Totul trebuie să fie legal!\n\n\nMulțumesc! \uD83D\uDD75️\u200D♂️");
                atentie.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        hideNavigationBar();
                    }
                }).setCancelable(false).show();

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
                    stopLayout.setVisibility(View.INVISIBLE);

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
    }

    public void CaleaSpreSucces(View v) {
         Toast toast = Toast.makeText(this, "Calea spre succes...", Toast.LENGTH_SHORT);
         toast.setGravity(Gravity.BOTTOM, 0, 0);

        // Toast improvement, never click twice, just once after each 3s
        long now = System.currentTimeMillis();

        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast.show();
            lastToastTime = now;
        }

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
         goToFinalResultActivity.putExtra("isFunctieDeBaza_YES", isFunctieDeBaza_YES);
         goToFinalResultActivity.putExtra("isFunctieDeBaza_NO", isFunctieDeBaza_NO);
         goToFinalResultActivity.putExtra("isScutitDeImpozit_YES", isScutitDeImpozit_YES);
         goToFinalResultActivity.putExtra("isScutitDeImpozit_NO", isScutitDeImpozit_NO);
         goToFinalResultActivity.putExtra("nrPersoaneInIntretinere", nrPersoaneInIntretinere);

         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

         if ((checkBox_functie_de_baza_DA.isChecked() || checkBox_functie_de_baza_NU.isChecked()) && (checkBox_scutit_de_impozit_DA.isChecked() || checkBox_scutit_de_impozit_NU.isChecked())) {
             startActivity(goToFinalResultActivity, options.toBundle());
         }
         else {

             AlertDialog.Builder atentie = new AlertDialog.Builder(MainActivity.this);
             atentie.setTitle("⚠ Puțină atenție, vă rog!");
             atentie.setMessage("\tCompletați formularul.\n\n\tMulțumesc! \uD83D\uDD75️\u200D♂️");
             atentie.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                     hideNavigationBar();
                 }
             }).setCancelable(false).show();

             // Navbar-fullscreen
             hideNavigationBar();
         }

     }

     public void StopFreestyleAnimations(View v) {
         // Stop freestyle animations
         stopLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 isFreestyleAnimationClicked = true;
                 if (isFreestyleAnimationClicked) {
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

                     btn_gift.setVisibility(View.VISIBLE);
                     btn_gift.setText(R.string.str_gift);

                     setGiftDownAndUp.start();
                 }
             }
         });
     }
}