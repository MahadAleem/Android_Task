package com.mahad.task.adapter;

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
import com.mahad.task.model.Fav_Model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Favourite_Adapter extends RecyclerView.Adapter<Favourite_Adapter.mHolder> {

    List<Fav_Model> favModelList;

    public Favourite_Adapter(List<Fav_Model> favModelList) {
        this.favModelList = favModelList;
    }

    @NonNull
    @NotNull
    @Override
    public mHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_recycle_view,parent,false);
        return new mHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Favourite_Adapter.mHolder holder, int position) {
        Glide.with(holder.imageView.getContext())
                .load(favModelList.get(position).getImageURL_fav())
                .into(holder.imageView);
        //end glider

        holder.glassNameV.setText(favModelList.get(position).getGlassName_fav());
        holder.drinkDetailV.setText(favModelList.get(position).getDrinkDetail_fav());
        holder.checkBoxV.setChecked(favModelList.get(position).isAlcoholic_fav());
    }

    @Override
    public int getItemCount() {
        return favModelList.size();
    }

    public class mHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView glassNameV, drinkDetailV;
        CheckBox checkBoxV;
        ImageView favBtn;

        public mHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.drink_image_recycle_fav);
            glassNameV = itemView.findViewById(R.id.glass_name_fav);
            drinkDetailV = itemView.findViewById(R.id.drink_detail_fav);
            checkBoxV = itemView.findViewById(R.id.checkbox_v_fav);
            favBtn = itemView.findViewById(R.id.add_fav_btn_f);
        }
    }

}
