package f.mr.gcr_alpha;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.io.File;

public class einstellungen extends Activity implements View.OnClickListener{


    private int GESCHWINDIGKEIT = 10; //kmh
    private int ANZAHL_MONEY = 10;
    private int ANZAHL_OBSTACES = 35;
    private int ANZAHL_Y_POINTS = 40;
    private int ANZAHL_LEBEN = 3;
    private int MAXIMALE_ANZAHL_COL_OBSTACLE = 10;
    private int MAXIMALE_ANZAHL_ROW_OBSTACLE = 7; //Startend von 0
    //GROESSE + (ABSTAND * 2) müssen immer 1 ergeben
    private double GROESSE_USER_IMG = 0.8;
    private double ABSTAND_SPURLINIE_USER = 0.1;
    private double GROESSE_MONEY = 0.3;
    private double ABSTAND_SPURLINIE_MONEY = 0.35;
    private double GROESSE_OBSTACLE = 0.75;
    private double ABSTAND_SPURLINIE_OBSTACLE = 0.35;
    private double OBSTACLE_PERCENT_FROM_USER = 3;//0.75;
    private double MONEY_PERCENT_FROM_USER = 0.5;
    private int FLASHTIME_USR = 100;
    private int FLASHCNT_USR = 20;
    private double less_hit_size = 19;

    private boolean isHit;

    private EditText ed_speed;
    private EditText ed_anzahlMoney;
    private EditText ed_anzahlObst;
    private EditText ed_anzahlY;
    private EditText ed_AnzahlLeben;
    private EditText ed_MAX_ANZ_COL_OBST;
    private EditText ed_MAX_ANZ_ROW_OBST;
    private EditText ed_groesse_USRIMG;
    private EditText ed_abstand_USR;
    private EditText ed_groesse_Money;
    private EditText ed_abstand_money;
    private EditText ed_groesse_obst;
    private EditText ed_abstand_obst;
    private EditText ed_abstand_percent_OBST;
    private EditText ed_abstand_percent_MONEY;
    private EditText ed_flashtimeUSR;
    private EditText ed_flashcnt_USR;
    private EditText ed_less_hit_size;

