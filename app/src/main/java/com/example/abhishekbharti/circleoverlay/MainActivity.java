package com.example.abhishekbharti.circleoverlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    ShapeOverlayView shapeOverlayView;
    FrameLayout frameLayout;
    Button circular, rectangular, reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.overlay);
        circular = (Button) findViewById(R.id.circular);
        rectangular = (Button) findViewById(R.id.rectangular);
        reset = (Button) findViewById(R.id.reset);

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeOverlayView = new ShapeOverlayView(view.getContext(), 70, 620, 210);
                shapeOverlayView.setAlpha(150);
                frameLayout.removeAllViews();
                frameLayout.addView(shapeOverlayView);
            }
        });

        rectangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeOverlayView = new ShapeOverlayView(view.getContext(), 6, 520, 720, 150, 250);
                shapeOverlayView.setAlpha(150);
                frameLayout.removeAllViews();
                frameLayout.addView(shapeOverlayView);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.removeAllViews();
            }
        });
    }
}
