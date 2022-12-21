package androidx.leanback.leanbackshowcase.app.grid;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.models.CardRow;
import androidx.leanback.leanbackshowcase.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import androidx.leanback.leanbackshowcase.app.Menu;
import jp.wasabeef.glide.transformations.BlurTransformation;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

// This is code about SlideShow
public class SlideShow extends Activity {

    MediaPlayer player;
    // 다시 시작 기능을 위한 현재 재생 위치 확인 변수
    // Current Playback location For the Restart Function
    int position = 0;

    Animation prev, next;
    ViewFlipper viewFlipper;
    ImageView bgImg;
    Boolean isStop = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        // Declare viewFlipper
        viewFlipper = findViewById(R.id.ViewFlipper01);

        prev = AnimationUtils.loadAnimation(this,R.anim.push_left_in);
        next = AnimationUtils.loadAnimation(this,R.anim.push_left_out);

        viewFlipper.setInAnimation(prev);
        viewFlipper.setOutAnimation(next);

        // This is Json code.
        String json = Utils.inputStreamToString(getResources()
                .openRawResource(R.raw.grid_example));
        final CardRow row = new Gson().fromJson(json, CardRow.class);


        View flipperview[]=new View[row.getCards().size()];
        for (int i = 0; i < row.getCards().size(); i++) {

            flipperview[i]=(View)View.inflate(this, R.layout.test2, null);
            // test2.xml ImageView id : realImg, bgImg
            ImageView realImg= (ImageView)flipperview[i].findViewById(R.id.realImg);
            ImageView bgImg= (ImageView)flipperview[i].findViewById(R.id.bgImg);

            Glide.with(this)
                    .load(row.getCards().get(i).getImageURI().toString())
                    .into(realImg);

            Glide.with(this)
                    .load(row.getCards().get(i).getImageURI().toString())
                    .apply(bitmapTransform(new BlurTransformation(25)))
                    .into(bgImg);

            viewFlipper.addView(flipperview[i]);
        }

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);

        playAudio();
    }

    // This is RemoteControl Code
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // This is RemoteControl Code
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                Log.d("remote", "onKeyUp pressed KEYCODE_DPAD_CENTER :: for keycode " + keyCode);
                isStop=!isStop;
                if(isStop) {
                    viewFlipper.stopFlipping();
                    pauseAudio();
                    Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
                }
                else {
                    viewFlipper.startFlipping();
                    resumeAudio();
                    Toast.makeText(this, "reStart", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

           else if ((keyCode == KeyEvent.KEYCODE_DPAD_UP )|| (keyCode ==KeyEvent.KEYCODE_DPAD_DOWN)) {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    // If you start SlideShow, The music plays.
    private void playAudio() {
        closePlayer();
        player = MediaPlayer.create(this, R.raw.music2);
        player.start();
        // Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "재생 시작됨.", Toast.LENGTH_SHORT).show();
    }

    // 현재 일시정지가 되었는지 중지가 되었는지 헷갈릴 수 있기 때문에 스위치 변수를 선언해 구분할 필요가 있다. (구현은 안했다.)
    // We don't know whether it's currently Audio pause or Audio stop. We need to implement the code.
    private void pauseAudio() {
        if (player != null) {
            position = player.getCurrentPosition();
            player.pause();
            // Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "일시정지됨.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resumeAudio() {
        if (player != null && !player.isPlaying()) {
            player.seekTo(position);
            player.start();
            // Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "재시작됨.", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopAudio() {
        if(player != null && player.isPlaying()){
            player.stop();
            // Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "중지됨.", Toast.LENGTH_SHORT).show();
        }
    }

    /* 녹음 시 마이크 리소스 제한. 누군가가 lock 걸어놓으면 다른 앱에서 사용할 수 없음.
     * 따라서 꼭 리소스를 해제해주어야함. */
    public void closePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("sssss","onpaused");
        player.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("sssss","ondestroy");
        player.stop();
        closePlayer();
    }

}