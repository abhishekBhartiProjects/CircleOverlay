package com.example.abhishekbharti.circleoverlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    CircleOverlayView circleOverlayView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleOverlayView = new CircleOverlayView(this, 70, 620, 210);
        circleOverlayView.setAlpha(150);

        frameLayout = (FrameLayout) findViewById(R.id.overlay);
        frameLayout.addView(circleOverlayView);
    }
}
