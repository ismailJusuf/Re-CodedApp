package com.example.isma3el.re_codedapp.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isma3el.re_codedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class WinnerActivity extends AppCompatActivity {

    @BindView(R.id.viewKonfetti)
    KonfettiView viewKonfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        ButterKnife.bind(this);

        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(1600L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(9, 4f))
                .setPosition(-50f, viewKonfetti.getWidth() + 1400f, -50f, -50f)
                .stream(230, 9000L);

    }
}
