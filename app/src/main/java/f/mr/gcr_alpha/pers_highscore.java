package f.mr.gcr_alpha;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class pers_highscore extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pers_highscore);

        Button cmd = (Button)findViewById(R.id.cmd_Clear);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });


        loadHighscore();
    }


    private void loadHighscore()
    {
        TextView tv;
        serialize ser = new serialize();
        pers_highscore_propertys highProps = (pers_highscore_propertys) ser.loadSerializedObject(new File("/sdcard/pers_highscore.bin"));

        if (highProps != null)
        {
            tv = findViewById(R.id.tv_name1);
            tv.setText(highProps.getPlayer_name()[0]);
            tv = findViewById(R.id.tv_miles1);
            tv.setText(Integer.toString(highProps.getMiles_score()[0]));
            tv = findViewById(R.id.tv_punkte1);
            tv.setText(Integer.toString(highProps.getMoney_score()[0]));
            tv = findViewById(R.id.tv_name2);
            tv.setText(highProps.getPlayer_name()[1]);
            tv = findViewById(R.id.tv_miles2);
            tv.setText(Integer.toString(highProps.getMiles_score()[1]));
            tv = findViewById(R.id.tv_punkte2);
            tv.setText(Integer.toString(highProps.getMoney_score()[1]));
            tv = findViewById(R.id.tv_name3);
            tv.setText(highProps.getPlayer_name()[2]);
            tv = findViewById(R.id.tv_miles3);
            tv.setText(Integer.toString(highProps.getMiles_score()[2]));
            tv = findViewById(R.id.tv_punkte3);
            tv.setText(Integer.toString(highProps.getMoney_score()[2]));
            tv = findViewById(R.id.tv_name4);
            tv.setText(highProps.getPlayer_name()[3]);
            tv = findViewById(R.id.tv_miles4);
            tv.setText(Integer.toString(highProps.getMiles_score()[3]));
            tv = findViewById(R.id.tv_punkte4);
            tv.setText(Integer.toString(highProps.getMoney_score()[3]));
            tv = findViewById(R.id.tv_name5);
            tv.setText(highProps.getPlayer_name()[4]);
            tv = findViewById(R.id.tv_miles5);
            tv.setText(Integer.toString(highProps.getMiles_score()[4]));
            tv = findViewById(R.id.tv_punkte5);
            tv.setText(Integer.toString(highProps.getMoney_score()[4]));
            tv = findViewById(R.id.tv_name6);
            tv.setText(highProps.getPlayer_name()[5]);
            tv = findViewById(R.id.tv_miles6);
            tv.setText(Integer.toString(highProps.getMiles_score()[5]));
            tv = findViewById(R.id.tv_punkte6);
            tv.setText(Integer.toString(highProps.getMoney_score()[5]));
            tv = findViewById(R.id.tv_name7);
            tv.setText(highProps.getPlayer_name()[6]);
            tv = findViewById(R.id.tv_miles7);
            tv.setText(Integer.toString(highProps.getMiles_score()[6]));
            tv = findViewById(R.id.tv_punkte7);
            tv.setText(Integer.toString(highProps.getMoney_score()[6]));
            tv = findViewById(R.id.tv_name8);
            tv.setText(highProps.getPlayer_name()[7]);
            tv = findViewById(R.id.tv_miles8);
            tv.setText(Integer.toString(highProps.getMiles_score()[7]));
            tv = findViewById(R.id.tv_punkte8);
            tv.setText(Integer.toString(highProps.getMoney_score()[7]));
            tv = findViewById(R.id.tv_name9);
            tv.setText(highProps.getPlayer_name()[8]);
            tv = findViewById(R.id.tv_miles9);
            tv.setText(Integer.toString(highProps.getMiles_score()[8]));
            tv = findViewById(R.id.tv_punkte9);
            tv.setText(Integer.toString(highProps.getMoney_score()[8]));
            tv = findViewById(R.id.tv_name10);
            tv.setText(highProps.getPlayer_name()[9]);
            tv = findViewById(R.id.tv_miles10);
            tv.setText(Integer.toString(highProps.getMiles_score()[9]));
            tv = findViewById(R.id.tv_punkte10);
            tv.setText(Integer.toString(highProps.getMoney_score()[9]));
        }
    }

    private void clear(){
        serialize ser = new serialize();
        pers_highscore pers = new pers_highscore();
        ser.saveObject(pers, new File("/sdcard/pers_highscore.bin"));

        loadHighscore();
    }
}
