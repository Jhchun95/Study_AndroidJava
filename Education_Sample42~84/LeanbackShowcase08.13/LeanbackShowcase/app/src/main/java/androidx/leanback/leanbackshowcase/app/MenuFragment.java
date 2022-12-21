package androidx.leanback.leanbackshowcase.app;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.leanback.leanbackshowcase.R;
import androidx.annotation.Nullable;
import androidx.leanback.leanbackshowcase.app.grid.GridExampleActivity;
import androidx.leanback.leanbackshowcase.app.grid.SlideShow;
import androidx.leanback.leanbackshowcase.app.grid.VideoGridExampleActivity;
import androidx.leanback.leanbackshowcase.models.CardRow;
import androidx.leanback.leanbackshowcase.models.VideoRow;
import androidx.leanback.leanbackshowcase.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MenuFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "VideoFragment";
    private static final String TAG_CATEGORY = "categories";

    ImageView imageView[] = new ImageView[6];
    ImageView imageView1[] = new ImageView[6];
    ImageView imageView2[] = new ImageView[11];

    ImageView img1, img2, img3;
    View line1, line2, line3;
    TextView text1, text2, text3;
    LinearLayout c1, c2, c3;

    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_frag, container, false);

        // LinearLayout
        c1 = rootView.findViewById(R.id.Category1);
        c2 = rootView.findViewById(R.id.Category2);
        c3 = rootView.findViewById(R.id.Category3);

        // ImageView
        img1 = rootView.findViewById(R.id.img1);
        img2 = rootView.findViewById(R.id.img2);
        img3 = rootView.findViewById(R.id.img3);
        // View
        line1 = rootView.findViewById(R.id.line1);
        line2 = rootView.findViewById(R.id.line2);
        line3 = rootView.findViewById(R.id.line3);
        // TextView
        text1 = rootView.findViewById(R.id.text1);
        text2 = rootView.findViewById(R.id.text2);
        text3 = rootView.findViewById(R.id.text3);

        ImageView play1 = rootView.findViewById(R.id.play1);
        ImageView play2 = rootView.findViewById(R.id.play2);
        ImageView play3 = rootView.findViewById(R.id.play3);
        ImageView play4 = rootView.findViewById(R.id.play4);
        ImageView play5 = rootView.findViewById(R.id.play5);
        ImageView play6 = rootView.findViewById(R.id.play6);
        play1.setAlpha((float) 0.3);
        play2.setAlpha((float) 0.3);
        play3.setAlpha((float) 0.3);
        play4.setAlpha((float) 0.3);
        play5.setAlpha((float) 0.3);
        play6.setAlpha((float) 0.3);

        imageView[0] = rootView.findViewById(R.id.image1);
        imageView[1] = rootView.findViewById(R.id.image2);
        imageView[2] = rootView.findViewById(R.id.image3);
        imageView[3] = rootView.findViewById(R.id.image4);
        imageView[4] = rootView.findViewById(R.id.image5);
        imageView[5] = rootView.findViewById(R.id.image6);

        imageView1[0] = rootView.findViewById(R.id.video1);
        imageView1[1] = rootView.findViewById(R.id.video2);
        imageView1[2] = rootView.findViewById(R.id.video3);
        imageView1[3] = rootView.findViewById(R.id.video4);
        imageView1[4] = rootView.findViewById(R.id.video5);
        imageView1[5] = rootView.findViewById(R.id.video6);

        imageView2[0] = rootView.findViewById(R.id.simage1);
        imageView2[1] = rootView.findViewById(R.id.simage2);
        imageView2[2] = rootView.findViewById(R.id.simage3);
        imageView2[3] = rootView.findViewById(R.id.simage4);
        imageView2[4] = rootView.findViewById(R.id.simage5);
        imageView2[4] = rootView.findViewById(R.id.simage5);
        imageView2[5] = rootView.findViewById(R.id.simage6);
        imageView2[6] = rootView.findViewById(R.id.simage7);
        imageView2[7] = rootView.findViewById(R.id.simage8);
        imageView2[8] = rootView.findViewById(R.id.simage9);
        imageView2[9] = rootView.findViewById(R.id.simage10);
        imageView2[10] = rootView.findViewById(R.id.simage11);

        for (int i = 0; i < 11; i++) {
            imageView2[i].setAlpha((float) (1 - i * 0.08));
        }

        setupRowAdapter();
        setupRowAdapter1();

        // This is c1, c2, c3 setOnFocusChangeListener Code.
        // Their code are almost the same.
        c1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus == true) {
                    Log.d("ssss", "focus category1");

                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    text1.setTextColor(Color.parseColor("#FFFFFF"));
                    text2.setTextColor(Color.parseColor("#8C8C8C"));
                    text3.setTextColor(Color.parseColor("#8C8C8C"));

                }
                else{
                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    text1.setTextColor(Color.parseColor("#8C8C8C"));
                    text2.setTextColor(Color.parseColor("#8C8C8C"));
                    text3.setTextColor(Color.parseColor("#8C8C8C"));
                }
            }
        });
        c2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    Log.d("ssss", "focus category2");

                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    text1.setTextColor(Color.parseColor("#8C8C8C"));
                    text2.setTextColor(Color.parseColor("#FFFFFF"));
                    text3.setTextColor(Color.parseColor("#8C8C8C"));

                }
                else{
                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    text1.setTextColor(Color.parseColor("#8C8C8C"));
                    text2.setTextColor(Color.parseColor("#8C8C8C"));
                    text3.setTextColor(Color.parseColor("#8C8C8C"));
                }
            }
        });
        c3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    Log.d("ssss", "focus category3");

                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.VISIBLE);
                    text1.setTextColor(Color.parseColor("#8C8C8C"));
                    text2.setTextColor(Color.parseColor("#8C8C8C"));
                    text3.setTextColor(Color.parseColor("#FFFFFF"));

                }
                else{
                    img1.setVisibility(View.INVISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    text1.setTextColor(Color.parseColor("#8C8C8C"));
                    text2.setTextColor(Color.parseColor("#8C8C8C"));
                    text3.setTextColor(Color.parseColor("#8C8C8C"));
                }
            }
        });
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        return rootView;


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.Category1:
                intent = new Intent(getContext(),
                        GridExampleActivity.class);
                break;
            case R.id.Category2:
                intent = new Intent(getContext(),
                        VideoGridExampleActivity.class);
                break;
            case R.id.Category3:
                intent = new Intent(getContext(),
                        SlideShow.class);
                break;
        }
        startActivity(intent);
    }


    private void setupRowAdapter() {
        createRows();
    }

    private void setupRowAdapter1() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createRows1();
            }
        }, 1000);
    }

    private void createRows() {
        String json = Utils.inputStreamToString(getResources()
                .openRawResource(R.raw.grid_example));
        CardRow row = new Gson().fromJson(json, CardRow.class);

        // mAdapter.addAll(0, row.getCards());

        for (int i = 0; i < 6; i++) {
            URI id = row.getCards().get(i).getImageURI();
            Log.d("rrrr", id.toString());
            RequestOptions options = new RequestOptions()
                    .centerCrop()

                    .transform(new RoundedCorners(25))
                     // .override(140,500);
                    ;


            Glide.with(this)
                    .load(id.toString())
                    //.load("https://res.heraldm.com/phpwas/restmb_idxmake_amp.php?idx=681&simg=%2Fcontent%2Fimage%2F2017%2F05%2F02%2F20170502000721_0.jpg")

                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(options)

                    //.transition(BitmapTransitionOptions.withCrossFade(1000))
                    .into(imageView[i]);


        }
        for (int i = 0; i < 11; i++) {
            URI id = row.getCards().get(i).getImageURI();
            Log.d("rrrr", id.toString());
            RequestOptions options = new RequestOptions()
                    .centerCrop();


            Glide.with(this)
                    .load(id.toString())
                    //.load("https://res.heraldm.com/phpwas/restmb_idxmake_amp.php?idx=681&simg=%2Fcontent%2Fimage%2F2017%2F05%2F02%2F20170502000721_0.jpg")

                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(options)

                    //.transition(BitmapTransitionOptions.withCrossFade(1000))
                    .into(imageView2[i]);
        }
    }


    private void createRows1() {
        String urlToFetch = getResources().getString(R.string.videos_url);
        fetchVideosInfo(urlToFetch);
    }

    private void onFetchVideosInfoSuccess(JSONObject jsonObj) {
        try {
            String videoRowsJson = jsonObj.getString(TAG_CATEGORY);
            VideoRow[] videoRows = new Gson().fromJson(videoRowsJson, VideoRow[].class);
            for (int i = 0; i < 6; i++) {
                String id = videoRows[0].getVideos().get(i).getVideoSources().get(0);
                Log.d("rrrr", id);

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                       // .onlyRetrieveFromCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new RoundedCorners(25))
                   .override(140,100);


                Glide.with(this)
                        .load(id)
                        //.load("https://res.heraldm.com/phpwas/restmb_idxmake_amp.php?idx=681&simg=%2Fcontent%2Fimage%2F2017%2F05%2F02%2F20170502000721_0.jpg")

                        .apply(options)
                        .into(imageView1[i]);

            }
        } catch (JSONException ex) {
            Log.e(TAG, "A JSON error occurred while fetching videos: " + ex.toString());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onFetchVideosInfoError(Exception ex) {
        Log.e(TAG, "Error fetching videos from " + getResources().getString(R.string.videos_url) +
                ", Exception: " + ex.toString());

    }
    private static class FetchResult {
        private boolean isSuccess;
        private Exception exception;
        JSONObject jsonObj;

        FetchResult(JSONObject obj) {
            jsonObj = obj;
            isSuccess = true;
            exception = null;
        }

        FetchResult(Exception ex) {
            jsonObj = null;
            isSuccess = false;
            exception = ex;
        }
    }

    private void fetchVideosInfo(final String urlString) {

        new AsyncTask<Void, Void, FetchResult>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(FetchResult fetchResult) {
                if (fetchResult.isSuccess) {
                    onFetchVideosInfoSuccess(fetchResult.jsonObj);
                } else {
                    onFetchVideosInfoError(fetchResult.exception);
                }
            }

            @Override
            protected FetchResult doInBackground(Void... params) {
                BufferedReader reader = null;
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    reader = new BufferedReader(
                            new InputStreamReader(urlConnection.getInputStream(),
                                    StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return new FetchResult(new JSONObject(sb.toString()));
                } catch (JSONException ex) {
                    Log.e(TAG, "A JSON error occurred while fetching videos: " + ex.toString());
                    return new FetchResult(ex);
                } catch (IOException ex) {
                    Log.e(TAG, "An I/O error occurred while fetching videos: " + ex.toString());
                    return new FetchResult(ex);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            Log.e(TAG, "JSON reader could not be closed! " + ex);
                        }
                    }
                }
            }
        }.execute();

    }
}




