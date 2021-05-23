package com.mahad.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahad.task.R;
import com.mahad.task.model.Home_Model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.mHolder> {

    List<Home_Model> homeModelList;
    ClickListner clickListner;
    private final Context context;


    public Home_Adapter(Context context, List<Home_Model> homeModelList, ClickListner clickListner) {
        this.homeModelList = homeModelList;
        this.clickListner = clickListner;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public mHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_view, parent, false);
        return new mHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Home_Adapter.mHolder holder, int position) {

        Glide.with(holder.imageView.getContext())
                .load(homeModelList.get(position).getImageURL_m())
                .into(holder.imageView);
        //end glider

        holder.glassNameV.setText(homeModelList.get(position).getGlassName_m());
        holder.drinkDetailV.setText(homeModelList.get(position).getDrinkDetail_m());
        holder.checkBoxV.setChecked(homeModelList.get(position).isAlcoholic());

    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    public class mHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView glassNameV, drinkDetailV;
        CheckBox checkBoxV;
        ImageView favBtn;

        public mHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.drink_image_recycle);
            glassNameV = itemView.findViewById(R.id.glass_name);
            drinkDetailV = itemView.findViewById(R.id.drink_detail);
            checkBoxV = itemView.findViewById(R.id.checkbox_v);
            favBtn = itemView.findViewById(R.id.add_fav_btn);

            favBtn.setOnClickListener(v -> {
                clickListner.favBtnAdd(getAdapterPosition(), v);
                favBtn.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_star_24));
            });
        }
    }

    public interface ClickListner {
        void favBtnAdd(int position, View view);
    }
}
