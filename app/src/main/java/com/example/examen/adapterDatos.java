package com.example.examen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterDatos extends RecyclerView.Adapter<adapterDatos.ExampleViewHolder> {
    private Context  mContext;
    private ArrayList<itemsDatos> mExampleList;

    public adapterDatos(Context context, ArrayList<itemsDatos> exampleList) {
        mContext = context;
        mExampleList =exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.my_text_view, parent, false );
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        itemsDatos currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String firstName = currentItem.getFirstName();
        String lastName = currentItem.getLastName();

        holder.mTextViewFirstName.setText(firstName);
        holder.mTextViewLastName.setText(lastName);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mAvatarImage);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAvatarImage;
        public TextView mTextViewFirstName;
        public TextView mTextViewLastName;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatarImage = itemView.findViewById(R.id.avatar_view);
            mTextViewFirstName = itemView.findViewById(R.id.first_name_tv);
            mTextViewLastName = itemView.findViewById(R.id.last_name_tv);
        }
    }
}
