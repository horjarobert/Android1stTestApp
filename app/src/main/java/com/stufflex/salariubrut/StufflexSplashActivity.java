package com.stufflex.salariubrut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class StufflexSplashActivity extends AppCompatActivity {

    // Declarations
    private ConstraintLayout clickToEndLayout;

    private TextView txt_changeable_letter, txt_changeable_f_1, txt_changeable_f_2, txt_letter_S,  txt_letter_t, txt_letter_u,
            txt_letter_f_1, txt_letter_f_2, txt_letter_l, txt_letter_e, txt_letter_x, txt_letter_b, txt_letter_y;

    private TextView txt_a_1, txt_a_2, txt_a_3, txt_a_4, txt_a_5, txt_a_6, txt_a_7, txt_a_8, txt_a_9, txt_a_10, txt_a_11, txt_a_12, txt_a_13, txt_a_14,
            txt_a_15, txt_a_16, txt_a_17, txt_a_18, txt_a_19, txt_a_20;

    private TextView txt_b_1, txt_b_2, txt_b_3, txt_b_4, txt_b_5, txt_b_6, txt_b_7, txt_b_8, txt_b_9, txt_b_10, txt_b_11, txt_b_12, txt_b_13, txt_b_14,
            txt_b_15, txt_b_16, txt_b_17, txt_b_18, txt_b_19, txt_b_20;

    private TextView txt_c_1, txt_c_2, txt_c_3, txt_c_4, txt_c_5, txt_c_6, txt_c_7, txt_c_8, txt_c_9, txt_c_10, txt_c_11, txt_c_12, txt_c_13, txt_c_14,
            txt_c_15, txt_c_16, txt_c_17, txt_c_18, txt_c_19, txt_c_20;

    private TextView txt_d_1, txt_d_2, txt_d_3, txt_d_4, txt_d_5, txt_d_6, txt_d_7, txt_d_8, txt_d_9, txt_d_10, txt_d_11, txt_d_12, txt_d_13, txt_d_14,
            txt_d_15, txt_d_16, txt_d_17, txt_d_18, txt_d_19, txt_d_20;

    private TextView txt_e_1, txt_e_2, txt_e_3, txt_e_4, txt_e_5, txt_e_6, txt_e_7, txt_e_8, txt_e_9, txt_e_10, txt_e_11, txt_e_12, txt_e_13, txt_e_14,
            txt_e_15, txt_e_16, txt_e_17, txt_e_18, txt_e_19, txt_e_20;

    private TextView txt_f_1, txt_f_2, txt_f_3, txt_f_4, txt_f_5, txt_f_6, txt_f_7, txt_f_8, txt_f_9, txt_f_10, txt_f_11, txt_f_12, txt_f_13, txt_f_14,
            txt_f_15, txt_f_16, txt_f_17, txt_f_18, txt_f_19, txt_f_20;

    private TextView txt_g_1, txt_g_2, txt_g_3, txt_g_4, txt_g_5, txt_g_6, txt_g_7, txt_g_8, txt_g_9, txt_g_10, txt_g_11, txt_g_12, txt_g_13, txt_g_14,
            txt_g_15, txt_g_16, txt_g_17, txt_g_18, txt_g_19, txt_g_20;

    private TextView txt_h_1, txt_h_2, txt_h_3, txt_h_4, txt_h_5, txt_h_6, txt_h_7, txt_h_8, txt_h_9, txt_h_10, txt_h_11, txt_h_12, txt_h_13, txt_h_14,
            txt_h_15, txt_h_16, txt_h_17, txt_h_18, txt_h_19, txt_h_20;

    private TextView txt_i_1, txt_i_2, txt_i_3, txt_i_4, txt_i_5, txt_i_6, txt_i_7, txt_i_8, txt_i_9, txt_i_10, txt_i_11, txt_i_12, txt_i_13, txt_i_14,
            txt_i_15, txt_i_16, txt_i_17, txt_i_18, txt_i_19, txt_i_20;

    private TextView txt_j_1, txt_j_2, txt_j_3, txt_j_4, txt_j_5, txt_j_6, txt_j_7, txt_j_8, txt_j_9, txt_j_10, txt_j_11, txt_j_12, txt_j_13, txt_j_14,
            txt_j_15, txt_j_16, txt_j_17, txt_j_18, txt_j_19, txt_j_20;

    private Animation anim_letter_S, anim_letter_t, anim_letter_u, anim_letter_f_1, anim_letter_f_2, anim_letter_l, anim_letter_e, anim_letter_x,
            anim_letter_b, anim_letter_y, anim_txt_changeable_f_1, anim_txt_changeable_f_2;

    private int SPLASH_DISPLAY_LENGTH = 7380;

    private Handler handler1, handler2, handler3, handler4, handler5, handler6, handler7, handler8, handler9, handlerSpecial;

    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7, runnable8, runnable9, runnableSpecial;

    private boolean isAnimStarting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stufflex_splash);

        // Navbar-fullscreen
        hideNavigationBar();

        // Disable screenshot option by using FLAG_SECURE
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        // Initializations
        clickToEndLayout = findViewById(R.id.clickToEndLayout);

        txt_changeable_letter = findViewById(R.id.txt_changeable_letter);
        txt_changeable_f_1 = findViewById(R.id.txt_changeable_f_1);
        txt_changeable_f_2 = findViewById(R.id.txt_changeable_f_2);
        txt_letter_S = findViewById(R.id.txt_letter_S);
        txt_letter_t = findViewById(R.id.txt_letter_t);
        txt_letter_u = findViewById(R.id.txt_letter_u);
        txt_letter_f_1 = findViewById(R.id.txt_letter_f_1);
        txt_letter_f_2 = findViewById(R.id.txt_letter_f_2);
        txt_letter_l = findViewById(R.id.txt_letter_l);
        txt_letter_e = findViewById(R.id.txt_letter_e);
        txt_letter_x = findViewById(R.id.txt_letter_x);
        txt_letter_b = findViewById(R.id.txt_letter_b);
        txt_letter_y = findViewById(R.id.txt_letter_y);

        txt_a_1 = findViewById(R.id.txt_a_1);
        txt_a_2 = findViewById(R.id.txt_a_2);
        txt_a_3 = findViewById(R.id.txt_a_3);
        txt_a_4 = findViewById(R.id.txt_a_4);
        txt_a_5 = findViewById(R.id.txt_a_5);
        txt_a_6 = findViewById(R.id.txt_a_6);
        txt_a_7 = findViewById(R.id.txt_a_7);
        txt_a_8 = findViewById(R.id.txt_a_8);
        txt_a_9 = findViewById(R.id.txt_a_9);
        txt_a_10 = findViewById(R.id.txt_a_10);
        txt_a_11 = findViewById(R.id.txt_a_11);
        txt_a_12 = findViewById(R.id.txt_a_12);
        txt_a_13 = findViewById(R.id.txt_a_13);
        txt_a_14 = findViewById(R.id.txt_a_14);
        txt_a_15 = findViewById(R.id.txt_a_15);
        txt_a_16 = findViewById(R.id.txt_a_16);
        txt_a_17 = findViewById(R.id.txt_a_17);
        txt_a_18 = findViewById(R.id.txt_a_18);
        txt_a_19 = findViewById(R.id.txt_a_19);
        txt_a_20 = findViewById(R.id.txt_a_20);

        txt_b_1 = findViewById(R.id.txt_b_1);
        txt_b_2 = findViewById(R.id.txt_b_2);
        txt_b_3 = findViewById(R.id.txt_b_3);
        txt_b_4 = findViewById(R.id.txt_b_4);
        txt_b_5 = findViewById(R.id.txt_b_5);
        txt_b_6 = findViewById(R.id.txt_b_6);
        txt_b_7 = findViewById(R.id.txt_b_7);
        txt_b_8 = findViewById(R.id.txt_b_8);
        txt_b_9 = findViewById(R.id.txt_b_9);
        txt_b_10 = findViewById(R.id.txt_b_10);
        txt_b_11 = findViewById(R.id.txt_b_11);
        txt_b_12 = findViewById(R.id.txt_b_12);
        txt_b_13 = findViewById(R.id.txt_b_13);
        txt_b_14 = findViewById(R.id.txt_b_14);
        txt_b_15 = findViewById(R.id.txt_b_15);
        txt_b_16 = findViewById(R.id.txt_b_16);
        txt_b_17 = findViewById(R.id.txt_b_17);
        txt_b_18 = findViewById(R.id.txt_b_18);
        txt_b_19 = findViewById(R.id.txt_b_19);
        txt_b_20 = findViewById(R.id.txt_b_20);

        txt_c_1 = findViewById(R.id.txt_c_1);
        txt_c_2 = findViewById(R.id.txt_c_2);
        txt_c_3 = findViewById(R.id.txt_c_3);
        txt_c_4 = findViewById(R.id.txt_c_4);
        txt_c_5 = findViewById(R.id.txt_c_5);
        txt_c_6 = findViewById(R.id.txt_c_6);
        txt_c_7 = findViewById(R.id.txt_c_7);
        txt_c_8 = findViewById(R.id.txt_c_8);
        txt_c_9 = findViewById(R.id.txt_c_9);
        txt_c_10 = findViewById(R.id.txt_c_10);
        txt_c_11 = findViewById(R.id.txt_c_11);
        txt_c_12 = findViewById(R.id.txt_c_12);
        txt_c_13 = findViewById(R.id.txt_c_13);
        txt_c_14 = findViewById(R.id.txt_c_14);
        txt_c_15 = findViewById(R.id.txt_c_15);
        txt_c_16 = findViewById(R.id.txt_c_16);
        txt_c_17 = findViewById(R.id.txt_c_17);
        txt_c_18 = findViewById(R.id.txt_c_18);
        txt_c_19 = findViewById(R.id.txt_c_19);
        txt_c_20 = findViewById(R.id.txt_c_20);

        txt_d_1 = findViewById(R.id.txt_d_1);
        txt_d_2 = findViewById(R.id.txt_d_2);
        txt_d_3 = findViewById(R.id.txt_d_3);
        txt_d_4 = findViewById(R.id.txt_d_4);
        txt_d_5 = findViewById(R.id.txt_d_5);
        txt_d_6 = findViewById(R.id.txt_d_6);
        txt_d_7 = findViewById(R.id.txt_d_7);
        txt_d_8 = findViewById(R.id.txt_d_8);
        txt_d_9 = findViewById(R.id.txt_d_9);
        txt_d_10 = findViewById(R.id.txt_d_10);
        txt_d_11 = findViewById(R.id.txt_d_11);
        txt_d_12 = findViewById(R.id.txt_d_12);
        txt_d_13 = findViewById(R.id.txt_d_13);
        txt_d_14 = findViewById(R.id.txt_d_14);
        txt_d_15 = findViewById(R.id.txt_d_15);
        txt_d_16 = findViewById(R.id.txt_d_16);
        txt_d_17 = findViewById(R.id.txt_d_17);
        txt_d_18 = findViewById(R.id.txt_d_18);
        txt_d_19 = findViewById(R.id.txt_d_19);
        txt_d_20 = findViewById(R.id.txt_d_20);

        txt_e_1 = findViewById(R.id.txt_e_1);
        txt_e_2 = findViewById(R.id.txt_e_2);
        txt_e_3 = findViewById(R.id.txt_e_3);
        txt_e_4 = findViewById(R.id.txt_e_4);
        txt_e_5 = findViewById(R.id.txt_e_5);
        txt_e_6 = findViewById(R.id.txt_e_6);
        txt_e_7 = findViewById(R.id.txt_e_7);
        txt_e_8 = findViewById(R.id.txt_e_8);
        txt_e_9 = findViewById(R.id.txt_e_9);
        txt_e_10 = findViewById(R.id.txt_e_10);
        txt_e_11 = findViewById(R.id.txt_e_11);
        txt_e_12 = findViewById(R.id.txt_e_12);
        txt_e_13 = findViewById(R.id.txt_e_13);
        txt_e_14 = findViewById(R.id.txt_e_14);
        txt_e_15 = findViewById(R.id.txt_e_15);
        txt_e_16 = findViewById(R.id.txt_e_16);
        txt_e_17 = findViewById(R.id.txt_e_17);
        txt_e_18 = findViewById(R.id.txt_e_18);
        txt_e_19 = findViewById(R.id.txt_e_19);
        txt_e_20 = findViewById(R.id.txt_e_20);

        txt_f_1 = findViewById(R.id.txt_f_1);
        txt_f_2 = findViewById(R.id.txt_f_2);
        txt_f_3 = findViewById(R.id.txt_f_3);
        txt_f_4 = findViewById(R.id.txt_f_4);
        txt_f_5 = findViewById(R.id.txt_f_5);
        txt_f_6 = findViewById(R.id.txt_f_6);
        txt_f_7 = findViewById(R.id.txt_f_7);
        txt_f_8 = findViewById(R.id.txt_f_8);
        txt_f_9 = findViewById(R.id.txt_f_9);
        txt_f_10 = findViewById(R.id.txt_f_10);
        txt_f_11 = findViewById(R.id.txt_f_11);
        txt_f_12 = findViewById(R.id.txt_f_12);
        txt_f_13 = findViewById(R.id.txt_f_13);
        txt_f_14 = findViewById(R.id.txt_f_14);
        txt_f_15 = findViewById(R.id.txt_f_15);
        txt_f_16 = findViewById(R.id.txt_f_16);
        txt_f_17 = findViewById(R.id.txt_f_17);
        txt_f_18 = findViewById(R.id.txt_f_18);
        txt_f_19 = findViewById(R.id.txt_f_19);
        txt_f_20 = findViewById(R.id.txt_f_20);

        txt_g_1 = findViewById(R.id.txt_g_1);
        txt_g_2 = findViewById(R.id.txt_g_2);
        txt_g_3 = findViewById(R.id.txt_g_3);
        txt_g_4 = findViewById(R.id.txt_g_4);
        txt_g_5 = findViewById(R.id.txt_g_5);
        txt_g_6 = findViewById(R.id.txt_g_6);
        txt_g_7 = findViewById(R.id.txt_g_7);
        txt_g_8 = findViewById(R.id.txt_g_8);
        txt_g_9 = findViewById(R.id.txt_g_9);
        txt_g_10 = findViewById(R.id.txt_g_10);
        txt_g_11 = findViewById(R.id.txt_g_11);
        txt_g_12 = findViewById(R.id.txt_g_12);
        txt_g_13 = findViewById(R.id.txt_g_13);
        txt_g_14 = findViewById(R.id.txt_g_14);
        txt_g_15 = findViewById(R.id.txt_g_15);
        txt_g_16 = findViewById(R.id.txt_g_16);
        txt_g_17 = findViewById(R.id.txt_g_17);
        txt_g_18 = findViewById(R.id.txt_g_18);
        txt_g_19 = findViewById(R.id.txt_g_19);
        txt_g_20 = findViewById(R.id.txt_g_20);

        txt_h_1 = findViewById(R.id.txt_h_1);
        txt_h_2 = findViewById(R.id.txt_h_2);
        txt_h_3 = findViewById(R.id.txt_h_3);
        txt_h_4 = findViewById(R.id.txt_h_4);
        txt_h_5 = findViewById(R.id.txt_h_5);
        txt_h_6 = findViewById(R.id.txt_h_6);
        txt_h_7 = findViewById(R.id.txt_h_7);
        txt_h_8 = findViewById(R.id.txt_h_8);
        txt_h_9 = findViewById(R.id.txt_h_9);
        txt_h_10 = findViewById(R.id.txt_h_10);
        txt_h_11 = findViewById(R.id.txt_h_11);
        txt_h_12 = findViewById(R.id.txt_h_12);
        txt_h_13 = findViewById(R.id.txt_h_13);
        txt_h_14 = findViewById(R.id.txt_h_14);
        txt_h_15 = findViewById(R.id.txt_h_15);
        txt_h_16 = findViewById(R.id.txt_h_16);
        txt_h_17 = findViewById(R.id.txt_h_17);
        txt_h_18 = findViewById(R.id.txt_h_18);
        txt_h_19 = findViewById(R.id.txt_h_19);
        txt_h_20 = findViewById(R.id.txt_h_20);

        txt_i_1 = findViewById(R.id.txt_i_1);
        txt_i_2 = findViewById(R.id.txt_i_2);
        txt_i_3 = findViewById(R.id.txt_i_3);
        txt_i_4 = findViewById(R.id.txt_i_4);
        txt_i_5 = findViewById(R.id.txt_i_5);
        txt_i_6 = findViewById(R.id.txt_i_6);
        txt_i_7 = findViewById(R.id.txt_i_7);
        txt_i_8 = findViewById(R.id.txt_i_8);
        txt_i_9 = findViewById(R.id.txt_i_9);
        txt_i_10 = findViewById(R.id.txt_i_10);
        txt_i_11 = findViewById(R.id.txt_i_11);
        txt_i_12 = findViewById(R.id.txt_i_12);
        txt_i_13 = findViewById(R.id.txt_i_13);
        txt_i_14 = findViewById(R.id.txt_i_14);
        txt_i_15 = findViewById(R.id.txt_i_15);
        txt_i_16 = findViewById(R.id.txt_i_16);
        txt_i_17 = findViewById(R.id.txt_i_17);
        txt_i_18 = findViewById(R.id.txt_i_18);
        txt_i_19 = findViewById(R.id.txt_i_19);
        txt_i_20 = findViewById(R.id.txt_i_20);

        txt_j_1 = findViewById(R.id.txt_j_1);
        txt_j_2 = findViewById(R.id.txt_j_2);
        txt_j_3 = findViewById(R.id.txt_j_3);
        txt_j_4 = findViewById(R.id.txt_j_4);
        txt_j_5 = findViewById(R.id.txt_j_5);
        txt_j_6 = findViewById(R.id.txt_j_6);
        txt_j_7 = findViewById(R.id.txt_j_7);
        txt_j_8 = findViewById(R.id.txt_j_8);
        txt_j_9 = findViewById(R.id.txt_j_9);
        txt_j_10 = findViewById(R.id.txt_j_10);
        txt_j_11 = findViewById(R.id.txt_j_11);
        txt_j_12 = findViewById(R.id.txt_j_12);
        txt_j_13 = findViewById(R.id.txt_j_13);
        txt_j_14 = findViewById(R.id.txt_j_14);
        txt_j_15 = findViewById(R.id.txt_j_15);
        txt_j_16 = findViewById(R.id.txt_j_16);
        txt_j_17 = findViewById(R.id.txt_j_17);
        txt_j_18 = findViewById(R.id.txt_j_18);
        txt_j_19 = findViewById(R.id.txt_j_19);
        txt_j_20 = findViewById(R.id.txt_j_20);

        // Set texts to invisible
        txt_changeable_letter.setVisibility(View.INVISIBLE);
        txt_letter_S.setVisibility(View.INVISIBLE);
        txt_letter_t.setVisibility(View.INVISIBLE);
        txt_letter_u.setVisibility(View.INVISIBLE);
        txt_letter_f_1.setVisibility(View.INVISIBLE);
        txt_letter_f_2.setVisibility(View.INVISIBLE);
        txt_letter_l.setVisibility(View.INVISIBLE);
        txt_letter_e.setVisibility(View.INVISIBLE);
        txt_letter_x.setVisibility(View.INVISIBLE);

        txt_a_1.setVisibility(View.INVISIBLE);
        txt_a_2.setVisibility(View.INVISIBLE);
        txt_a_3.setVisibility(View.INVISIBLE);
        txt_a_4.setVisibility(View.INVISIBLE);
        txt_a_5.setVisibility(View.INVISIBLE);
        txt_a_6.setVisibility(View.INVISIBLE);
        txt_a_7.setVisibility(View.INVISIBLE);
        txt_a_8.setVisibility(View.INVISIBLE);
        txt_a_9.setVisibility(View.INVISIBLE);
        txt_a_10.setVisibility(View.INVISIBLE);
        txt_a_11.setVisibility(View.INVISIBLE);
        txt_a_12.setVisibility(View.INVISIBLE);
        txt_a_13.setVisibility(View.INVISIBLE);
        txt_a_14.setVisibility(View.INVISIBLE);
        txt_a_15.setVisibility(View.INVISIBLE);
        txt_a_16.setVisibility(View.INVISIBLE);
        txt_a_17.setVisibility(View.INVISIBLE);
        txt_a_18.setVisibility(View.INVISIBLE);
        txt_a_19.setVisibility(View.INVISIBLE);
        txt_a_20.setVisibility(View.INVISIBLE);

        txt_b_1.setVisibility(View.INVISIBLE);
        txt_b_2.setVisibility(View.INVISIBLE);
        txt_b_3.setVisibility(View.INVISIBLE);
        txt_b_4.setVisibility(View.INVISIBLE);
        txt_b_5.setVisibility(View.INVISIBLE);
        txt_b_6.setVisibility(View.INVISIBLE);
        txt_b_7.setVisibility(View.INVISIBLE);
        txt_b_8.setVisibility(View.INVISIBLE);
        txt_b_9.setVisibility(View.INVISIBLE);
        txt_b_10.setVisibility(View.INVISIBLE);
        txt_b_11.setVisibility(View.INVISIBLE);
        txt_b_12.setVisibility(View.INVISIBLE);
        txt_b_13.setVisibility(View.INVISIBLE);
        txt_b_14.setVisibility(View.INVISIBLE);
        txt_b_15.setVisibility(View.INVISIBLE);
        txt_b_16.setVisibility(View.INVISIBLE);
        txt_b_17.setVisibility(View.INVISIBLE);
        txt_b_18.setVisibility(View.INVISIBLE);
        txt_b_19.setVisibility(View.INVISIBLE);
        txt_b_20.setVisibility(View.INVISIBLE);

        txt_c_1.setVisibility(View.INVISIBLE);
        txt_c_2.setVisibility(View.INVISIBLE);
        txt_c_3.setVisibility(View.INVISIBLE);
        txt_c_4.setVisibility(View.INVISIBLE);
        txt_c_5.setVisibility(View.INVISIBLE);
        txt_c_6.setVisibility(View.INVISIBLE);
        txt_c_7.setVisibility(View.INVISIBLE);
        txt_c_8.setVisibility(View.INVISIBLE);
        txt_c_9.setVisibility(View.INVISIBLE);
        txt_c_10.setVisibility(View.INVISIBLE);
        txt_c_12.setVisibility(View.INVISIBLE);
        txt_c_13.setVisibility(View.INVISIBLE);
        txt_c_14.setVisibility(View.INVISIBLE);
        txt_c_15.setVisibility(View.INVISIBLE);
        txt_c_16.setVisibility(View.INVISIBLE);
        txt_c_17.setVisibility(View.INVISIBLE);
        txt_c_18.setVisibility(View.INVISIBLE);
        txt_c_19.setVisibility(View.INVISIBLE);
        txt_c_20.setVisibility(View.INVISIBLE);

        txt_d_1.setVisibility(View.INVISIBLE);
        txt_d_2.setVisibility(View.INVISIBLE);
        txt_d_3.setVisibility(View.INVISIBLE);
        txt_d_4.setVisibility(View.INVISIBLE);
        txt_d_5.setVisibility(View.INVISIBLE);
        txt_d_6.setVisibility(View.INVISIBLE);
        txt_d_7.setVisibility(View.INVISIBLE);
        txt_d_8.setVisibility(View.INVISIBLE);
        txt_d_9.setVisibility(View.INVISIBLE);
        txt_d_10.setVisibility(View.INVISIBLE);
        txt_d_11.setVisibility(View.INVISIBLE);
        txt_d_12.setVisibility(View.INVISIBLE);
        txt_d_13.setVisibility(View.INVISIBLE);
        txt_d_14.setVisibility(View.INVISIBLE);
        txt_d_15.setVisibility(View.INVISIBLE);
        txt_d_16.setVisibility(View.INVISIBLE);
        txt_d_17.setVisibility(View.INVISIBLE);
        txt_d_18.setVisibility(View.INVISIBLE);
        txt_d_19.setVisibility(View.INVISIBLE);
        txt_d_20.setVisibility(View.INVISIBLE);

        txt_e_1.setVisibility(View.INVISIBLE);
        txt_e_2.setVisibility(View.INVISIBLE);
        txt_e_3.setVisibility(View.INVISIBLE);
        txt_e_4.setVisibility(View.INVISIBLE);
        txt_e_5.setVisibility(View.INVISIBLE);
        txt_e_6.setVisibility(View.INVISIBLE);
        txt_e_7.setVisibility(View.INVISIBLE);
        txt_e_8.setVisibility(View.INVISIBLE);
        txt_e_9.setVisibility(View.INVISIBLE);
        txt_e_10.setVisibility(View.INVISIBLE);
        txt_e_12.setVisibility(View.INVISIBLE);
        txt_e_13.setVisibility(View.INVISIBLE);
        txt_e_14.setVisibility(View.INVISIBLE);
        txt_e_15.setVisibility(View.INVISIBLE);
        txt_e_16.setVisibility(View.INVISIBLE);
        txt_e_17.setVisibility(View.INVISIBLE);
        txt_e_18.setVisibility(View.INVISIBLE);
        txt_e_19.setVisibility(View.INVISIBLE);
        txt_e_20.setVisibility(View.INVISIBLE);

        txt_f_1.setVisibility(View.INVISIBLE);
        txt_f_2.setVisibility(View.INVISIBLE);
        txt_f_3.setVisibility(View.INVISIBLE);
        txt_f_4.setVisibility(View.INVISIBLE);
        txt_f_5.setVisibility(View.INVISIBLE);
        txt_f_6.setVisibility(View.INVISIBLE);
        txt_f_7.setVisibility(View.INVISIBLE);
        txt_f_8.setVisibility(View.INVISIBLE);
        txt_f_9.setVisibility(View.INVISIBLE);
        txt_f_10.setVisibility(View.INVISIBLE);
        txt_f_11.setVisibility(View.INVISIBLE);
        txt_f_12.setVisibility(View.INVISIBLE);
        txt_f_13.setVisibility(View.INVISIBLE);
        txt_f_14.setVisibility(View.INVISIBLE);
        txt_f_15.setVisibility(View.INVISIBLE);
        txt_f_16.setVisibility(View.INVISIBLE);
        txt_f_17.setVisibility(View.INVISIBLE);
        txt_f_18.setVisibility(View.INVISIBLE);
        txt_f_19.setVisibility(View.INVISIBLE);
        txt_f_20.setVisibility(View.INVISIBLE);

        txt_g_1.setVisibility(View.INVISIBLE);
        txt_g_2.setVisibility(View.INVISIBLE);
        txt_g_3.setVisibility(View.INVISIBLE);
        txt_g_4.setVisibility(View.INVISIBLE);
        txt_g_5.setVisibility(View.INVISIBLE);
        txt_g_6.setVisibility(View.INVISIBLE);
        txt_g_7.setVisibility(View.INVISIBLE);
        txt_g_8.setVisibility(View.INVISIBLE);
        txt_g_9.setVisibility(View.INVISIBLE);
        txt_g_10.setVisibility(View.INVISIBLE);
        txt_g_11.setVisibility(View.INVISIBLE);
        txt_g_12.setVisibility(View.INVISIBLE);
        txt_g_13.setVisibility(View.INVISIBLE);
        txt_g_14.setVisibility(View.INVISIBLE);
        txt_g_15.setVisibility(View.INVISIBLE);
        txt_g_16.setVisibility(View.INVISIBLE);
        txt_g_17.setVisibility(View.INVISIBLE);
        txt_g_18.setVisibility(View.INVISIBLE);
        txt_g_19.setVisibility(View.INVISIBLE);
        txt_g_20.setVisibility(View.INVISIBLE);

        txt_h_1.setVisibility(View.INVISIBLE);
        txt_h_2.setVisibility(View.INVISIBLE);
        txt_h_3.setVisibility(View.INVISIBLE);
        txt_h_4.setVisibility(View.INVISIBLE);
        txt_h_5.setVisibility(View.INVISIBLE);
        txt_h_6.setVisibility(View.INVISIBLE);
        txt_h_7.setVisibility(View.INVISIBLE);
        txt_h_8.setVisibility(View.INVISIBLE);
        txt_h_9.setVisibility(View.INVISIBLE);
        txt_h_10.setVisibility(View.INVISIBLE);
        txt_h_11.setVisibility(View.INVISIBLE);
        txt_h_12.setVisibility(View.INVISIBLE);
        txt_h_13.setVisibility(View.INVISIBLE);
        txt_h_14.setVisibility(View.INVISIBLE);
        txt_h_15.setVisibility(View.INVISIBLE);
        txt_h_16.setVisibility(View.INVISIBLE);
        txt_h_17.setVisibility(View.INVISIBLE);
        txt_h_18.setVisibility(View.INVISIBLE);
        txt_h_19.setVisibility(View.INVISIBLE);
        txt_h_20.setVisibility(View.INVISIBLE);

        txt_i_1.setVisibility(View.INVISIBLE);
        txt_i_2.setVisibility(View.INVISIBLE);
        txt_i_3.setVisibility(View.INVISIBLE);
        txt_i_4.setVisibility(View.INVISIBLE);
        txt_i_5.setVisibility(View.INVISIBLE);
        txt_i_6.setVisibility(View.INVISIBLE);
        txt_i_7.setVisibility(View.INVISIBLE);
        txt_i_8.setVisibility(View.INVISIBLE);
        txt_i_9.setVisibility(View.INVISIBLE);
        txt_i_10.setVisibility(View.INVISIBLE);
        txt_i_11.setVisibility(View.INVISIBLE);
        txt_i_12.setVisibility(View.INVISIBLE);
        txt_i_13.setVisibility(View.INVISIBLE);
        txt_i_14.setVisibility(View.INVISIBLE);
        txt_i_15.setVisibility(View.INVISIBLE);
        txt_i_16.setVisibility(View.INVISIBLE);
        txt_i_17.setVisibility(View.INVISIBLE);
        txt_i_18.setVisibility(View.INVISIBLE);
        txt_i_19.setVisibility(View.INVISIBLE);
        txt_i_20.setVisibility(View.INVISIBLE);

        txt_j_1.setVisibility(View.INVISIBLE);
        txt_j_2.setVisibility(View.INVISIBLE);
        txt_j_3.setVisibility(View.INVISIBLE);
        txt_j_4.setVisibility(View.INVISIBLE);
        txt_j_5.setVisibility(View.INVISIBLE);
        txt_j_6.setVisibility(View.INVISIBLE);
        txt_j_7.setVisibility(View.INVISIBLE);
        txt_j_8.setVisibility(View.INVISIBLE);
        txt_j_9.setVisibility(View.INVISIBLE);
        txt_j_10.setVisibility(View.INVISIBLE);
        txt_j_11.setVisibility(View.INVISIBLE);
        txt_j_12.setVisibility(View.INVISIBLE);
        txt_j_13.setVisibility(View.INVISIBLE);
        txt_j_14.setVisibility(View.INVISIBLE);
        txt_j_15.setVisibility(View.INVISIBLE);
        txt_j_16.setVisibility(View.INVISIBLE);
        txt_j_17.setVisibility(View.INVISIBLE);
        txt_j_18.setVisibility(View.INVISIBLE);
        txt_j_19.setVisibility(View.INVISIBLE);
        txt_j_20.setVisibility(View.INVISIBLE);

        // Set animations
        anim_letter_S = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_t = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_u = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_f_1 = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_f_2 = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_l = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_e = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_x = AnimationUtils.loadAnimation(this, R.anim.bottom_letters);
        anim_letter_b = AnimationUtils.loadAnimation(this, R.anim.b_letter);
        anim_letter_y = AnimationUtils.loadAnimation(this, R.anim.y_letter);
        anim_txt_changeable_f_1 = AnimationUtils.loadAnimation(this, R.anim.anim_f1);
        anim_txt_changeable_f_2 = AnimationUtils.loadAnimation(this, R.anim.anim_f2);

        txt_letter_b.setAnimation(anim_letter_b);
        txt_letter_y.setAnimation(anim_letter_y);

        StartAnimations();

        clickToEndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnimStarting = true;
                if (isAnimStarting) {
                    handlerSpecial.removeCallbacksAndMessages(null);
                    GoToMainActivity();
                }
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

    // Start Animations
    public void StartAnimations() {
        handler1 = new  Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setVisibility(View.VISIBLE);

                txt_letter_S.setVisibility(View.VISIBLE);
                txt_letter_S.setAnimation(anim_letter_S);
            }
        };
        handler1.postDelayed(runnable1, 1000);

        handler2 = new  Handler();
        runnable2 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setText(R.string.str_letter_t);

                txt_letter_t.setVisibility(View.VISIBLE);
                txt_letter_t.setAnimation(anim_letter_t);
            }
        };
        handler2.postDelayed(runnable2, 1300);

        handler3 = new  Handler();
        runnable3 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setText(R.string.str_letter_u);

                txt_letter_u.setVisibility(View.VISIBLE);
                txt_letter_u.setAnimation(anim_letter_u);
            }
        };
        handler3.postDelayed(runnable3, 1600);

        handler4 = new  Handler();
        runnable4 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setVisibility(View.INVISIBLE);
                txt_changeable_f_1.setVisibility(View.VISIBLE);

                txt_changeable_f_1.setAnimation(anim_txt_changeable_f_1);

                txt_letter_f_1.setVisibility(View.VISIBLE);
                txt_letter_f_1.setAnimation(anim_letter_f_1);

            }
        };
        handler4.postDelayed(runnable4, 1900);

        handler5 = new  Handler();
        runnable5 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_f_1.setVisibility(View.INVISIBLE);
                txt_changeable_f_2.setVisibility(View.VISIBLE);

                txt_changeable_f_2.setAnimation(anim_txt_changeable_f_2);

                txt_letter_f_2.setVisibility(View.VISIBLE);
                txt_letter_f_2.setAnimation(anim_letter_f_2);
            }
        };
        handler5.postDelayed(runnable5, 2300);

        handler6 = new  Handler();
        runnable6 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_f_2.setVisibility(View.INVISIBLE);
                txt_changeable_letter.setVisibility(View.VISIBLE);

                txt_changeable_letter.setText(R.string.str_letter_l);

                txt_letter_l.setVisibility(View.VISIBLE);
                txt_letter_l.setAnimation(anim_letter_l);
            }
        };
        handler6.postDelayed(runnable6, 2700);

        handler7 = new  Handler();
        runnable7 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setText(R.string.str_letter_e);

                txt_letter_e.setVisibility(View.VISIBLE);
                txt_letter_e.setAnimation(anim_letter_e);
            }
        };
        handler7.postDelayed(runnable7, 3000);

        handler8 = new  Handler();
        runnable8 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setVisibility(View.VISIBLE);
                txt_changeable_letter.setText(R.string.str_letter_x);

                txt_letter_x.setVisibility(View.VISIBLE);
                txt_letter_x.setAnimation(anim_letter_x);
            }
        };
        handler8.postDelayed(runnable8, 3300);

        handler9 = new  Handler();
        runnable9 = new Runnable() {
            @Override
            public void run() {
                txt_changeable_letter.setVisibility(View.INVISIBLE);
                txt_letter_b.setVisibility(View.INVISIBLE);
                txt_letter_y.setVisibility(View.INVISIBLE);
                txt_letter_S.setVisibility(View.INVISIBLE);
                txt_letter_t.setVisibility(View.INVISIBLE);
                txt_letter_u.setVisibility(View.INVISIBLE);
                txt_letter_f_1.setVisibility(View.INVISIBLE);
                txt_letter_f_2.setVisibility(View.INVISIBLE);
                txt_letter_l.setVisibility(View.INVISIBLE);
                txt_letter_e.setVisibility(View.INVISIBLE);
                txt_letter_x.setVisibility(View.INVISIBLE);
            }
        };
        handler9.postDelayed(runnable9, 4200);

        // New handler S design
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_i_4.setVisibility(View.VISIBLE);
                txt_b_17.setVisibility(View.VISIBLE);
            }
        }, 4300);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_h_3.setVisibility(View.VISIBLE);
                txt_c_18.setVisibility(View.VISIBLE);
            }
        }, 4400);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_g_2.setVisibility(View.VISIBLE);
                txt_d_19.setVisibility(View.VISIBLE);
            }
        }, 4500);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_f_2.setVisibility(View.VISIBLE);
                txt_e_19.setVisibility(View.VISIBLE);
            }
        }, 4600);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_2.setVisibility(View.VISIBLE);
                txt_f_19.setVisibility(View.VISIBLE);
            }
        }, 4700);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_2.setVisibility(View.VISIBLE);
                txt_g_19.setVisibility(View.VISIBLE);
            }
        }, 4800);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_3.setVisibility(View.VISIBLE);
                txt_h_18.setVisibility(View.VISIBLE);
            }
        }, 4900);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_4.setVisibility(View.VISIBLE);
                txt_i_17.setVisibility(View.VISIBLE);
            }
        }, 5000);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_5.setVisibility(View.VISIBLE);
                txt_i_16.setVisibility(View.VISIBLE);
            }
        }, 5100);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_6.setVisibility(View.VISIBLE);
                txt_i_15.setVisibility(View.VISIBLE);
            }
        }, 5200);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_7.setVisibility(View.VISIBLE);
                txt_i_14.setVisibility(View.VISIBLE);
            }
        }, 5300);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_8.setVisibility(View.VISIBLE);
                txt_h_13.setVisibility(View.VISIBLE);
            }
        }, 5400);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_9.setVisibility(View.VISIBLE);
                txt_g_12.setVisibility(View.VISIBLE);
            }
        }, 5500);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_10.setVisibility(View.VISIBLE);
                txt_f_11.setVisibility(View.VISIBLE);
            }
        }, 5600);

        // Full option
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_1.setVisibility(View.VISIBLE);
                txt_j_1.setVisibility(View.VISIBLE);
                txt_a_20.setVisibility(View.VISIBLE);
                txt_j_20.setVisibility(View.VISIBLE);
            }
        }, 5700);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_2.setVisibility(View.VISIBLE);
                txt_b_1.setVisibility(View.VISIBLE);
                txt_i_1.setVisibility(View.VISIBLE);
                txt_j_2.setVisibility(View.VISIBLE);
                txt_a_19.setVisibility(View.VISIBLE);
                txt_b_20.setVisibility(View.VISIBLE);
                txt_j_19.setVisibility(View.VISIBLE);
                txt_i_20.setVisibility(View.VISIBLE);
            }
        }, 5720);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_3.setVisibility(View.VISIBLE);
                txt_b_2.setVisibility(View.VISIBLE);
                txt_c_1.setVisibility(View.VISIBLE);
                txt_h_1.setVisibility(View.VISIBLE);
                txt_i_2.setVisibility(View.VISIBLE);
                txt_j_3.setVisibility(View.VISIBLE);
                txt_a_18.setVisibility(View.VISIBLE);
                txt_b_19.setVisibility(View.VISIBLE);
                txt_c_20.setVisibility(View.VISIBLE);
                txt_h_20.setVisibility(View.VISIBLE);
                txt_i_19.setVisibility(View.VISIBLE);
                txt_j_18.setVisibility(View.VISIBLE);
            }
        }, 5740);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_4.setVisibility(View.VISIBLE);
                txt_b_3.setVisibility(View.VISIBLE);
                txt_c_2.setVisibility(View.VISIBLE);
                txt_d_1.setVisibility(View.VISIBLE);
                txt_g_1.setVisibility(View.VISIBLE);
                txt_h_2.setVisibility(View.VISIBLE);
                txt_i_3.setVisibility(View.VISIBLE);
                txt_j_4.setVisibility(View.VISIBLE);
                txt_a_17.setVisibility(View.VISIBLE);
                txt_b_18.setVisibility(View.VISIBLE);
                txt_c_19.setVisibility(View.VISIBLE);
                txt_d_20.setVisibility(View.VISIBLE);
                txt_g_20.setVisibility(View.VISIBLE);
                txt_h_19.setVisibility(View.VISIBLE);
                txt_i_18.setVisibility(View.VISIBLE);
                txt_j_17.setVisibility(View.VISIBLE);
            }
        }, 5760);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_16.setVisibility(View.VISIBLE);
                txt_j_5.setVisibility(View.VISIBLE);
            }
        }, 5780);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_20.setVisibility(View.VISIBLE);
                txt_f_1.setVisibility(View.VISIBLE);
            }
        }, 5800);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_15.setVisibility(View.VISIBLE);
                txt_j_6.setVisibility(View.VISIBLE);
            }
        }, 5820);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_1.setVisibility(View.VISIBLE);
                txt_f_20.setVisibility(View.VISIBLE);
            }
        }, 5840);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_14.setVisibility(View.VISIBLE);
                txt_j_7.setVisibility(View.VISIBLE);
            }
        }, 5860);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_13.setVisibility(View.VISIBLE);
                txt_j_8.setVisibility(View.VISIBLE);
            }
        }, 5880);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_12.setVisibility(View.VISIBLE);
                txt_j_9.setVisibility(View.VISIBLE);
            }
        }, 5900);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_11.setVisibility(View.VISIBLE);
                txt_j_10.setVisibility(View.VISIBLE);
            }
        }, 5920);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_1.setVisibility(View.VISIBLE);
                txt_j_1.setVisibility(View.VISIBLE);
                txt_a_20.setVisibility(View.VISIBLE);
                txt_j_20.setVisibility(View.VISIBLE);
            }
        }, 5940);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_10.setVisibility(View.VISIBLE);
                txt_j_11.setVisibility(View.VISIBLE);
            }
        }, 5960);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_9.setVisibility(View.VISIBLE);
                txt_j_12.setVisibility(View.VISIBLE);
            }
        }, 5980);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_8.setVisibility(View.VISIBLE);
                txt_j_13.setVisibility(View.VISIBLE);
            }
        }, 6000);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_7.setVisibility(View.VISIBLE);
                txt_j_14.setVisibility(View.VISIBLE);
            }
        }, 6020);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_6.setVisibility(View.VISIBLE);
                txt_j_15.setVisibility(View.VISIBLE);
            }
        }, 6040);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_a_5.setVisibility(View.VISIBLE);
                txt_j_16.setVisibility(View.VISIBLE);
            }
        }, 6060);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_17.setVisibility(View.VISIBLE);
                txt_i_4.setVisibility(View.VISIBLE);
            }
        }, 6080);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_16.setVisibility(View.VISIBLE);
                txt_i_5.setVisibility(View.VISIBLE);
            }
        }, 6100);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_15.setVisibility(View.VISIBLE);
                txt_i_6.setVisibility(View.VISIBLE);
            }
        }, 6120);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_14.setVisibility(View.VISIBLE);
                txt_i_7.setVisibility(View.VISIBLE);
            }
        }, 6140);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_13.setVisibility(View.VISIBLE);
                txt_i_8.setVisibility(View.VISIBLE);
            }
        }, 6160);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_12.setVisibility(View.VISIBLE);
                txt_i_9.setVisibility(View.VISIBLE);
            }
        }, 6180);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_11.setVisibility(View.VISIBLE);
                txt_i_10.setVisibility(View.VISIBLE);
            }
        }, 6200);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_10.setVisibility(View.VISIBLE);
                txt_i_11.setVisibility(View.VISIBLE);
            }
        }, 6220);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_9.setVisibility(View.VISIBLE);
                txt_i_12.setVisibility(View.VISIBLE);
            }
        }, 6240);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_8.setVisibility(View.VISIBLE);
                txt_i_13.setVisibility(View.VISIBLE);
            }
        }, 6260);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_7.setVisibility(View.VISIBLE);
                txt_i_14.setVisibility(View.VISIBLE);
            }
        }, 6280);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_6.setVisibility(View.VISIBLE);
                txt_i_15.setVisibility(View.VISIBLE);
            }
        }, 6300);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_5.setVisibility(View.VISIBLE);
                txt_i_16.setVisibility(View.VISIBLE);
            }
        }, 6320);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_b_4.setVisibility(View.VISIBLE);
                txt_i_17.setVisibility(View.VISIBLE);
            }
        }, 6340);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_2.setVisibility(View.VISIBLE);
                txt_g_19.setVisibility(View.VISIBLE);
            }
        }, 6360);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_2.setVisibility(View.VISIBLE);
                txt_f_19.setVisibility(View.VISIBLE);
            }
        }, 6380);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_f_2.setVisibility(View.VISIBLE);
                txt_e_19.setVisibility(View.VISIBLE);
            }
        }, 6400);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_f_2.setVisibility(View.VISIBLE);
                txt_d_19.setVisibility(View.VISIBLE);
            }
        }, 6420);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_18.setVisibility(View.VISIBLE);
                txt_h_3.setVisibility(View.VISIBLE);
            }
        }, 6440);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_17.setVisibility(View.VISIBLE);
                txt_h_4.setVisibility(View.VISIBLE);
            }
        }, 6460);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_16.setVisibility(View.VISIBLE);
                txt_h_5.setVisibility(View.VISIBLE);
            }
        }, 6480);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_15.setVisibility(View.VISIBLE);
                txt_h_6.setVisibility(View.VISIBLE);
            }
        }, 6500);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_14.setVisibility(View.VISIBLE);
                txt_h_7.setVisibility(View.VISIBLE);
            }
        }, 6520);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_13.setVisibility(View.VISIBLE);
                txt_h_8.setVisibility(View.VISIBLE);
            }
        }, 6540);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_12.setVisibility(View.VISIBLE);
                txt_h_9.setVisibility(View.VISIBLE);
            }
        }, 6560);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_11.setVisibility(View.VISIBLE);
                txt_h_10.setVisibility(View.VISIBLE);
            }
        }, 6580);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_10.setVisibility(View.VISIBLE);
                txt_h_11.setVisibility(View.VISIBLE);
            }
        }, 6600);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_9.setVisibility(View.VISIBLE);
                txt_h_12.setVisibility(View.VISIBLE);
            }
        }, 6620);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_8.setVisibility(View.VISIBLE);
                txt_h_13.setVisibility(View.VISIBLE);
            }
        }, 6640);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_7.setVisibility(View.VISIBLE);
                txt_h_14.setVisibility(View.VISIBLE);
            }
        }, 6660);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_6.setVisibility(View.VISIBLE);
                txt_h_15.setVisibility(View.VISIBLE);
            }
        }, 6680);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_5.setVisibility(View.VISIBLE);
                txt_h_16.setVisibility(View.VISIBLE);
            }
        }, 6700);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_4.setVisibility(View.VISIBLE);
                txt_h_17.setVisibility(View.VISIBLE);
            }
        }, 6720);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_c_3.setVisibility(View.VISIBLE);
                txt_h_18.setVisibility(View.VISIBLE);
            }
        }, 6740);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_3.setVisibility(View.VISIBLE);
                txt_g_18.setVisibility(View.VISIBLE);
            }
        }, 6760);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_3.setVisibility(View.VISIBLE);
                txt_f_18.setVisibility(View.VISIBLE);
            }
        }, 6780);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_f_3.setVisibility(View.VISIBLE);
                txt_e_18.setVisibility(View.VISIBLE);
            }
        }, 6800);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_g_3.setVisibility(View.VISIBLE);
                txt_d_18.setVisibility(View.VISIBLE);
            }
        }, 6820);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_17.setVisibility(View.VISIBLE);
                txt_g_4.setVisibility(View.VISIBLE);
            }
        }, 6840);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_16.setVisibility(View.VISIBLE);
                txt_g_5.setVisibility(View.VISIBLE);
            }
        }, 6860);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_15.setVisibility(View.VISIBLE);
                txt_g_6.setVisibility(View.VISIBLE);
            }
        }, 6880);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_14.setVisibility(View.VISIBLE);
                txt_g_7.setVisibility(View.VISIBLE);
            }
        }, 6900);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_13.setVisibility(View.VISIBLE);
                txt_g_8.setVisibility(View.VISIBLE);
            }
        }, 6920);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_12.setVisibility(View.VISIBLE);
                txt_g_9.setVisibility(View.VISIBLE);
            }
        }, 6940);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_11.setVisibility(View.VISIBLE);
                txt_g_10.setVisibility(View.VISIBLE);
            }
        }, 6960);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_10.setVisibility(View.VISIBLE);
                txt_g_11.setVisibility(View.VISIBLE);
            }
        }, 6980);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_9.setVisibility(View.VISIBLE);
                txt_g_12.setVisibility(View.VISIBLE);
            }
        }, 7000);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_8.setVisibility(View.VISIBLE);
                txt_g_13.setVisibility(View.VISIBLE);
            }
        }, 7020);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_7.setVisibility(View.VISIBLE);
                txt_g_14.setVisibility(View.VISIBLE);
            }
        }, 7040);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_6.setVisibility(View.VISIBLE);
                txt_g_15.setVisibility(View.VISIBLE);
            }
        }, 7060);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_5.setVisibility(View.VISIBLE);
                txt_g_16.setVisibility(View.VISIBLE);
            }
        }, 7080);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_d_4.setVisibility(View.VISIBLE);
                txt_g_17.setVisibility(View.VISIBLE);
            }
        }, 7100);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_4.setVisibility(View.VISIBLE);
                txt_f_17.setVisibility(View.VISIBLE);
            }
        }, 7120);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_f_4.setVisibility(View.VISIBLE);
                txt_e_17.setVisibility(View.VISIBLE);
            }
        }, 7140);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_16.setVisibility(View.VISIBLE);
                txt_f_5.setVisibility(View.VISIBLE);
            }
        }, 7160);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_15.setVisibility(View.VISIBLE);
                txt_f_6.setVisibility(View.VISIBLE);
            }
        }, 7180);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_14.setVisibility(View.VISIBLE);
                txt_f_7.setVisibility(View.VISIBLE);
            }
        }, 7200);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_13.setVisibility(View.VISIBLE);
                txt_f_8.setVisibility(View.VISIBLE);
            }
        }, 7220);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_12.setVisibility(View.VISIBLE);
                txt_f_9.setVisibility(View.VISIBLE);
            }
        }, 7240);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_11.setVisibility(View.VISIBLE);
                txt_f_10.setVisibility(View.VISIBLE);
            }
        }, 7260);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_10.setVisibility(View.VISIBLE);
                txt_f_11.setVisibility(View.VISIBLE);
            }
        }, 7280);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_9.setVisibility(View.VISIBLE);
                txt_f_12.setVisibility(View.VISIBLE);
            }
        }, 7300);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_8.setVisibility(View.VISIBLE);
                txt_f_13.setVisibility(View.VISIBLE);
            }
        }, 7320);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_7.setVisibility(View.VISIBLE);
                txt_f_14.setVisibility(View.VISIBLE);
            }
        }, 7340);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_6.setVisibility(View.VISIBLE);
                txt_f_15.setVisibility(View.VISIBLE);
            }
        }, 7360);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                txt_e_5.setVisibility(View.VISIBLE);
                txt_f_16.setVisibility(View.VISIBLE);
            }
        }, 7380);

        // Splash screen time-limit
        handlerSpecial = new Handler();
        runnableSpecial = new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StufflexSplashActivity.this, MainActivity.class);
                StufflexSplashActivity.this.startActivity(mainIntent);
                StufflexSplashActivity.this.finish();
            }
        };
        handlerSpecial.postDelayed(runnableSpecial, SPLASH_DISPLAY_LENGTH);
    }

    // Go to MainActivity
    public void GoToMainActivity() {
        Intent mainIntent = new Intent(StufflexSplashActivity.this, MainActivity.class);
        StufflexSplashActivity.this.startActivity(mainIntent);
        StufflexSplashActivity.this.finish();
    }

}