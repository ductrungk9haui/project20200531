package com.example.appmedia;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class GridListSongAdapter extends BaseAdapter {
    private Context context;
    private int layout ;
    private List<MusicInfor> musicInforList;
    Animation animation;

    public GridListSongAdapter(Context context, int layout, List<MusicInfor> musicInforList) {

        this.context = context;
        this.layout = layout;
        this.musicInforList = musicInforList;
        animation = AnimationUtils.loadAnimation(context, R.anim.gridani);


    }


    @Override
    public int getCount() {
        return musicInforList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        LinearLayout myLayout;
        ImageView artwork;
        TextView nameSong;
        TextView nameSing;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.artwork = convertView.findViewById(R.id.artWork);
            viewHolder.nameSong = convertView.findViewById(R.id.textGridNameSong);
            viewHolder.nameSing = convertView.findViewById(R.id.textGridNameSing);
            viewHolder.myLayout = convertView.findViewById(R.id.myLayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MusicInfor musicInfor = musicInforList.get(position);
        viewHolder.artwork.setImageResource(musicInfor.getArtwork());
        viewHolder.nameSong.setText(musicInfor.getNameSong());
        viewHolder.nameSing.setText(musicInfor.getNameSinger());
        if(position != 0){
            viewHolder.myLayout.startAnimation(animation);
            viewHolder.myLayout.setBackground(null);
            viewHolder.myLayout.setTransitionName("");
            viewHolder.artwork.setTransitionName("");
            viewHolder.nameSong.setTransitionName("");
            viewHolder.nameSing.setTransitionName("");
        }else{

            viewHolder.myLayout.setBackgroundResource(R.drawable.ic_plate_main);
            viewHolder.myLayout.setTransitionName("plateMainTrans");
            viewHolder.artwork.setTransitionName("imgMainTrans");
            viewHolder.nameSong.setTransitionName("txtNameSongTrans");
            viewHolder.nameSing.setTransitionName("txtSingerTrans");
        }

        return convertView;
    }
}
