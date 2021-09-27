package com.test.neil.kavayi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Intent intent = getIntent();
        Log.e("nie", "=======test 1 = " + intent.getStringExtra("test_1"));
        Log.e("nie", "=======test 2 = " + intent.getIntExtra("test_2", 3));
        Log.e("nie", "=======uri = " + intent.getData());

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv:
                break;
        }
    }
}
