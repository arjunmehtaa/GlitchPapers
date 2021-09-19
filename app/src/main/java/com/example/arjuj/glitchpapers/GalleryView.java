package com.example.arjuj.glitchpapers;

import android.app.ActionBar;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class GalleryView extends AppCompatActivity {

    int tag;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
    }

    public void chosenWallpaper(View view) {
        ImageView imageView = (ImageView) view;
        Intent intent = new Intent(GalleryView.this, ViewImage.class);
        int number = Integer.parseInt(imageView.getTag().toString());
        intent.putExtra("imageTag", number);
        intent.putExtra("Category", tag);
        Log.i("yolo", String.valueOf(number));
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_18dp);

        Intent intent = getIntent();
        tag = intent.getIntExtra("Tag", 2);
        if (tag == 1) {
            getSupportActionBar().setCustomView(R.layout.txt_layout1);

            for (int i = 1; i < 21; i++) {
                String imageId = "image" + i;
                ImageView targetImage = (ImageView) findViewById(getResources().getIdentifier(imageId, "id", getPackageName()));
                String url = "https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Symmetry%20Posters%2F" + i + ".png?alt=media&token=38d4f7b9-c01b-4b0c-b466-b4b1eda8520c";
                Picasso.with(this)
                        .load(url)
                        .fit()
                        .placeholder(R.drawable.blue)
                        .into(targetImage);
            }

        } else if (tag == 2) {
            getSupportActionBar().setCustomView(R.layout.txt_layout2);
            for (int i = 1; i < 21; i++) {
                String imageId = "image" + i;
                ImageView targetImage = (ImageView) findViewById(getResources().getIdentifier(imageId, "id", getPackageName()));
                String url = "https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Bubble%20Posters%2F" + i + ".png?alt=media&token=38d4f7b9-c01b-4b0c-b466-b4b1eda8520c";
                Picasso.with(this)
                        .load(url)
                        .fit()
                        .placeholder(R.drawable.blue)
                        .into(targetImage);
            }

        } else if (tag == 3) {
            getSupportActionBar().setCustomView(R.layout.txt_layout3);
            for (int i = 1; i < 21; i++) {
                String imageId = "image" + i;
                ImageView targetImage = (ImageView) findViewById(getResources().getIdentifier(imageId, "id", getPackageName()));
                String url = "https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Posters%2F" + i + ".png?alt=media&token=8bd34d5a-789e-42cc-ab2e-c7afcda6a62f";
                Picasso.with(this)
                        .load(url)
                        .fit()
                        .placeholder(R.drawable.blue)
                        .into(targetImage);
            }
        } else if (tag == 4) {
            getSupportActionBar().setCustomView(R.layout.txt_layout4);
        } else if (tag == 5) {
            getSupportActionBar().setCustomView(R.layout.txt_layout5);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right);
        }

        return super.onOptionsItemSelected(item);
    }

}
