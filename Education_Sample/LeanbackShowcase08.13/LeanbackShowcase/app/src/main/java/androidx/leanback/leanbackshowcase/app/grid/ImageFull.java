package androidx.leanback.leanbackshowcase.app.grid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.models.Card;
import androidx.leanback.leanbackshowcase.models.CardRow;
import androidx.leanback.leanbackshowcase.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import java.util.List;

// This is code about Full Image.
public class ImageFull extends Activity {
    int position;
    List<Card> cards;
    ImageView imageView;
    String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full);

        imageView=(ImageView) findViewById(R.id.imageView);

        String json = Utils.inputStreamToString(getResources()
                .openRawResource(R.raw.grid_example));
        // java - androidx.leanback.leanbackshowcase - models - Card Java Class
        CardRow row = new Gson().fromJson(json, CardRow.class);
        cards=row.getCards();

        Intent intent= getIntent();
        Bundle bundle=intent.getExtras();

        String id=bundle.getString("uri");
        //URI id= URI.parse(uriString);
        title=bundle.getString("title");
        Log.d("kimjina","title1:"+title);

        for(int i=0;i<cards.size();i++) {
            if(cards.get(i).getTitle().equals(title)) {
                position=i;
            }
        }

        setImage(id);

    }

    // This is RemoteControl Code
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            event.startTracking();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // This is RemoteControl Code
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                position--;
                if(position==-1) {
                    // Toast.makeText(this, "First Image", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "처음 이미지 입니다.", Toast.LENGTH_SHORT).show();
                    position=0;
                    return true;
                }

                setImage(cards.get(position).getImageURI().toString());
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                position++;
                if(position==cards.size()){
                    // Toast.makeText(this, "Last Image", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "마지막 이미지 입니다.", Toast.LENGTH_SHORT).show();
                    position=cards.size()-1;
                    return true;
                }

                setImage(cards.get(position).getImageURI().toString());
                //Glide.with(this)
                //        .load(cards.get(position+dj1).getImageURI().toString())
                //        .into(imageView);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public void setImage(String id){
        Glide.with(this)
                .load(id)
                .into(imageView);
    }

}
