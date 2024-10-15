package com.example.coinflipproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewCoin;
    private Button buttonHeads;
    private Button buttonTails;
    private TextView textViewResult;
    private int rounds;
    private int win;
    private int lose;
    private Random random;
    private AlertDialog alertDialog;

    private int coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        buttonHeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(0);
            }
        });

        buttonTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });
    }

    public void check(int playerChoose) {

        if (rounds < 5) {
            rounds++;
            int result = random.nextInt(2);

            if (result == 0) {
                imageViewCoin.setImageResource(R.drawable.head);
            } else {
                imageViewCoin.setImageResource(R.drawable.tails);
            }


            if (playerChoose == result) {
                win++;
                textViewResult.setText(String.format("Dobások száma: %d \nGyőzelmek: %d Vereségek: %d", rounds, win, lose));
            } else {
                lose++;
                textViewResult.setText(String.format("Dobások száma: %d \nGyőzelmek: %d Vereségek: %d", rounds, win, lose));
            }


        } else {
            showDialog();
        }
    }

    public void init() {
        random = new Random();

        imageViewCoin = findViewById(R.id.imageViewCoin);
        buttonHeads = findViewById(R.id.buttonHeads);
        buttonTails = findViewById(R.id.buttonTails);
        textViewResult = findViewById(R.id.textViewResult);

        rounds = 0;
        win = 0;
        lose = 0;
        textViewResult.setText(String.format("Dobások száma: %d \nGyőzelmek: %d Vereségek: %d", rounds, win, lose));

        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Játék vége")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetGame();
                    }
                })
                .setCancelable(false)
                .create();
    }

    private void showDialog() {
        alertDialog.show();
    }

    private void resetGame() {
        rounds = 0;
        win = 0;
        lose = 0;
        textViewResult.setText(String.format("Dobások száma: %d \nGyőzelmek: %d Vereségek: %d", rounds, win, lose));
        imageViewCoin.setImageResource(R.drawable.img);
    }
}
