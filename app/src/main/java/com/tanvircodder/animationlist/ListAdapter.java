package com.tanvircodder.animationlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvircodder.animationlist.utility.Util;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private Context mContext;
    private List<Util> mListData;
    public ListAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Util util = mListData.get(position);
        String title = util.getEnglishTitle();
        String type = util.getType();
        String description = util.getDescription();
//        nwo i am going to bind the view to to the view..//
        holder.mTitleView.setText(title);
        holder.mTypeView.setText(type);
    }

    @Override
    public int getItemCount() {
        if (mListData == null)return 0;
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitleView;
        private TextView mTypeView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleView = (TextView)itemView.findViewById(R.id.list_title);
            mTypeView = (TextView)  itemView.findViewById(R.id.list_type);
        }
    }
    public void swapTheData(List<Util> mData){
        this.mListData = mData;
        if (mData != null){
            notifyDataSetChanged();
        }
    }
}
