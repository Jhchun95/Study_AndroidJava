package com.example.jjh10.recyclerviewddd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.jjh10.recyclerviewddd.MainActivity.EXTRA_DATE;
import static com.example.jjh10.recyclerviewddd.MainActivity.EXTRA_URL;

/**
 * Created by jjh10 on 2019-04-07.
 */

public class GalleryPreview extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_preview);

        Intent intent = getIntent();
        String img_url = intent.getStringExtra(EXTRA_URL);
        String date = intent.getStringExtra(EXTRA_DATE);

        ImageView imageViewpreview = findViewById(R.id.GalleryPreviewImg);
        TextView textViewpreview = findViewById(R.id.GalleryPreviewTest);
        /*
        Glide.with(this).load(img_url).into(imageViewpreview);
        */
        Glide.with(this).load(img_url).signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).into(imageViewpreview);
        textViewpreview.setText(date);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            //do nothing at the moment
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000) {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                //do nothing at the moment
            } else {
                //if permission is not granted exit the app
                Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}