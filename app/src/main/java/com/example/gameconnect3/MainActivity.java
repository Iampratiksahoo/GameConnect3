package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    // 0 is Yellow, 1 is Red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;




    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        TextView textView = (TextView)findViewById(R.id.textView);
        if(gameState[tappedCounter] == 2 && gameActive )
        {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);


            if (activePlayer == 0)
            {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else
                {
                counter.setImageResource((R.drawable.red));

                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotationY(360).setDuration(300);

            for (int[] winPos : winningPositions)
            {

                if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2)
                {
                    gameActive = false;

                    String winner = "";
                    if (activePlayer == 1)
                    {
                        winner = "Yellow";
                    }
                    else
                    {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " Has Won!!", Toast.LENGTH_SHORT).show();
                    Button restartButton = (Button) findViewById(R.id.restartButton);
                    restartButton.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void click(View view)
    {
        Button restartButton = (Button) findViewById(R.id.restartButton);
        TextView textView = (TextView)findViewById(R.id.textView);
        restartButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);


        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ImageView Counter = (ImageView)gridLayout.getChildAt(i);
            Counter.setImageDrawable(null);
        }

        for(int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

       activePlayer = 0;
       gameActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}