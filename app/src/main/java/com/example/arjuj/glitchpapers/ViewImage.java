package com.example.arjuj.glitchpapers;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import pl.droidsonroids.gif.GifImageView;

public class ViewImage extends AppCompatActivity {

    static ImageView wallpaperimage;
    BitmapDrawable drawable;
    Bitmap bitmap;
    BottomDialog bottomDialog;
    String url;
    ImageView backButton;
    View decorView;
    WallpaperManager myWallpaperManager;
    static Boolean dismissed = false;
    Button setWallpaperButton;
    GifImageView gifLoading;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_right );
    }

    public void setWallpaper(View view){
        bottomDialog=new BottomDialog();
        bottomDialog.show(getSupportFragmentManager(),"Bottom Sheet");
    }

    public void setHomeScreen(View view){
        afterWallpaperOptionSelected();
        try {
            myWallpaperManager.setBitmap(bitmap,null,true,WallpaperManager.FLAG_SYSTEM);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        wallpaperSetToast();
    }

    public void setLockScreen(View view){
        afterWallpaperOptionSelected();
        try {
            myWallpaperManager.setBitmap(bitmap,null,true,WallpaperManager.FLAG_LOCK);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        wallpaperSetToast();
    }

    public void setBothScreen(View view){
        afterWallpaperOptionSelected();
        try {
            myWallpaperManager.setBitmap(bitmap);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        wallpaperSetToast();
    }

    public void saveToGallery(View view){
        bottomDialog.dismiss();
        wallpaperimage.invalidate();
        drawable = (BitmapDrawable) wallpaperimage.getDrawable();
        bitmap = drawable.getBitmap();
        if(isStoragePermissionGranted()){
            createDirectoryAndSaveFile(bitmap,"GlitchPapers-"+ UUID.randomUUID().toString()+".jpg");
        }
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {
        File direct = new File(Environment.getExternalStorageDirectory() + "Pictures/GlitchPapers");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Pictures/GlitchPapers/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File("/sdcard/Pictures/GlitchPapers/", fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(this, new String[] { file.getPath() }, new String[] { "image/jpeg" }, null);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                savedToGalleryToast();
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            createDirectoryAndSaveFile(bitmap,"GlitchPapers-"+ UUID.randomUUID().toString()+".jpg");
            savedToGalleryToast();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        decorView = getWindow().getDecorView();
        hideSystemUI();

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.activity_view_image);
        wallpaperimage=(ImageView)findViewById(R.id.wallpaperImage);
        backButton=(ImageView) findViewById(R.id.back_button);
        setWallpaperButton=(Button)findViewById(R.id.setWallpaperButton);
        gifLoading=(GifImageView)findViewById(R.id.gifLoading);

        Intent intent=getIntent();
        int tagImg=intent.getIntExtra("imageTag",2);
        int category=intent.getIntExtra("Category",2);
        if(category==1){
            url="https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Symmetry%2F"+tagImg+".png?alt=media&token=17c1b88e-79a6-4787-863d-5bb846ade5a9";
        }
        if(category==2){
            url="https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Bubble%2F"+tagImg+".png?alt=media&token=b2779009-5cc3-4130-9f8b-8fb46e3e76f0";
        }
        if(category==3){
            url="https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Wallpapers%2F"+tagImg+".jpg?alt=media&token=dff1304d-a80e-4625-9db6-4ef6e1480f67";
        }
        if(category==4){
            url="https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Wallpapers%2F"+tagImg+".jpg?alt=media&token=dff1304d-a80e-4625-9db6-4ef6e1480f67";
        }
        if(category==5){
            url="https://firebasestorage.googleapis.com/v0/b/glitchpapers.appspot.com/o/Wallpapers%2F"+tagImg+".jpg?alt=media&token=dff1304d-a80e-4625-9db6-4ef6e1480f67";
        }
        Picasso.with(this)
                .load(url)
                .fit().centerCrop()
                .placeholder(R.color.GreyYo)
                .into(wallpaperimage, new Callback.EmptyCallback() {
                    @Override public void onSuccess() {
                        setWallpaperButton.setAlpha(1);
                        setWallpaperButton.setEnabled(true);
                        gifLoading.setVisibility(View.INVISIBLE);
                    }
                });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        hideSystemUI();
    }

    private void hideSystemUI() {
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void afterWallpaperOptionSelected(){
        onResume();
        bottomDialog.dismiss();
        wallpaperimage.invalidate();
        drawable = (BitmapDrawable) wallpaperimage.getDrawable();
        bitmap = drawable.getBitmap();
        myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
    }

    private void wallpaperSetToast(){
        Toast.makeText(this, "Wallpaper Set.", Toast.LENGTH_SHORT).show();
    }

    private void savedToGalleryToast(){
        Toast.makeText(this, "Saved to Gallery.", Toast.LENGTH_SHORT).show();
    }
}

