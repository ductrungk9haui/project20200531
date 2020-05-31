package com.example.appmedia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class playBoard extends Fragment {
    private Button btnLike, btnOther, btnNavi, btnPhone, btnSetting, btnSpotify, btnClose, btnOthers, btnSync;
    private ImageView timeBar, plate, platehide;
    private String transitionMode;
    private TextView phoneText, naviText, textSetting, textSpotify, textSync, txtNameSong, txtNameSinger, alexaText;
    private CustomFadeAnimation EnterSharedElementFade = new CustomFadeAnimation();
    private ConstraintLayout plate1, plate2, plate3, plate4, controlBar;
    private boolean enterAppear;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.sharedelement));
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.sharedelement));
        enterAppear = true;
        setEnterTransition(new Fade());
        setExitTransition(new Fade());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_song_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitUI(view);
        postponeEnterTransition();
        Transition sharedElementEnterTransition = (Transition) getSharedElementEnterTransition();
        long durationEnterTransition = sharedElementEnterTransition.getDuration();
        //add Element needing to fade when start fragment width Shared Element
        addFade(durationEnterTransition);
        EnterSharedElementFade.StartAnimationFadeIn(transitionMode);
        transitionMode = "IN";
        btnSync.setAlpha(1);
        btnClose.setAlpha(0);
        //when view drawed start appear Circle Animation
        ViewTreeObserver vto = plate.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                startPostponedEnterTransition();
                plate.getViewTreeObserver().removeOnPreDrawListener(this);
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
                return true;
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });
    }
    private void addFade(long durationEnterTransition) {
        //add view needing fade
        EnterSharedElementFade.AddTarget(btnLike, 400);
        EnterSharedElementFade.AddTarget(btnOther, 400);
        EnterSharedElementFade.AddTarget(timeBar, 400);
        EnterSharedElementFade.AddTarget(alexaText, 300);
        EnterSharedElementFade.AddTarget(btnPhone, 300);
        EnterSharedElementFade.AddTarget(btnNavi, 300);
        EnterSharedElementFade.AddTarget(phoneText, 300);
        EnterSharedElementFade.AddTarget(naviText, 300);
        EnterSharedElementFade.AddTarget(btnSetting, 300);
        EnterSharedElementFade.AddTarget(btnSpotify, 300);
        EnterSharedElementFade.AddTarget(textSetting, 300);
        EnterSharedElementFade.AddTarget(textSpotify, 300);
        EnterSharedElementFade.AddTarget(textSync, 300);
        EnterSharedElementFade.AddTarget(plate, 400);
        EnterSharedElementFade.AddTarget(platehide, durationEnterTransition);
    }

    private void InitUI(View view) {
        transitionMode = "OUT";
        btnLike = view.findViewById(R.id.btnLike);
        btnOther = view.findViewById(R.id.otherButton);
        timeBar = view.findViewById(R.id.timeBar);
        alexaText = view.findViewById(R.id.alexaName);
        btnNavi = view.findViewById(R.id.navi);
        btnPhone = view.findViewById(R.id.phone);
        phoneText = view.findViewById(R.id.phone1);
        naviText = view.findViewById(R.id.naviText);
        btnSetting = view.findViewById(R.id.setting);
        btnSpotify = view.findViewById(R.id.spotify);
        textSetting = view.findViewById(R.id.settingText);
        textSpotify = view.findViewById(R.id.spotifyText);
        textSync = view.findViewById(R.id.syncText);
        plate = view.findViewById(R.id.platesub);
        platehide = view.findViewById(R.id.platehide);
        txtNameSong = view.findViewById(R.id.txtNameSong);
        txtNameSinger = view.findViewById(R.id.txtSinger);
        plate1 = view.findViewById(R.id.plate1);
        plate2 = view.findViewById(R.id.plate2);
        plate3 = view.findViewById(R.id.plate3);
        plate4 = view.findViewById(R.id.plate4);
        controlBar = view.findViewById(R.id.controlLayout);
        btnClose = view.findViewById(R.id.btnClose);
        btnOthers = view.findViewById(R.id.btnOthers);
        btnSync = view.findViewById(R.id.sync);
    }
    private AnimatorSet animateView(final View view, double moveToPercentWidthScreen, double moveToPercentHeightScreen,
                                    float fromScaleX, float toScaleX, float fromScaleY, float toScaleY) {
        float runX;
        float runY;
        //get Width x Height Screen
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthScreen = size.x;
        int heightScreen = size.y;
        //get Position of View form Left and Top Parent
        float x = view.getX();
        float y = view.getY();
        //get Width x Height of View
        float widthView = view.getMeasuredWidth();
        float heightView = view.getMeasuredHeight();

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
            private boolean mCanceled;
            @Override
            public void onAnimationStart(Animator animation) {
                mCanceled = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCanceled = true;
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!mCanceled) {
                    out.start();
                    out.setStartDelay(0);
                }
            }
        });
        //delay 1s to wait appear of views
        out.setStartDelay(1500);
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
        out.setStartDelay(500);
        out.start();
    }
}