    private CheckBox chk_Hit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellungen);


        ed_speed = (EditText)findViewById(R.id.ed_speed);
        ed_anzahlMoney = (EditText)findViewById(R.id.ed_Anzahl_money);
        ed_anzahlObst = (EditText)findViewById(R.id.ed_Anzahl_obstaces);
        ed_anzahlY = (EditText)findViewById(R.id.ed_Anzahl_Y_Points);
        ed_AnzahlLeben = (EditText)findViewById(R.id.ed_Anzahl_leben);
        ed_MAX_ANZ_COL_OBST = (EditText)findViewById(R.id.ed_max_col_cnt_obst);
        ed_MAX_ANZ_ROW_OBST = (EditText)findViewById(R.id.ed_max_row_cnt_obst);
        ed_flashtimeUSR = (EditText)findViewById(R.id.ed_Flashtime_USR);
        ed_flashcnt_USR = (EditText)findViewById(R.id.ed_flashcnt_USR);
        ed_groesse_USRIMG = (EditText)findViewById(R.id.ed_groesse_USRIMG);
        ed_abstand_USR = (EditText)findViewById(R.id.ed_abstand_spur_USRIMG);
        ed_groesse_Money = (EditText)findViewById(R.id.ed_groesse_MoneyIMG);
        ed_abstand_money = (EditText)findViewById(R.id.ed_abstand_spur_moneyIMG);
        ed_groesse_obst = (EditText)findViewById(R.id.ed_groesse_obstIMG);
        ed_abstand_obst = (EditText)findViewById(R.id.ed_abstand_spur_obstIMG);
        ed_abstand_percent_OBST = (EditText)findViewById(R.id.ed_obst_percent_fromUSR);
        ed_abstand_percent_MONEY = (EditText)findViewById(R.id.ed_money_percent_fromUSR);
        ed_less_hit_size = (EditText)findViewById(R.id.ed_less_hit_size);
        chk_Hit = (CheckBox)findViewById(R.id.chk_hit_Obst);

        Button cmd = (Button)findViewById(R.id.cmd_save);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_speed);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_Speed();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_anzahl_money);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_anzahlMoney();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_anzahl_obst);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_anzahlObst();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_anzahlYPoint);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_anzahlY();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_anzahlLeben);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_anzahlLeben();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_flashcntUSR);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_flascnt_USR();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_flashtimeUSR);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_flashtime_USR();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_max_col_cnt_obst);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_max_col_cnt_obst();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_max_row_cnt_obst);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_max_row_cnt_obst();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_groesseUSRIMG);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_groesseUSRIMG();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_abstandSpurUSR);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_abstandUSRIMG();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_groesseMoneyIMG);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_groesseMONEY();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_abstandSpurMoneyIMG);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_abstandMONEY();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_groesseObstIMG);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_groesseOBST();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_abstandSpurObstIMG);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_abstandOBST();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_obstPercentFromUSR);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_obstPercentUSR();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_moneyPercentFromUSR);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_moneyPercentUSR();
            }
        });

        cmd = (Button)findViewById(R.id.cmd_standart_less_hit_size);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standart_less_hit_size();
            }
        });

        serialize serialize = new serialize();
        static_einstellungen se = (static_einstellungen) serialize.loadSerializedObject(new File("/sdcard/save_einstellungen.bin")); //get the serialized object from the sdcard and caste it into the Person class.

        if (se != null)
        {
            static_einstellungen se2 = new static_einstellungen(se.getGESCHWINDIGKEIT(), se.getANZAHL_MONEY(), se.getANZAHL_OBSTACES(), se.getANZAHL_Y_POINTS(), se.getANZAHL_LEBEN(), se.getMAXIMALE_ANZAHL_COL_OBSTACLE(),
                    se.getMAXIMALE_ANZAHL_ROW_OBSTACLE(), se.getGROESSE_USER_IMG(), se.getABSTAND_SPURLINIE_USER(), se.getGROESSE_MONEY(), se.getABSTAND_SPURLINIE_MONEY(), se.getGROESSE_OBSTACLE(),
                    se.getABSTAND_SPURLINIE_OBSTACLE(), se.getOBSTACLE_PERCENT_FROM_USER(), se.getMONEY_PERCENT_FROM_USER(), se.getFLASHTIME_USR(), se.getFLASHCNT_USR(),
                    se.getLess_hit_size(), se.isVisible());
            setData(se2);
        }
        else
        {
            se = new static_einstellungen();
        }

        cmd = (Button)findViewById(R.id.cmd_cancel);
        cmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v)
    {


    }

    public void setData(static_einstellungen se)
    {
        ed_speed.setText(Integer.toString(se.getGESCHWINDIGKEIT()));
        ed_anzahlMoney.setText(Integer.toString(se.getANZAHL_MONEY()));
        ed_anzahlObst.setText(Integer.toString(se.getANZAHL_OBSTACES()));
        ed_anzahlY.setText(Integer.toString(se.getANZAHL_Y_POINTS()));
        ed_AnzahlLeben.setText(Integer.toString(se.getANZAHL_LEBEN()));
        ed_MAX_ANZ_COL_OBST.setText(Integer.toString(se.getMAXIMALE_ANZAHL_COL_OBSTACLE()));
        ed_MAX_ANZ_ROW_OBST.setText(Integer.toString(se.getMAXIMALE_ANZAHL_ROW_OBSTACLE()));
        ed_flashcnt_USR.setText(Integer.toString(se.getFLASHCNT_USR()));
        ed_flashtimeUSR.setText(Integer.toString(se.getFLASHTIME_USR()));
        ed_groesse_USRIMG.setText(Double.toString(se.getGROESSE_USER_IMG()));
        ed_abstand_USR.setText(Double.toString(se.getABSTAND_SPURLINIE_USER()));
        ed_groesse_Money.setText(Double.toString(se.getGROESSE_MONEY()));
        ed_abstand_money.setText(Double.toString(se.getABSTAND_SPURLINIE_MONEY()));
        ed_groesse_obst.setText(Double.toString(se.getGROESSE_OBSTACLE()));
        ed_abstand_obst.setText(Double.toString(se.getABSTAND_SPURLINIE_OBSTACLE()));
        ed_abstand_percent_OBST.setText(Double.toString(se.getOBSTACLE_PERCENT_FROM_USER()));
        ed_abstand_percent_MONEY.setText(Double.toString(se.getMONEY_PERCENT_FROM_USER()));
        ed_less_hit_size.setText(Double.toString(se.getLess_hit_size()));

        chk_Hit.setChecked(se.isVisible());

    }

    public void save()
    {
        static_einstellungen se = new static_einstellungen();
        se.setGESCHWINDIGKEIT(Integer.parseInt(ed_speed.getText().toString()));
        se.setANZAHL_MONEY(Integer.parseInt(ed_anzahlMoney.getText().toString()));
        se.setANZAHL_OBSTACES(Integer.parseInt(ed_anzahlObst.getText().toString()));
        se.setANZAHL_Y_POINTS(Integer.parseInt(ed_anzahlY.getText().toString()));
        se.setANZAHL_LEBEN(Integer.parseInt(ed_AnzahlLeben.getText().toString()));
        se.setMAXIMALE_ANZAHL_COL_OBSTACLE(Integer.parseInt(ed_MAX_ANZ_COL_OBST.getText().toString()));
        se.setMAXIMALE_ANZAHL_ROW_OBSTACLE(Integer.parseInt(ed_MAX_ANZ_ROW_OBST.getText().toString()));
        se.setFLASHTIME_USR(Integer.parseInt(ed_flashtimeUSR.getText().toString()));
        se.setFLASHCNT_USR(Integer.parseInt(ed_flashcnt_USR.getText().toString()));
        se.setGROESSE_USER_IMG(Double.parseDouble(ed_groesse_USRIMG.getText().toString()));
        se.setABSTAND_SPURLINIE_USER(Double.parseDouble(ed_abstand_USR.getText().toString()));
        se.setGROESSE_MONEY(Double.parseDouble(ed_groesse_Money.getText().toString()));
        se.setABSTAND_SPURLINIE_MONEY(Double.parseDouble(ed_abstand_money.getText().toString()));
        se.setGROESSE_OBSTACLE(Double.parseDouble(ed_groesse_obst.getText().toString()));
        se.setABSTAND_SPURLINIE_OBSTACLE(Double.parseDouble(ed_abstand_obst.getText().toString()));
        se.setOBSTACLE_PERCENT_FROM_USER(Double.parseDouble(ed_abstand_percent_OBST.getText().toString()));
        se.setMONEY_PERCENT_FROM_USER(Double.parseDouble(ed_abstand_percent_MONEY.getText().toString()));
        se.setLess_hit_size(Double.parseDouble(ed_less_hit_size.getText().toString()));

        se.setVisible(chk_Hit.isChecked());

        serialize ser = new serialize();
        ser.saveObject(se, new File("/sdcard/save_einstellungen.bin"));

        finish();
    }

    public void standart_Speed()
    {
        ed_speed.setText(Integer.toString(10));
    }
    public void standart_anzahlMoney()
    {
        ed_anzahlMoney.setText(Integer.toString(10));
    }
    public void standart_anzahlObst()
    {
        ed_anzahlObst.setText(Integer.toString(35));
    }
    public void standart_anzahlY()
    {
        ed_anzahlY.setText(Integer.toString(40));
    }
    public void standart_anzahlLeben()
    {
        ed_AnzahlLeben.setText(Integer.toString(3));
    }
    public void standart_max_col_cnt_obst()
    {
        ed_MAX_ANZ_COL_OBST.setText(Integer.toString(10));
    }
    public void standart_max_row_cnt_obst()
    {
        ed_MAX_ANZ_ROW_OBST.setText(Integer.toString(7));
    }
    public void standart_flashtime_USR()
    {
        ed_flashtimeUSR.setText(Integer.toString(100));
    }
    public void standart_flascnt_USR()
    {
        ed_flashcnt_USR.setText(Integer.toString(20));
    }
    public void standart_groesseUSRIMG()
    {
        ed_groesse_USRIMG.setText(Double.toString(0.8));
    }
    public void standart_abstandUSRIMG()
    {
        ed_abstand_USR.setText(Double.toString(0.1));
    }
    public void standart_groesseMONEY()
    {
        ed_groesse_Money.setText(Double.toString(0.3));
    }
    public void standart_abstandMONEY()
    {
        ed_abstand_money.setText(Double.toString(0.35));
    }
    public void standart_groesseOBST()
    {
        ed_groesse_obst.setText(Double.toString(0.75));
    }
    public void standart_abstandOBST()
    {
        ed_abstand_obst.setText(Double.toString(0.35));
    }
    public void standart_obstPercentUSR() {
        ed_abstand_percent_OBST.setText(Double.toString(3));
    }
    public void standart_moneyPercentUSR() {
        ed_abstand_percent_MONEY.setText(Double.toString(0.5));
    }
    public void standart_less_hit_size()
    {
        less_hit_size = 1;
    }

}
