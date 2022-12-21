
package com.example.jjh10.internetpngupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnDownload).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        WriteFileFromHTTP();
    }

    public void Log(String msg)
    {
        EditText etResult = (EditText)findViewById(R.id.etResult);
        etResult.append(msg + "\n");
    }

    private void WriteFileFromHTTP()
    {
        URL url = null;
        try {
            url = new URL("https://developer.android.com/images/home/sdk-large.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        int bufSize = 1024 * 1024;
        byte buf[] = new byte[bufSize];	// 1MB
        int nRead = 0;
        int offset = 0;

        String sdcard = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        if( null != url )
        {
            try {
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                BufferedInputStream in = new BufferedInputStream(urlConn.getInputStream());
                while( true )
                {
                    nRead = in.read(buf, 0, bufSize - offset);
                    if( nRead <= 0 ) break;
                    offset += nRead;
                }
                in.close();
                urlConn.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try
            {
                File sdDir = new File(sdcard);
                if( sdDir.exists() && sdDir.canWrite())
                {
                    File f = new File(sdDir.getAbsolutePath() + "/droid.png");
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedOutputStream out = new BufferedOutputStream(fos);
                    out.write(buf, 0, offset);
                    out.close();
                    fos.close();
                    Log("Writing " + offset + " bytes finished");
                }
            }
            catch (Exception e)
            {
                Log("Could not write file " + e.getMessage());
            }
        }
    }


}