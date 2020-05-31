package com.example.appmedia;


import android.content.Context;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArtBoard extends Fragment {
    private Context context;
    private GridView gridArt;
    private ArrayList<MusicInfor> musicInforList;
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
        return inflater.inflate(R.layout.artboard,container,false);

    }
    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitUI(view);
        GridListSongAdapter gridListSongAdapter = new GridListSongAdapter(context, R.layout.gridlayoutlistsong, musicInforList);
        gridArt.setAdapter(gridListSongAdapter);
    }
    private void InitUI(View view){
        gridArt = view.findViewById(R.id.gridArt);
        context = getContext();
        musicInforList = new ArrayList<>();
        musicInforList.add(new MusicInfor(R.drawable.zedd,"CandyMan","Zed & Aloe Blacc"));
        musicInforList.add(new MusicInfor(R.drawable.artwork,"Stay","Zed & Alessia Cara"));
        musicInforList.add(new MusicInfor(R.drawable.artwork1,"Get Low","Zed & Liam Payne"));
        musicInforList.add(new MusicInfor(R.drawable.artwork2,"Break Fee","Ariana Grande"));
        musicInforList.add(new MusicInfor(R.drawable.artwork3,"Beautiful Now","Zed"));
        musicInforList.add(new MusicInfor(R.drawable.artwork4,"Adrenaline","Zed & Grey"));
    }
}