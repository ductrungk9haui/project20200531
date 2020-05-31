package com.example.appmedia;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomFadeAnimation {
    private ArrayList<ObjectAnimator> listFadeIn = new ArrayList<>();
    private ArrayList<ObjectAnimator> listFadeOut = new ArrayList<>();
    View viewMain;
    float width;
    float height;
    long duration;
    float widthOld;
    float heightOld;

    void AddTarget(View view, long duration) {
        ObjectAnimator fade;
        fade = ObjectAnimator.ofFloat(view,"alpha", 1f, 0f);
        fade.setDuration(duration);
        listFadeIn.add(fade);
        fade = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fade.setDuration(duration);
        listFadeOut.add(fade);
    }
    void AddTarget(View view, long duration, float widthOld, float heightOld, float widthNew, float heightNew) {


        this.viewMain = view;
        view.post(new Runnable() {
            @Override
            public void run() {
                width = viewMain.getWidth();
                height = viewMain.getHeight();
            }
        });
        float scaleX = widthOld/width;
        float scaleY = heightOld/height;
        ObjectAnimator fade;
        ObjectAnimator scaleWidth;
        ObjectAnimator scaleHeight;

        fade = ObjectAnimator.ofFloat(view,"alpha", 1f, 0f);
        scaleWidth = ObjectAnimator.ofFloat(view, "scaleX",scaleX,1f);
        scaleHeight = ObjectAnimator.ofFloat(view, "scaleY",scaleY,1f);
        fade.setDuration(duration);
        scaleWidth.setDuration(duration);
        scaleHeight.setDuration(duration);
        listFadeIn.add(fade);
        listFadeIn.add(scaleWidth);
        listFadeIn.add(scaleHeight);

        fade = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        scaleWidth = ObjectAnimator.ofFloat(view, "scaleX",1f,scaleX);
        scaleHeight = ObjectAnimator.ofFloat(view, "scaleY",1,scaleY);
        fade.setDuration(duration);
        scaleWidth.setDuration(duration);
        scaleHeight.setDuration(duration);
        listFadeOut.add(fade);
        listFadeOut.add(scaleWidth);
        listFadeOut.add(scaleHeight);
    }


    void StartAnimationFadeIn(String mode) {
        switch (mode){
            case "OUT":
                if (listFadeIn.size() == 1) {
                    listFadeIn.get(0).start();
                }else{
                    AnimatorSet set = new AnimatorSet();
                    for (int index = 1; index < listFadeIn.size(); index++) {
                        set.play(listFadeIn.get(0)).with(listFadeIn.get(index));
                    }
                    set.start();
                }
                break;
            case "IN":
                if (listFadeOut.size() == 1) {
                    listFadeOut.get(0).start();
                }else{
                    AnimatorSet set = new AnimatorSet();
                    for (int index = 1; index < listFadeOut.size(); index++) {
                        set.play(listFadeOut.get(0)).with(listFadeOut.get(index));
                    }
                    set.start();
                }
                break;
        }


    }
}
