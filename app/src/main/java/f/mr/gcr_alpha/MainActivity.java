package f.mr.gcr_alpha;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity{

    private main_properties var_main_properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView iv = (ImageView)findViewById(R.id.ivBack);
        iv.setVisibility(iv.INVISIBLE);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.test);

        Button cmd = (Button)findViewById(R.id.button);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        Button cmdEinstellungen = (Button)findViewById(R.id.cmdEinstellungen);
        cmdEinstellungen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEinstellungen();
            }
        });

        Button cmdPersHighscore = (Button)findViewById(R.id.cmd_pers_highscore);
        cmdPersHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPersHighscore();
            }
        });

        Button cmdShop = (Button)findViewById(R.id.cmd_Shop);
        cmdShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShop();
            }
        });

        serialize serialize = new serialize();


        // Get the Object
        main_properties prop = new main_properties();// = (main_properties) serialize.loadSerializedObject(new File("/sdcard/save_USRDATA.bin")); //get the serialized object from the sdcard and caste it into the Person class.

        if (prop != null)
        {
            var_main_properties = new main_properties(prop.getPoints(), prop.getDiamonds(), prop.getLevel(), prop.getExp(), prop.getMap_resource(), prop.getUser(), prop.getVehicel());

            serialize.saveObject(prop, new File("/sdcard/save_USRDATA.bin"));
        }
        else
        {
            prop = new main_properties();
            //serialize.saveObject(prop, new File("/sdcard/save_USRDATA.bin"));
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        serialize serialize = new serialize();


        // Get the Object
        main_properties prop = new main_properties();//(main_properties) serialize.loadSerializedObject(new File("/sdcard/save_USRDATA.bin")); //get the serialized object from the sdcard and caste it into the Person class.

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv = (TextView)toolbar.findViewById(R.id.tv_money);
        tv.setText(Integer.toString(prop.getPoints()));
        tv = toolbar.findViewById(R.id.tv_diamonds);
        tv.setText(Integer.toString(prop.getDiamonds()));

        var_main_properties = new main_properties(prop.getPoints(), prop.getDiamonds(), prop.getLevel(), prop.getExp(), prop.getMap_resource(), prop.getUser(), prop.getVehicel());

        serialize.saveObject(prop, new File("/sdcard/save_USRDATA.bin"));
    }

    public void startGame()
    {
        TextView tv = findViewById(R.id.ed_player_name);
        String pn = tv.getText().toString();
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("main_prop", var_main_properties);
        intent.putExtra("playerName", pn);
        startActivity(intent);
    }

    public void startEinstellungen() {
        startActivity(new Intent(this, einstellungen.class));

    }

    public void startPersHighscore()
    {
        startActivity(new Intent(this, pers_highscore.class));
    }

    public void startShop()
    {
        startActivity(new Intent(this, shop.class));
    }
}
