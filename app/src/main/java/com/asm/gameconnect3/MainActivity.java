package com.asm.gameconnect3;

//All imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    //Variable initialization
    int player=0;                           //sets red and yellow player
int[] tagState={2,2,2,2,2,2,2,2,2};         // giving default match state , 0 for red,1 for yellow, 2 for null
    boolean finish=true;                    //Used to start or stop functionality of click
    String announcement;                    //Gives result
    ImageView playAgain;
    TextView result;
    GridLayout grid;

//onclick function for all the image views inside grid
    public void imageAnim(View view){

        if(finish) {
        ImageView image= (ImageView)view;
        image.setTranslationY(-2000);
        int [][] winList={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; // All the possible win state
        int tag=Integer.parseInt(image.getTag().toString());                                //getting individual image views by tag attribute
            //to drop red
            if (player == 0) {
                image.setImageResource(R.drawable.red);
                image.animate().translationYBy(2000).setDuration(300);
                player = 1;
                tagState[tag] = 0;
            //to drop yellow
            } else {
                image.setImageResource(R.drawable.yellow);
                image.animate().translationYBy(2000).setDuration(300);
                player = 0;
                tagState[tag] = 1;

            }
        //check winning state at every turn
        for (int[] ints : winList) {

            if (tagState[ints[0]] == tagState[ints[1]] && tagState[ints[2]] == tagState[ints[1]]&&tagState[ints[1]]!=2) {

                if (tagState[ints[0]] == 0) {
                    announcement= "Red has won";
                }
                else {
                    announcement= "Yellow has won";
                }
                finish=false;
                isVisible(announcement);//play again and winning announcement visibility

                break;
            }

        }
        // check draw state
            for(int i = 0,draw=0; i < grid.getChildCount(); i++) {
                ImageView child = (ImageView) grid.getChildAt(i);
                if(child.getDrawable() != null){
                    draw=draw+1;
                }
                Log.i("tagState3", String.valueOf(draw));
                Log.i("tagState3", String.valueOf(finish));
                if (draw==9 && finish){
                    announcement="DRAW!";
                    finish=false;
                    isVisible(announcement);
                    Log.i("tagState3",result.getText().toString());

                    break;
                }

            }

        }
    }
    //play again and winning announcement visibility
    public void isVisible(String announcement){

        result.setText(announcement);
        if (!finish){
            playAgain.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
        }
    }
    //turning every properties to their default state to start the game again
    public void isPlayable(View view){

        finish=true;
        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        //null, all the image resources
        for(int i = 0; i < grid.getChildCount(); i++) {
            ImageView child = (ImageView) grid.getChildAt(i);
            child.setImageDrawable(null);
        }
        Arrays.fill(tagState, 2);
        player=0;
        Log.d("tag", Arrays.toString(tagState));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgain= findViewById(R.id.playAgain);
        result= findViewById(R.id.result);
        grid= findViewById(R.id.grid);

    }
}