package com.example.jjh10.samplecapture;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            //do nothing at the moment
        }


        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap bitmap = getScreenShot(rootView);
                store(bitmap, "ScreenShot.png");
            }
        });
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

    //get screen shot of the app
    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    //store the image on the device
    public void store(Bitmap bm, String fileName) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";

        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dirPath, fileName);
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();

        try {
            FileOutputStream fos = new FileOutputStream
                    (dirPath + "/Download" + day.format(date) + ".png");
            bm.compress(Bitmap.CompressFormat.PNG, 100 , fos);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + dirPath +"/Download" + day.format(date) + ".png")));
            fos.flush();
            fos.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}
