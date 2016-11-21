package ml.aditi.www.scarnesdice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int useroverallscore = 0;
    public int computeroverallscore = 0;
    public int userturnscore = 0;
    public int computerturnscore = 0;
    TextView t1;
    ImageView im;
    Button b1, b2, b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        t1 = (TextView) findViewById(R.id.textView);
        im = (ImageView) findViewById(R.id.imageView);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);


        class ListenerForRoll implements View.OnClickListener {
            public void onClick(View v) {
                setTitle("Your Turn");

                int dieval = roll();

                String tryi = Integer.toString(dieval);
                String url = "dice" + tryi;
                int drawableID = MainActivity.this.getResources().getIdentifier(url, "drawable", getPackageName());
                im.setImageResource(drawableID);
                if (dieval != 1) {
                    userturnscore += dieval;
                    t1.setText("Your Score :" + useroverallscore + " Computer Score : " + computeroverallscore + "  Your TurnScore :" + userturnscore);
                }
                if (dieval == 1) {
                    userturnscore = 0;
                    t1.setText("Your Score :" + useroverallscore + " Computer Score : " + computeroverallscore + "  Your TurnScore :" + userturnscore);
                    computerRoll();
                }
                if (useroverallscore == 100 || useroverallscore >= 100)
                {
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    t1.setText(" Congratulation ! You win :-)  Your Score :"+useroverallscore);
                }

            }

        }


        ListenerForRoll meralistener = new ListenerForRoll();
        b1.setOnClickListener(meralistener);


        class ListenerForReset implements View.OnClickListener {
            public void onClick(View v) {
                userturnscore = 0;
                useroverallscore = 0;
                computeroverallscore = 0;
                computerturnscore = 0;
                t1.setText("Your Score :" + useroverallscore + " Computer Score : " + computeroverallscore);
                int drawableID = MainActivity.this.getResources().getIdentifier("dice1", "drawable", getPackageName());
                im.setImageResource(drawableID);
                b1.setEnabled(true);
                b2.setEnabled(true);
            }

        }

        ListenerForReset meralistener1 = new ListenerForReset();
        b3.setOnClickListener(meralistener1);


        class ListenerForHold implements View.OnClickListener {
            public void onClick(View v) {
                useroverallscore += userturnscore;
                userturnscore=0;
                t1.setText("Your score : " + useroverallscore + "  Computer score :" + computeroverallscore);
                computerRoll();

            }

        }

        ListenerForHold meralistener3 = new ListenerForHold();
        b2.setOnClickListener(meralistener3);



   }



    public void computerRoll()
    {   setTitle("Computer's turn");
        b1.setEnabled(false);
        b2.setEnabled(false);
        int die ;
           do
            {
                 die = roll();
                String tryi = Integer.toString(die);
                String url = "dice" + tryi;
                int drawableID = MainActivity.this.getResources().getIdentifier(url, "drawable", getPackageName());
                im.setImageResource(drawableID);
                if(die!=1)
                {
                    computerturnscore+= die;
                    t1.setText("Your Score :" + useroverallscore + " Computer Score : " + computeroverallscore + " Computer TurnScore:" + computerturnscore);}

             } while (computerturnscore <= 20 && die >= 2 && die <= 6);
                if (computerturnscore >= 20)
                { computerHold();
                    setTitle("Your turn");
                        }

                  if (die == 1)
                  {computerHold();
                      setTitle("Your turn");

                  }
        if (computeroverallscore == 100 || computeroverallscore >= 100)
        {
            b1.setEnabled(false);
            b2.setEnabled(false);
            t1.setText("Computer win! Computer Score :"+computeroverallscore);
        }

    }

     public void computerHold()
     {
         computeroverallscore+=computerturnscore;

         b1.setEnabled(true);
         b2.setEnabled(true);
         t1.setText("Your Score :" + useroverallscore + " Computer Score : " + computeroverallscore+" ( Computer last Turnscore:"+computerturnscore+")");
         computerturnscore=0;
         int drawableID = MainActivity.this.getResources().getIdentifier("dice1", "drawable", getPackageName());
         im.setImageResource(drawableID);
     }

    public int roll() {
        return (int) (6.0 * Math.random()) + 1;
    }

}
