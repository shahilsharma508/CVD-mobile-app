package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class about_page extends AppCompatActivity {
     ImageSlider imageSlider, imageSlider1, imageSlider2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        imageSlider = findViewById(R.id.image_slider);
        imageSlider1 = findViewById(R.id.image_slider2);
        imageSlider2 = findViewById(R.id.image_slider3);
        button = (Button) findViewById(R.id.btn_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(about_page.this,MainActivity.class);
                startActivity(intent);

            }
        });
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.person,null));
        images.add(new SlideModel(R.drawable.person1,null));
        images.add(new SlideModel(R.drawable.persorn2,null));
        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);

        ArrayList<SlideModel> images1 = new ArrayList<>();
        images1.add(new SlideModel(R.drawable.ncd1,null));
        images1.add(new SlideModel(R.drawable.ncd2,null));
        images1.add(new SlideModel(R.drawable.ncd3,null));
        images1.add(new SlideModel(R.drawable.ncd4,null));
        imageSlider1.setImageList(images1, ScaleTypes.CENTER_CROP);

        ArrayList<SlideModel> images2 = new ArrayList<>();
        images2.add(new SlideModel(R.drawable.n1,null));
        images2.add(new SlideModel(R.drawable.n2,null));
        images2.add(new SlideModel(R.drawable.n3,null));
        images2.add(new SlideModel(R.drawable.n4,null));
        imageSlider2.setImageList(images2, ScaleTypes.CENTER_CROP);



    }



}