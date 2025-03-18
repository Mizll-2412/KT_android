package deso1.ngoquocminh.dlu_21a100100239;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<Food> foods;
    public Adapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(foods.get(position).getImage()).into(holder.foodImage);
        holder.foodName.setText(foods.get(position).getName());
        holder.foodPrice.setText(foods.get(position).getPrice());

        holder.foodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetail.class);
                intent.putExtra("Image", foods.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name", foods.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Price", foods.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("Key", foods.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, foodPrice;
        ImageButton foodEdit;
        CardView foodCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.imageFood);
            foodName = itemView.findViewById(R.id.textFoodName);
            foodPrice = itemView.findViewById(R.id.textFoodPrice);
            foodCard = itemView.findViewById(R.id.card);
            foodEdit = itemView.findViewById(R.id.buttonEdit);

        }
    }
}
