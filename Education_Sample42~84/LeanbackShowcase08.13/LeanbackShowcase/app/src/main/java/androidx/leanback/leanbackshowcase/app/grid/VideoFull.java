package androidx.leanback.leanbackshowcase.app.grid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import androidx.leanback.leanbackshowcase.R;

// This is code about Full Video.
public class VideoFull extends Activity {
    private PlayerView exoPlayerView;
    private SimpleExoPlayer player;

    private Boolean playWhenReady = true;
    private int currentWindow = 0;
    private Long playbackPosition = 0L;

    String sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_full);

        Intent intent = getIntent();
        sample = intent.getStringExtra("source");

        // video_full.xml PlayerView id : exoPlayerView
        exoPlayerView = findViewById(R.id.exoPlayerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void initializePlayer() {
        if (player == null) {

            /*DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(this.getApplicationContext());
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            DefaultLoadControl loadControl = new DefaultLoadControl();

            player = ExoPlayerFactory.newSimpleInstance(
                    this.getApplicationContext(),
                    renderersFactory,
                    trackSelector,
                    loadControl);*/

            player = ExoPlayerFactory.newSimpleInstance(this.getApplicationContext());

            //플레이어 연결
            exoPlayerView.setPlayer(player);

            //컨트롤러 없애기
            //exoPlayerView.setControllerHideOnTouch(true);
            //exoPlayerView.setUseController(true);

            //사이즈 조절
            //exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM); // or RESIZE_MODE_FILL

            //음량조절
            //player.setVolume(0);

            //프레임 포지션 설정
            //player.seekTo(currentWindow, playbackPosition);
        }
        //String sample= "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        MediaSource mediaSource = buildMediaSource(Uri.parse(sample));
        player.prepare(mediaSource, true, false);
        player.setPlayWhenReady(playWhenReady);
    }

    // We used ExoPlayer Library.
    private MediaSource buildMediaSource(Uri uri) {

        String userAgent = Util.getUserAgent(this, "blackJin");
        if (uri.getLastPathSegment().contains("mp3") || uri.getLastPathSegment().contains("mp4")) {
            return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri);

        } else if (uri.getLastPathSegment().contains("m3u8")) {
            // com.google.android.exoplayer:exoplayer-hls 확장 라이브러리를 빌드 해야 합니다.
            // We should build 'com.google.android.exoplayer:exoplayer-hls' Extended Library.
            return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent)).createMediaSource(uri);

        } else {
            return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(this, userAgent)).createMediaSource(uri);
        }

    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();

            exoPlayerView.setPlayer(null);
            player.release();
            player = null;

        }
    }

    // This is RemoteControl Code.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0) {
            exoPlayerView.showController();
        }
        return super.onKeyUp(keyCode, event);
    }

}