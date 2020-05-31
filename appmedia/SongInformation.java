package com.example.appmedia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jgabrielfreitas.core.BlurImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SongInformation extends AppCompatActivity {
    Button btnLike, btnOther, btnNavi, btnPhone, btnSetting, btnSpotify, btnClose, btnOthers, btnSync;
    ImageView timeBar, plate, platehide;
    String transitionMode;
    TextView phoneText, naviText, textSetting, textSpotify, textSync, txtNameSong, txtNameSinger, alexaText;
    CustomFadeAnimation EnterSharedElementFade = new CustomFadeAnimation();
    ConstraintLayout plate1, plate2, plate3, plate4, controlBar;
    boolean enterAppear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_information);
        getIdFromLayout();
        enterAppear = true;

        Transition sharedElementEnterTransition = getWindow().getSharedElementEnterTransition();
        Intent intent = getIntent();
        transitionMode = intent.getStringExtra("TransitionMode");
        long durationEnterTransition = sharedElementEnterTransition.getDuration();
        addFade(durationEnterTransition);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
        sharedElementEnterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                EnterSharedElementFade.StartAnimationFadeIn(transitionMode);
                transitionMode = "IN";
                btnSync.setAlpha(1);
                btnClose.setAlpha(0);
            }

            @Override

            public void onTransitionEnd(Transition transition) {
                if (enterAppear) {
                    //Appear Views before Around Animation Start
                    appearAnimatiedView();
                    //Start Run Around Animation
                    animatePlate(plate1, 0);
                    animatePlate(plate2, 6);
                    animatePlate(plate3, 2);
                    animatePlate(plate4, 4);
                    enterAppear = false;
                }
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    private void addFade(long durationEnterTransition) {
        //textsize  animation
       /* HandlerCallback handler = new HandlerCallback(this);
        handler.addTextViewSizeResource((TextView) findViewById(R.id.txtNameSong),
                R.dimen._20ssp, R.dimen._16ssp);
        handler.addTextViewSizeResource((TextView) findViewById(R.id.txtSinger),
                R.dimen._15ssp, R.dimen._12ssp);*/
        //add view needing fade
        EnterSharedElementFade.AddTarget(btnLike, durationEnterTransition - 400);
        EnterSharedElementFade.AddTarget(btnOther, durationEnterTransition - 400);
        EnterSharedElementFade.AddTarget(timeBar, durationEnterTransition - 400);
        EnterSharedElementFade.AddTarget(alexaText, durationEnterTransition - 700);
        EnterSharedElementFade.AddTarget(btnPhone, 300);
        EnterSharedElementFade.AddTarget(btnNavi, 300);
        EnterSharedElementFade.AddTarget(phoneText, 300);
        EnterSharedElementFade.AddTarget(naviText, 300);
        EnterSharedElementFade.AddTarget(btnSetting, 300);
        EnterSharedElementFade.AddTarget(btnSpotify, 300);
        EnterSharedElementFade.AddTarget(textSetting, 300);
        EnterSharedElementFade.AddTarget(textSpotify, 300);
        EnterSharedElementFade.AddTarget(textSync, 300);
        EnterSharedElementFade.AddTarget(plate, durationEnterTransition - 400);
        EnterSharedElementFade.AddTarget(platehide, durationEnterTransition);
    }

    private void getIdFromLayout() {
        btnLike = findViewById(R.id.btnLike);
        btnOther = findViewById(R.id.otherButton);
        timeBar = findViewById(R.id.timeBar);
        alexaText = findViewById(R.id.alexaName);
        btnNavi = findViewById(R.id.navi);
        btnPhone = findViewById(R.id.phone);
        phoneText = findViewById(R.id.phone1);
        naviText = findViewById(R.id.naviText);
        btnSetting = findViewById(R.id.setting);
        btnSpotify = findViewById(R.id.spotify);
        textSetting = findViewById(R.id.settingText);
        textSpotify = findViewById(R.id.spotifyText);
        textSync = findViewById(R.id.syncText);
        plate = findViewById(R.id.platesub);
        platehide = findViewById(R.id.platehide);
        txtNameSong = findViewById(R.id.txtNameSong);
        txtNameSinger = findViewById(R.id.txtSinger);
        plate1 = findViewById(R.id.plate1);
        plate2 = findViewById(R.id.plate2);
        plate3 = findViewById(R.id.plate3);
        plate4 = findViewById(R.id.plate4);
        controlBar = findViewById(R.id.controlLayout);
        btnClose = findViewById(R.id.btnClose);
        btnOthers = findViewById(R.id.btnOthers);
        btnSync = findViewById(R.id.sync);

    }

    private AnimatorSet animateView(View view, double moveToPercentWidthScreen, double moveToPercentHeightScreen,
                                    float fromScaleX, float toScaleX, float fromScaleY, float toScaleY) {
        float runX;
        float runY;
        //get Width x Height Screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScreen = size.x;
        int heightScreen = size.y;
        //get Position of View form Left and Top Parent
        float x = view.getX();
        float y = view.getY();
        //get Width x Height of View
        float widthView = view.getWidth();
        float heightView = view.getHeight();

        //Caculate translate to move
        if (moveToPercentWidthScreen < 0.5) {
            //when the view in half left of screen.
            moveToPercentWidthScreen = moveToPercentWidthScreen - widthView / widthScreen;
        }
        if (moveToPercentHeightScreen < 0.5) {
            //when the view in half top of screen.
            moveToPercentHeightScreen = moveToPercentHeightScreen - heightView / heightScreen;
        }

        runX = (float) (widthScreen * moveToPercentWidthScreen) - x;
        runY = (float) (heightScreen * moveToPercentHeightScreen) - y;
        ObjectAnimator animTranslateX = ObjectAnimator.ofFloat(view, "translationX", runX);
        ObjectAnimator animTranslateY = ObjectAnimator.ofFloat(view, "translationY", runY);
        ObjectAnimator animScaleX = ObjectAnimator.ofFloat(view, "scaleX", fromScaleX, toScaleX);
        ObjectAnimator animScaleY = ObjectAnimator.ofFloat(view, "scaleY", fromScaleY, toScaleY);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animTranslateX, animTranslateY, animScaleX, animScaleY);
        return set;
    }

    public void animatePlate(View view, int startFrom) {
        ArrayList<AnimatorSet> listAnimation = new ArrayList<>();
        //Circle Round Setting
        AnimatorSet ani1 = animateView(view, 0.7, 0.4, 1f, 1.2f, 1f, 1.2f);
        AnimatorSet ani2 = animateView(view, 0.7, 0.6, 1.2f, 1.2f, 1.2f, 1.2f);
        AnimatorSet ani3 = animateView(view, 0.6, 0.8, 1.2f, 1f, 1.2f, 1f);
        AnimatorSet ani4 = animateView(view, 0.4, 0.8, 1f, 1f, 1f, 1f);
        AnimatorSet ani5 = animateView(view, 0.3, 0.6, 1f, 1.2f, 1f, 1.2f);
        AnimatorSet ani6 = animateView(view, 0.3, 0.4, 1.2f, 1.2f, 1.2f, 1.2f);
        AnimatorSet ani7 = animateView(view, 0.4, 0.2, 1.2f, 1f, 1.2f, 1f);
        AnimatorSet ani8 = animateView(view, 0.6, 0.2, 1f, 1f, 1f, 1f);

        listAnimation.add(ani1);
        listAnimation.add(ani2);
        listAnimation.add(ani3);
        listAnimation.add(ani4);
        listAnimation.add(ani5);
        listAnimation.add(ani6);
        listAnimation.add(ani7);
        listAnimation.add(ani8);

        //Set start position of the views form StartFrom var
        final AnimatorSet out = new AnimatorSet();
        listAnimation.get(startFrom).setDuration(5000);
        int index = startFrom;
        while ((index + 1) < listAnimation.size()) {
            listAnimation.get(index + 1).setDuration(5000);
            out.play(listAnimation.get(index)).before(listAnimation.get(index + 1));
            index = index + 1;
        }
        for (int i = 0; i < startFrom; i++) {
            listAnimation.get(i).setDuration(5000);
            int beforeI = i - 1;
            if (beforeI < 0) {
                beforeI = index;
            }
            out.play(listAnimation.get(beforeI)).before(listAnimation.get(i));
        }

        out.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                out.start();
                out.setStartDelay(0);
            }
        });
        //delay 1s to wait appear of views
        out.setStartDelay(1000);
        out.start();
    }

    private void appearAnimatiedView() {
        //Appear Views before Circle Animation Start
        AnimatorSet ani8 = animateView(plate1, 0.6, 0.2, 1f, 1f, 1f, 1f);
        AnimatorSet ani6 = animateView(plate2, 0.3, 0.4, 1.2f, 1.2f, 1.2f, 1.2f);
        AnimatorSet ani2 = animateView(plate3, 0.7, 0.6, 1.2f, 1.2f, 1.2f, 1.2f);
        AnimatorSet ani4 = animateView(plate4, 0.4, 0.8, 1f, 1f, 1f, 1f);
        ObjectAnimator animAlpha1 = ObjectAnimator.ofFloat(plate1, "alpha", 0f, 1f);
        ObjectAnimator animAlpha2 = ObjectAnimator.ofFloat(plate2, "alpha", 0f, 1f);
        ObjectAnimator animAlpha3 = ObjectAnimator.ofFloat(plate3, "alpha", 0f, 1f);
        ObjectAnimator animAlpha4 = ObjectAnimator.ofFloat(plate4, "alpha", 0f, 1f);
        ObjectAnimator animAlpha5 = ObjectAnimator.ofFloat(btnClose, "alpha", 0f, 1f);
        ObjectAnimator animAlpha6 = ObjectAnimator.ofFloat(btnSync, "alpha", 1f, 0f);
        ObjectAnimator animAlpha7 = ObjectAnimator.ofFloat(btnOthers, "alpha", 0f, 1f);


        ObjectAnimator rotationClose = ObjectAnimator.ofFloat(btnClose, "rotation", -45, 0);
        ObjectAnimator rotationOthers = ObjectAnimator.ofFloat(btnOthers, "rotation", 0, 180);
        AnimatorSet out = new AnimatorSet();
        out.playTogether(ani8, ani6, ani4, ani2, animAlpha1, animAlpha2, animAlpha3, animAlpha4, animAlpha5, animAlpha6, animAlpha7, rotationClose, rotationOthers);
        out.setDuration(1000);
        out.start();
    }
}
