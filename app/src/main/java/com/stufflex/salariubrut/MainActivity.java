package com.stufflex.salariubrut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Declarations
    private TextView txt_title;
    private EditText editTextNumberDecimal;
    private Button btn_euro;
    private Button btn_dollar;
    private Button btn_play;
    private Button btn_lei;
    private TextView txt_arrow;
    private TextView txt_copyright;

    private Placeholder placeholder;

    private ConstraintLayout mainLayout;
    private int n = 12;

    private Animation anim_txt_title;
    private Animation anim_editTextNumberDecimal;
    private Animation anim_btn_euro;
    private Animation anim_btn_dollar;
    private Animation anim_btn_play;
    private Animation anim_btn_lei;
    private Animation anim_txt_copyright;
    private Animation anim_txt_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navbar-fullscreen
        hideNavigationBar();

        // Initializations
        txt_title = findViewById(R.id.txt_title);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        btn_euro = findViewById(R.id.btn_euro);
        btn_dollar = findViewById(R.id.btn_dollar);
        btn_play = findViewById(R.id.btn_play);
        btn_lei = findViewById(R.id.btn_lei);
        txt_arrow = findViewById(R.id.txt_arrow);
        txt_copyright = findViewById(R.id.txt_copyright);
        mainLayout = findViewById(R.id.mainLayout);
        placeholder = findViewById(R.id.placeholder);

        // Alert user when EditText limit exxceeded
        editTextNumberDecimal.addTextChangedListener(new TextWatcher() {
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
                    editTextNumberDecimal.setText("");

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
        anim_txt_title = AnimationUtils.loadAnimation(this, R.anim.blinking);
        // anim_editTextNumberDecimal = AnimationUtils.loadAnimation(this, R.anim.big_small);
        // anim_btn_euro = AnimationUtils.loadAnimation(this, R.anim.big_small);
        anim_btn_dollar = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        anim_btn_play = AnimationUtils.loadAnimation(this, R.anim.fancy);
        anim_btn_lei = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        anim_txt_copyright = AnimationUtils.loadAnimation(this, R.anim.bottom_to_up);
        anim_txt_arrow = AnimationUtils.loadAnimation(this, R.anim.fancy);

        txt_title.setAnimation(anim_txt_title);
        btn_lei.setAnimation(anim_btn_lei);
        btn_dollar.setAnimation(anim_btn_dollar);
        txt_copyright.setAnimation(anim_txt_copyright);
        btn_play.setAnimation(anim_btn_play);
        txt_arrow.setAnimation(anim_txt_arrow);


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

    public void SwapLei(View v){
        TransitionManager.beginDelayedTransition(mainLayout);
        placeholder.setContentId(v.getId());
        btn_lei.setTextColor(Color.parseColor("#00FF00"));
        btn_euro.setTextColor(Color.parseColor("#FFFFFF"));
        btn_dollar.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void SwapEuro(View v){
        TransitionManager.beginDelayedTransition(mainLayout);
        placeholder.setContentId(v.getId());
        btn_lei.setTextColor(Color.parseColor("#FFFFFF"));
        btn_euro.setTextColor(Color.parseColor("#00FF00"));
        btn_dollar.setTextColor(Color.parseColor("#FFFFFF"));
    }
    public void SwapDollar(View v){
        TransitionManager.beginDelayedTransition(mainLayout);
        placeholder.setContentId(v.getId());
        btn_lei.setTextColor(Color.parseColor("#FFFFFF"));
        btn_euro.setTextColor(Color.parseColor("#FFFFFF"));
        btn_dollar.setTextColor(Color.parseColor("#00FF00"));
    }

    // Reset the app
    public void Reset(View v){
        Intent appReset = getIntent();
        finish();
        startActivity(appReset);
    }
}