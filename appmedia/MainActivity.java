package com.example.appmedia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentActionListener {
    /*  Button btnSync,btnBack,btnPlay,btnNext,btnLike,btnOther,btnAlexa, btnNavi, btnPhone,btnSetting,btnSpotify;
      ImageView timeBar,alexaText,platHide;
      TextView txtNameSong,txtNameSinger,phoneText,naviText,textSetting,textSpotify,textSync;
      ConstraintLayout plate;
      Intent sharedIntent;
      Intent sharedArtboard;*/
    FrameLayout fragmentContainer;
    ArtBoard artBoard = new ArtBoard();
    MainBoard mainBoard = new MainBoard();
    playBoard playBoard = new playBoard();
    FragmentManager fragmentManager = getSupportFragmentManager();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*InitUI();

        btnSync.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                sharedIntent = new Intent(MainActivity.this, SongInformation.class);
                callSongInformation();

            }
        });
        btnSpotify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                sharedArtboard = new Intent(MainActivity.this, ArtBoard.class);
                startActivity(sharedArtboard);
            }
        });*/
        callMainBoard();
        fragmentContainer = findViewById(R.id.fragmentContainer);
        fragmentContainer.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                if (fragmentManager.findFragmentById(R.id.fragmentContainer) instanceof MainBoard) {
                    Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                    mainBoard.callArtBoard(fragmentManager, artBoard);
                }
            }
        });
    }

    private void callMainBoard() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mainBoard.setFragmentActionListener(this);
        fragmentTransaction.replace(R.id.fragmentContainer, mainBoard)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentById(R.id.fragmentContainer) instanceof MainBoard) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void ActionPerformed() {
            if (fragmentManager.findFragmentById(R.id.fragmentContainer) instanceof MainBoard) {
                mainBoard.callPlayBoard(fragmentManager, playBoard);
            }
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
   /* private void InitUI(){
        profileimg = findViewById(R.id.imgMain);
        btnSync = findViewById(R.id.sync);
        txtNameSong = findViewById(R.id.txtNameSong);
        txtNameSinger = findViewById(R.id.txtSinger);
        plate = findViewById(R.id.plate);
        btnBack = findViewById(R.id.btnBack);
        btnPlay = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnLike = findViewById(R.id.btnLike);
        timeBar = findViewById(R.id.timeBar);
        btnOther = findViewById(R.id.otherButton);
        btnAlexa = findViewById(R.id.alexa);
        alexaText = findViewById(R.id.alexaName);
        btnNavi = findViewById(R.id.navi);
        btnPhone = findViewById(R.id.phone);
        phoneText = findViewById(R.id.phone1);
        naviText = findViewById(R.id.naviText);
        btnSetting = findViewById(R.id.setting);
        btnSpotify = findViewById(R.id.spotify);
        textSetting = findViewById(R.id.settingText);
        textSpotify = findViewById(R.id.spotifyText );
        textSync = findViewById(R.id.syncText);
        platHide = findViewById(R.id.platehide);

    }
*/
}



