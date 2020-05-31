package com.example.appmedia;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainBoard extends Fragment {
    private ConstraintLayout mainLayout;
    private Button btnSync, btnBack, btnPlay, btnNext, btnLike, btnOther, btnAlexa, btnNavi, btnPhone, btnSetting, btnSpotify;
    private ImageView timeBar, platHide, profileimg;
    private TextView txtNameSong, txtNameSinger, phoneText, naviText, textSetting, alexaText,textSpotify, textSync;
    private ConstraintLayout plate;
    private  FragmentActionListener fragmentActionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.sharedelement));
        setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.sharedelement));
        setEnterTransition(new Fade());
        setExitTransition(new Fade());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitUI(view);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentActionListener.ActionPerformed();
            }
        });
    }
    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    public void callArtBoard(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                .addSharedElement(btnBack, btnBack.getTransitionName())
                .addSharedElement(btnPlay, btnPlay.getTransitionName())
                .addSharedElement(btnNext, btnNext.getTransitionName())
                .addSharedElement(timeBar, timeBar.getTransitionName())
                .addSharedElement(btnOther, btnOther.getTransitionName())
                .addSharedElement(plate, plate.getTransitionName())
                .addSharedElement(btnLike, btnLike.getTransitionName())
                .addSharedElement(btnSync, btnSync.getTransitionName())
                .addSharedElement(btnAlexa, btnAlexa.getTransitionName())
                .addSharedElement(platHide,platHide.getTransitionName())
                .addSharedElement(profileimg,profileimg.getTransitionName())
                .addSharedElement(txtNameSong,txtNameSong.getTransitionName())
                .addSharedElement(txtNameSinger,txtNameSinger.getTransitionName())
                .addToBackStack(null)
                .commit();
    }
    public void callPlayBoard(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                .addSharedElement(plate,plate .getTransitionName())
                .addSharedElement(profileimg, profileimg.getTransitionName())
                .addSharedElement(btnSync, btnSync.getTransitionName())
                .addSharedElement(txtNameSong, txtNameSong.getTransitionName())
                .addSharedElement(txtNameSinger, txtNameSinger.getTransitionName())
                .addSharedElement(btnBack, btnBack.getTransitionName())
                .addSharedElement(btnPlay,btnPlay .getTransitionName())
                .addSharedElement(btnNext, btnNext.getTransitionName())
                .addSharedElement(btnLike,btnLike .getTransitionName())
                .addSharedElement(timeBar,timeBar .getTransitionName())
                .addSharedElement(btnOther,btnOther .getTransitionName())
                .addSharedElement(btnAlexa,btnAlexa .getTransitionName())
                .addSharedElement(alexaText,alexaText .getTransitionName())
                .addSharedElement(btnPhone,btnPhone .getTransitionName())
                .addSharedElement(btnNavi,btnNavi .getTransitionName())
                .addSharedElement(naviText,naviText .getTransitionName())
                .addSharedElement(phoneText,phoneText .getTransitionName())
                .addSharedElement(btnSetting,btnSetting .getTransitionName())
                .addSharedElement(btnSpotify,btnSpotify .getTransitionName())
                .addSharedElement(textSetting,textSetting .getTransitionName())
                .addSharedElement(textSpotify,textSpotify .getTransitionName())
                .addSharedElement(textSync,textSync .getTransitionName())
                .addSharedElement(platHide,platHide .getTransitionName())
                .addToBackStack(null)
                .commit();
    }
     /* private  void callSongInformation(){
        //setting shared element views
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair[] pairs = new Pair[23];
            pairs[0] = new Pair<View, String>(profileimg, profileimg.getTransitionName());
            pairs[1] = new Pair<View, String>(btnSync, btnSync.getTransitionName());
            pairs[2] = new Pair<View, String>(txtNameSong, txtNameSong.getTransitionName());
            pairs[3] = new Pair<View, String>(txtNameSinger, txtNameSinger.getTransitionName());
            pairs[4] = new Pair<View, String>(btnBack, btnBack.getTransitionName());
            pairs[5] = new Pair<View, String>(btnPlay,btnPlay .getTransitionName());
            pairs[6] = new Pair<View, String>(btnNext, btnNext.getTransitionName());
            pairs[7] = new Pair<View, String>(btnLike,btnLike .getTransitionName());
            pairs[8] = new Pair<View, String>(timeBar,timeBar .getTransitionName());
            pairs[9] = new Pair<View, String>(btnOther,btnOther .getTransitionName());
            pairs[10] = new Pair<View, String>(btnAlexa,btnAlexa .getTransitionName());
            pairs[11] = new Pair<View, String>(alexaText,alexaText .getTransitionName());
            pairs[12] = new Pair<View, String>(btnPhone,btnPhone .getTransitionName());
            pairs[13] = new Pair<View, String>(btnNavi,btnNavi .getTransitionName());
            pairs[14] = new Pair<View, String>(naviText,naviText .getTransitionName());
            pairs[15] = new Pair<View, String>(phoneText,phoneText .getTransitionName());
            pairs[16] = new Pair<View, String>(btnSetting,btnSetting .getTransitionName());
            pairs[17] = new Pair<View, String>(btnSpotify,btnSpotify .getTransitionName());
            pairs[18] = new Pair<View, String>(textSetting,textSetting .getTransitionName());
            pairs[19] = new Pair<View, String>(textSpotify,textSpotify .getTransitionName());
            pairs[20] = new Pair<View, String>(textSync,textSync .getTransitionName());
            pairs[21] = new Pair<View, String>(plate,plate .getTransitionName());
            pairs[22] = new Pair<View, String>(platHide,platHide .getTransitionName());
            sharedIntent.putExtra("TransitionMode","OUT");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(sharedIntent, options.toBundle());
        } else {
            startActivity(sharedIntent);
        }
    }*/

    private void InitUI(View view) {

        profileimg = view.findViewById(R.id.imgMain);
        btnSync = view.findViewById(R.id.sync);
        txtNameSong = view.findViewById(R.id.txtNameSong);
        txtNameSinger = view.findViewById(R.id.txtSinger);
        plate = view.findViewById(R.id.plate);
        btnBack = view.findViewById(R.id.btnBacks);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnNext = view.findViewById(R.id.btnNext);
        btnLike = view.findViewById(R.id.btnLike);
        timeBar = view.findViewById(R.id.timeBar);
        btnOther = view.findViewById(R.id.otherButton);
        btnAlexa = view.findViewById(R.id.alexa);
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
        platHide = view.findViewById(R.id.platehide);
    }

}
