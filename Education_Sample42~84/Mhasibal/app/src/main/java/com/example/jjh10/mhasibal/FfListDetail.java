package com.example.jjh10.mhasibal;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * Created by 문경태 on 2018-05-26.
 */

public class FfListDetail extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ff_list_detail);

        TextView tvTitle = (TextView)findViewById(R.id.tvff01);
        TextView tvAddress = (TextView)findViewById(R.id.tvff02);
        ImageView imageView = (ImageView)findViewById(R.id.ivff01);
        Button detailBtn = (Button)findViewById(R.id.detailBtn);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));
        tvAddress.setText(intent.getStringExtra("address"));
        imageView.setImageResource(intent.getIntExtra("img", 0));

    }
}
