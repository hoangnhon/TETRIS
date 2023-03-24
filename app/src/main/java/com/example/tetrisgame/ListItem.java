package com.example.tetrisgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListItem {
    private Bitmap mThumbnail = null;
    private int score ;
    public ListItem(Bitmap thumbnail, int score){
        this.mThumbnail = thumbnail;
        this.score = score;
    }
    public Bitmap getmThumbnail(){
        return mThumbnail;
    }
    public int getScore(){
        return score;
    }
}
class ListAdapter extends ArrayAdapter<ListItem>{
    private int Resource ;
    private List<ListItem> Items ;
    private LayoutInflater Inflater ;
    //コンストラクタ
    public ListAdapter(Context context, int resource, List<ListItem> items){
        super(context,resource,items);
        Resource = resource;
        Items = items;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view ;
        if (convertView != null){
            view = convertView;
        }else{
            view = Inflater.inflate(Resource,null);
        }
        //リストビューに表示する要素を取得
        ListItem item = Items.get(position);
        //サムネイル画像を設定
        ImageView thumbnail = view.findViewById(R.id.thumbnail);
        thumbnail.setImageBitmap(item.getmThumbnail());
        //スコアを設定
        TextView scoreView = view.findViewById(R.id.scoreText);
        scoreView.setText(Integer.toString(item.getScore()));

        return view ;
    }
}
