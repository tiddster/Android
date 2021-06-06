package com.example.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Word> allWORDS = new ArrayList<>();

    public void setAllWORDS(List<Word> allWORDS) {
        this.allWORDS = allWORDS;
    }

    //
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_normal,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWORDS.get(position);
        holder.mTextViewId.setText(String.valueOf(word.getId()));
        holder.mTextViewE.setText(word.getWord());
        holder.mTextViewC.setText(word.getCM());
    }

    @Override
    public int getItemCount() {
        return allWORDS.size();
    }


    //
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewId,mTextViewE,mTextViewC;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.textViewId);
            mTextViewC = itemView.findViewById(R.id.textViewC);
            mTextViewE = itemView.findViewById(R.id.textViewE);
        }
    }
}
