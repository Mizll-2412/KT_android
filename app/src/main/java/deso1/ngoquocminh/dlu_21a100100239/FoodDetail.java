package deso1.ngoquocminh.dlu_21a100100239;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.core.view.WindowInsetsCompat;

public class FoodDetail extends AppCompatActivity {
    EditText Name, Price;
    ImageView foodImage;
    Button btn_edt;
    String key = "";
    String imgURL = "";
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    Name = findViewById(R.id.edtFoodName);
    Price = findViewById(R.id.edtFoodPrice);
    foodImage = findViewById(R.id.foodImageView);
    btn_edt = findViewById(R.id.btnAddFood);
    Bundle bundle = getIntent().getExtras();
    if(bundle !=null)
    {
        key = bundle.getString("Key");
        imgURL = bundle.getString("Image");
        Name.setText(bundle.getString("Name"));
        Price.setText(bundle.getString("Price"));
        Glide.with(this).load(bundle.getString("Image")).into(foodImage);
    }
    btn_edt.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        String name = Name.getText().toString();
        String price = Price.getText().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference("Foods").child(key);
        Food foodData = new Food(key, imgURL, Name.getText().toString(), Price.getText().toString());
        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(FoodDetail.this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    });
}
}