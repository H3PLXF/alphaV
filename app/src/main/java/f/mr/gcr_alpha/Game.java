package f.mr.gcr_alpha;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

public class Game extends Activity implements Runnable, Serializable{

    private  int GESCHWINDIGKEIT = 10; //kmh
    private  int ANZAHL_MONEY = 10;
    private  int ANZAHL_OBSTACES ;
    private  int ANZAHL_Y_POINTS;
    private  int ANZAHL_LEBEN = 3;
    private  int MAXIMALE_ANZAHL_COL_OBSTACLE = 10;
    private  int MAXIMALE_ANZAHL_ROW_OBSTACLE = 7; //Startend von 0
    //GROESSE + (ABSTAND * 2) müssen immer 1 ergeben
    private  double GROESSE_USER_IMG = 0.8;
    private  double ABSTAND_SPURLINIE_USER = 0.1;
    private  double GROESSE_MONEY = 0.3;
    private  double ABSTAND_SPURLINIE_MONEY = 0.35;
    private  double GROESSE_OBSTACLE = 0.75;
    private  double ABSTAND_SPURLINIE_OBSTACLE = 0.35;
    private  double OBSTACLE_PERCENT_FROM_USER = 3;//0.75;
    private  double MONEY_PERCENT_FROM_USER = 0.5;
    private  int FLASHTIME_USR = 100;
    private  int FLASHCNT_USR = 20;
    private double less_hit_size = 1;
    //-------------- ^ STATICS ^ -----------------------------
    private int Background_Hight; //1125dp = 1500px
    private Handler handler = new Handler();
    private int[] way_X;
    private int[] index_draussen_obstaces;
    private ImageView backgroundLEFT[];
    private ImageView backgroundRIGHT[];
    private ImageView backgroundWay[];
    private ImageView bonus_money[];
    private ImageView obstaces[];
    private ImageView imgUSER;
    private Display display;
    private FrameLayout frame;
    private int dp_width;
    private int dp_height;
    private int AnzahlBackPic;
    private int punkte;
    private int punkte_add = 1;
    private int leben;
    private Dialog gameOverDialog;
    private main_properties var_main_properties;
    private boolean isVisible_USR = true;
    private int cnt_flashingUSR = 0;
    private int akt_row_usr = 1; //START POS
    private boolean isPause = false;

    private boolean goRight = false;
    private boolean goLeft = false;

    private boolean isok = false;
    private int Miles = 0;
    private int max_ALK_Miles = 1000;
    private int min_ALK_Miles = 500;
    private boolean move_ALK = false;
    private int ALK_MILES = 0;
    private int drunk_interval = 100;
    private int drunk_dauer = 25;
    private int drunk_cnt = 0;
    private ImageView iv_ALK = null;
    private boolean drunkFlash = false;
    private int cnt_hits_alk = 0;

    private int max_ExtraLife_Miles = 5000;
    private int min_ExtraLife_Miles = 4500;
    private boolean move_ExtraLife = false;
    private int ExtraLife_MILES = 0;
    private ImageView iv_ExtraLife = null;

    private int max_Diamonds_Miles = 10000;
    private int min_Diamonds_Miles = 9000;
    private boolean move_Diamonds = false;
    private int Diamonds_MILES = 0;
    private ImageView iv_Diamonds = null;
    private int diamonds;

    private String playerName;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        var_main_properties = (main_properties) getIntent().getSerializableExtra("main_prop");
        playerName = (String)getIntent().getSerializableExtra("playerName");

        display  = getWindowManager().getDefaultDisplay();
        dp_width = display.getWidth();
        dp_height = display.getHeight();

        loadStaticEinstellungen();

        ANZAHL_Y_POINTS = 5;
        ANZAHL_OBSTACES = ANZAHL_Y_POINTS * (MAXIMALE_ANZAHL_ROW_OBSTACLE-1) ;

        gameOverDialog = new Dialog(this, R.style.Theme_AppCompat_Light_NoActionBar);
        gameOverDialog.setContentView(R.layout.game_over);

        punkte = 0;
        leben = ANZAHL_LEBEN;

        way_X = wegEinteilen();

        Background_Hight = (int)Math.round((way_X[1] - way_X[0]) *3 );
        AnzahlBackPic = (dp_height / Background_Hight) + 2;

        backgroundLEFT = new ImageView[AnzahlBackPic];
        backgroundRIGHT = new ImageView[AnzahlBackPic];
        backgroundWay = new ImageView[AnzahlBackPic];
        bonus_money = new ImageView[ANZAHL_MONEY];
        obstaces = new ImageView[ANZAHL_OBSTACES];
        index_draussen_obstaces = new int[ANZAHL_OBSTACES];

        for (int i = 0; i < ANZAHL_OBSTACES; i++)
        {
            index_draussen_obstaces[i] = 0;
        }
        createBackground();

        tv = (TextView)findViewById(R.id.tv_Leben);
        tv.setText(Integer.toString(ANZAHL_LEBEN));

        //LINKS
        Button cmdLeft = (Button)findViewById(R.id.cmdLeft);
        cmdLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRight = false;
                goLeft = false;
                IMG_UserLEFT();
            }
        });
        cmdLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                goLeft = true;
                goRight = false;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IMG_UserLEFT();
                        if (goLeft)
                        {
                            handler.postDelayed(this, 50);
                        }
                    }
                }, 50);

                return false;
            }
        });
        //RECHTS
        Button cmdRight = (Button)findViewById(R.id.cmdRight);
        cmdRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_UserRIGHT();
                goRight = false;
                goLeft = false;
            }
        });
        cmdRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                goRight = true;
                goLeft = false;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IMG_UserRIGHT();
                        if (goRight)
                        {
                            handler.postDelayed(this, 50);
                        }
                    }
                }, 50);
                return false;
            }
        });
        //PAUSE
        Button cmdPause = (Button)findViewById(R.id.cmd_Pause);
        cmdPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        Button cmdMenu = (Button)gameOverDialog.findViewById(R.id.cmd_Menue);
        cmdMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoMenu();
                gameOverDialog.hide();
            }
        });

        Button cmdRestart = (Button)gameOverDialog.findViewById(R.id.cmd_restart);
        cmdRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neustart();
                gameOverDialog.hide();
            }
        });

        FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) cmdRight.getLayoutParams();
        params.width = dp_width / 2;
        cmdRight.setLayoutParams(params);

        params = (android.widget.FrameLayout.LayoutParams) cmdLeft.getLayoutParams();
        params.width = dp_width / 2;
        cmdLeft.setLayoutParams(params);

        TextView tvPOINT = findViewById(R.id.tv_Points);
        tvPOINT.setText(Integer.toString(punkte));

        create_Money(true, null);
        create_Obstaces();
        create_Obstacles_as_wall();
        calc_alk();
        calc_extraLife();
        calc_Diamonds();
    }

    @Override
    public void run() {
        checkBackgroundPNG_Margin();
        MovePNG();
        checkMoney_MAP();
        checkObstacle_MAP();
        checkALK_MAP();
        checkExtraLife_MAP();
        checkDiamonds_MAP();
        makeMoney();
        makeObstaces();
        make_drunk();
        make_extraLife();
        make_Diamonds();
        check_draussen_cnt();

    }

    private void loadStaticEinstellungen() {
        serialize serialize = new serialize();
        static_einstellungen se = (static_einstellungen) serialize.loadSerializedObject(new File("/sdcard/save_einstellungen.bin")); //get the serialized object from the sdcard and caste it into the Person class.

        if (se != null)
        {
            static_einstellungen se2 = new static_einstellungen(se.getGESCHWINDIGKEIT(), se.getANZAHL_MONEY(), se.getANZAHL_OBSTACES(), se.getANZAHL_Y_POINTS(), se.getANZAHL_LEBEN(), se.getMAXIMALE_ANZAHL_COL_OBSTACLE(),
                    se.getMAXIMALE_ANZAHL_ROW_OBSTACLE(), se.getGROESSE_USER_IMG(), se.getABSTAND_SPURLINIE_USER(), se.getGROESSE_MONEY(), se.getABSTAND_SPURLINIE_MONEY(), se.getGROESSE_OBSTACLE(),
                    se.getABSTAND_SPURLINIE_OBSTACLE(), se.getOBSTACLE_PERCENT_FROM_USER(), se.getMONEY_PERCENT_FROM_USER(), se.getFLASHTIME_USR(), se.getFLASHCNT_USR(), se.getLess_hit_size(), se.isVisible());
            setStaticEinstellungen(se2);
        }
        else
        {
            se = new static_einstellungen();
            setStaticEinstellungen(se);
        }

    }

    private void setStaticEinstellungen(static_einstellungen se){
        GESCHWINDIGKEIT = se.getGESCHWINDIGKEIT();
        ANZAHL_MONEY = se.getANZAHL_MONEY();
        ANZAHL_LEBEN = se.getANZAHL_LEBEN();
        MAXIMALE_ANZAHL_COL_OBSTACLE = se.getMAXIMALE_ANZAHL_COL_OBSTACLE();
        MAXIMALE_ANZAHL_ROW_OBSTACLE = se.getMAXIMALE_ANZAHL_ROW_OBSTACLE(); //Startend von 0
        //GROESSE + (ABSTAND * 2) müssen immer 1 ergeben
        GROESSE_USER_IMG = se.getGROESSE_USER_IMG();
        ABSTAND_SPURLINIE_USER = se.getABSTAND_SPURLINIE_USER();
        GROESSE_MONEY = se.getGROESSE_MONEY();
        ABSTAND_SPURLINIE_MONEY = se.getABSTAND_SPURLINIE_MONEY();
        GROESSE_OBSTACLE = se.getGROESSE_OBSTACLE();
        ABSTAND_SPURLINIE_OBSTACLE = se.getABSTAND_SPURLINIE_OBSTACLE();
        OBSTACLE_PERCENT_FROM_USER = se.getOBSTACLE_PERCENT_FROM_USER();//0.75;
        MONEY_PERCENT_FROM_USER = se.getMONEY_PERCENT_FROM_USER();
        FLASHTIME_USR = se.getFLASHTIME_USR();
        FLASHCNT_USR = se.getFLASHCNT_USR();
        less_hit_size = se.getLess_hit_size();
        isVisible_USR = se.isVisible();

    }

    private void pause()
    {
        if (isPause)
        {
            isPause = false;
        }
        else
        {
            isPause = true;
        }
    }

    private void neustart()
    {

        //GAME AUF NULL
        isPause = false;
        punkte = 0;
        leben = ANZAHL_LEBEN;
        Miles = 0;

        tv = (TextView)findViewById(R.id.tv_Leben);
        tv.setText(Integer.toString(leben));
        tv = (TextView)findViewById(R.id.tv_Points);
        tv.setText(Integer.toString(punkte));
        for (int i = 0; i < ANZAHL_OBSTACES; i++)
        {
            index_draussen_obstaces[i] = 0;
        }

        create_Obstacles_as_wall();
    }

    private void backtoMenu()
    {
        finish();
    }

    private void gameOver()
    {
        serialize ser = new serialize();
        if (var_main_properties != null)
        {
            var_main_properties.setPoints(var_main_properties.getPoints() + punkte);
            var_main_properties.setDiamonds(var_main_properties.getDiamonds() + diamonds);
        }
        else
        {
            main_properties prop = (main_properties) ser.loadSerializedObject(new File("/sdcard/save_USRDATA.bin")); //get the serialized object from the sdcard and caste it into the Person class.
            if (prop != null)
            {
                var_main_properties = new main_properties();
                var_main_properties.setDiamonds(prop.getDiamonds() + diamonds);
                var_main_properties.setExp(prop.getExp());
                var_main_properties.setLevel(prop.getLevel());
                var_main_properties.setMap_resource(prop.getMap_resource());
                var_main_properties.setPoints(prop.getPoints() + punkte);
                var_main_properties.setUser(prop.getUser());
                var_main_properties.setVehicel(prop.getVehicel());

            }
        }
        ser.saveObject(var_main_properties, new File("/sdcard/save_USRDATA.bin"));

        checkPersHighscore();

        gameOverDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        gameOverDialog.show();
        isPause = true;

    }

    private void checkPersHighscore()
    {
        serialize ser = new serialize();
        pers_highscore_propertys persHigh = (pers_highscore_propertys)ser.loadSerializedObject(new File("/sdcard/pers_highscore.bin"));

        String save_Name = "";
        int save_km = 0;
        int save_punkte = 0;
        String save_Name2 = "";
        int save_km2 = 0;
        int save_punkte2 = 0;
        boolean d = false;

        if (persHigh != null)
        {
            String[] pn = persHigh.getPlayer_name();
            int[] km = persHigh.getMiles_score();
            int[] punk = persHigh.getMoney_score();
            //check ob überschreiben0
            loop : for (int i = 0; i < 10; i++){
                if (d){
                    save_Name2 = pn[i];
                    save_km2 = km[i];
                    save_punkte2 = punk[i];

                    pn[i] = save_Name;
                    km[i] = save_km;
                    punk[i] = save_punkte;

                    save_Name = save_Name2;
                    save_km = save_km2;
                    save_punkte = save_punkte2;
                }
                if (persHigh.getMiles_score()[i] < this.Miles && !d)
                {
                    save_Name = pn[i];
                    save_km = km[i];
                    save_punkte = punk[i];
                    d = true;

                    pn[i] = playerName;
                    km[i] = this.Miles;
                    punk[i] = this.punkte;
                }
            }

            persHigh.setPlayer_name(pn);
            persHigh.setMiles_score(km);
            persHigh.setMoney_score(punk);
        }
        else
        {
            String[] pn = new String[10];
            pn[0] = playerName;
            int[] place = new int[10];
            place[0] = 1;
            int[] km = new int[10];
            km[0] = this.Miles;
            int[] punk = new int[10];
            punk[0] = this.punkte;
            for (int i = 1; i < 10; i++){
                pn[i] = "";
                place[i] = i+1;
                km[i] = 0;
                punk[i] = 0;
            }
            persHigh = new pers_highscore_propertys();
            persHigh.setPlayer_name(pn);
            persHigh.setMiles_score(km);
            persHigh.setMoney_score(punk);
            persHigh.setPlace(place);
        }

        ser.saveObject(persHigh, new File("/sdcard/pers_highscore.bin"));


    }

    //##############################################################################################
    //                                       MONEY
    //##############################################################################################


    private void create_Money(boolean neu, ImageView v)
    {
        int x = spurEinteilen(true);
        FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

        int ab = (int)Math.round(paramsUSER.width * GROESSE_MONEY);
        //int ab = (int)Math.round((wegEinteilen()[1] - wegEinteilen()[0]) * GROESSE_MONEY);

        FrameLayout frame2 = findViewById(R.id.frameUSER);

        if (neu)
        {
            for (int i = 0; i < ANZAHL_MONEY; i++)
            {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab,ab);
                params.leftMargin = x;
                Random rnd_Y = new Random();
                int Y = rnd_Y.nextInt(ANZAHL_Y_POINTS);
                params.topMargin = Y;

                bonus_money[i] = new ImageView(this);
                bonus_money[i].setImageResource(R.drawable.dollar);
                bonus_money[i].setScaleType(ImageView.ScaleType.FIT_XY);

                frame2.addView(bonus_money[i], params);
            }
        }
        else
        {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab,ab);
            params.leftMargin = x;
            Random rnd_Y = new Random();
            int Y = rnd_Y.nextInt(ANZAHL_Y_POINTS);

            params.topMargin = y_Einteilung(true)[Y];
            v.setLayoutParams(params);
        }
    }

    private void makeMoney(){

        //Wenn außerhalb des spielfeld ist => WEG
        //Wenn vom spieler eingefangen => WEG + PUNKTE
        for (int i = 0; i < ANZAHL_MONEY; i++)
        {
            if (User_hit(bonus_money[i]))
            {
                punkte += punkte_add;

                TextView tvPOINT = findViewById(R.id.tv_Points);
                tvPOINT.setText(Integer.toString(punkte));
                //KOORDINATEN ÄNDERN DAS AUF 0 und wieder kommen
                create_Money(false, bonus_money[i]);

            }
        }

    }

    private void checkMoney_MAP()
    {

        for (int i = 0; i < ANZAHL_MONEY; i++)
        {
            FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) bonus_money[i].getLayoutParams();

            if (params.topMargin >= dp_height)
            {
                /*Random rnd = new Random();
                int y = rnd.nextInt(ANZAHL_Y_POINTS);
                params.topMargin = y_Einteilung()[y];
                bonus_money[i].setLayoutParams(params);*/
                create_Money(false, bonus_money[i]);

            }

        }
    }

    //##############################################################################################
    //                                       JÄGERMEISTER
    //##############################################################################################

    private void calc_alk() {

        Random rnd = new Random();
        ALK_MILES = rnd.nextInt(max_ALK_Miles - min_ALK_Miles) + min_ALK_Miles;
        ALK_MILES += Miles;

    }

    private void create_ALK(int x, int y) {
        FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

        int ab = (int) Math.round(paramsUSER.width * GROESSE_OBSTACLE);

        FrameLayout frame2 = findViewById(R.id.frameUSER);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab/2, ab);
        params.topMargin = y;
        params.leftMargin = x;

        iv_ALK = new ImageView(this);
        iv_ALK.setImageResource(R.drawable.alk);
        iv_ALK.setScaleType(ImageView.ScaleType.FIT_XY);

        frame2.addView(iv_ALK, params);
        move_ALK = true;
    }

    private void checkALK_MAP()
    {
            if (iv_ALK != null)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ALK.getLayoutParams();

                if (params.topMargin >= dp_height)
                {
                    move_ALK = false;
                }
            }
    }

    private void make_drunk()
    {
        if (isVisible_USR)
        {
            if (User_hit(iv_ALK))
            {
                cnt_hits_alk++;
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ALK.getLayoutParams();
                params.topMargin = dp_height;
                iv_ALK.setLayoutParams(params);
                move_ALK = false;

                GESCHWINDIGKEIT = Math.round(GESCHWINDIGKEIT * 2);
                punkte_add = punkte_add * 2;

                drunking_Handler();
            }
        }

    }

    private void drunking_Handler()
    {
        if (drunk_cnt >= (drunk_interval + cnt_hits_alk))
        {
            GESCHWINDIGKEIT = GESCHWINDIGKEIT / 2;
            punkte_add = punkte_add / 2;
            FrameLayout frame2 = findViewById(R.id.frameUSER);
            frame2.setBackgroundResource(R.color.Transparent);
            drunk_cnt = 0;
        }
        else
        {
            Handler DrunkHandler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int timeToBlink = drunk_dauer;    //in milissegunds
                    try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            FrameLayout frame2 = findViewById(R.id.frameUSER);

                           if (drunkFlash)
                           {
                               frame2.setBackgroundResource(R.color.DrunkColor1);
                               drunkFlash = false;
                           }
                           else
                           {
                               frame2.setBackgroundResource(R.color.DrunkColor2);
                               drunkFlash = true;
                           }

                            drunk_cnt++;
                            drunking_Handler();
                        }
                    });
                }
            }).start();
        }
    }

    //##############################################################################################
    //                                       EXTRA LEBEN
    //##############################################################################################

    private void calc_extraLife() {

        Random rnd = new Random();
        ExtraLife_MILES = rnd.nextInt(max_ExtraLife_Miles - min_ExtraLife_Miles) + min_ExtraLife_Miles;
        ExtraLife_MILES += Miles;

    }

    private void create_ExtraLife(int x, int y) {
        FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

        int ab = (int) Math.round(paramsUSER.width * GROESSE_OBSTACLE);

        FrameLayout frame2 = findViewById(R.id.frameUSER);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab, ab);
        params.topMargin = y;
        params.leftMargin = x;

        iv_ExtraLife = new ImageView(this);
        iv_ExtraLife.setImageResource(R.drawable.heart);
        iv_ExtraLife.setScaleType(ImageView.ScaleType.FIT_XY);

        frame2.addView(iv_ExtraLife, params);
        move_ExtraLife = true;
    }

    private void checkExtraLife_MAP()
    {
        if (iv_ExtraLife != null)
        {
            FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ExtraLife.getLayoutParams();

            if (params.topMargin >= dp_height)
            {
                move_ExtraLife = false;
            }
        }
    }

    private void make_extraLife()
    {
        if (isVisible_USR)
        {
            if (User_hit(iv_ExtraLife))
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ExtraLife.getLayoutParams();
                params.topMargin = dp_height;
                iv_ExtraLife.setLayoutParams(params);
                move_ExtraLife = false;

                leben++;
                tv = findViewById(R.id.tv_Leben);
                tv.setText(Integer.toString(leben));
            }
        }

    }


    //##############################################################################################
    //                                       DIAMONDS
    //##############################################################################################

    private void calc_Diamonds() {

        Random rnd = new Random();
        Diamonds_MILES = rnd.nextInt(max_Diamonds_Miles - min_Diamonds_Miles) + min_Diamonds_Miles;
        Diamonds_MILES += Miles;

    }

    private void create_Diamonds(int x, int y) {
        FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

        int ab = (int) Math.round(paramsUSER.width * GROESSE_OBSTACLE);

        FrameLayout frame2 = findViewById(R.id.frameUSER);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab, ab);
        params.topMargin = y;
        params.leftMargin = x;

        iv_Diamonds = new ImageView(this);
        iv_Diamonds.setImageResource(R.drawable.diamonds);
        iv_Diamonds.setScaleType(ImageView.ScaleType.FIT_XY);

        frame2.addView(iv_Diamonds, params);
        move_Diamonds = true;
    }

    private void checkDiamonds_MAP()
    {
        if (iv_Diamonds != null)
        {
            FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_Diamonds.getLayoutParams();

            if (params.topMargin >= dp_height)
            {
                move_Diamonds = false;
            }
        }
    }

    private void make_Diamonds()
    {
        if (isVisible_USR)
        {
            if (User_hit(iv_Diamonds))
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_Diamonds.getLayoutParams();
                params.topMargin = dp_height;
                iv_Diamonds.setLayoutParams(params);
                move_Diamonds = false;

                diamonds++;
                tv = findViewById(R.id.tv_diamonds);
                tv.setText(Integer.toString(diamonds));
            }
        }

    }

    //##############################################################################################
    //                                       HINDERNISSE
    //##############################################################################################

    private void create_Obstaces()
    {
        FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

        int ab = (int)Math.round(paramsUSER.width * GROESSE_OBSTACLE);

        FrameLayout frame2 = findViewById(R.id.frameUSER);

        for (int i = 0; i < ANZAHL_OBSTACES; i++) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ab, ab);
            //params.leftMargin = x;
            params.topMargin = dp_height;

            obstaces[i] = new ImageView(this);
            obstaces[i].setImageResource(R.drawable.test_klein);
            obstaces[i].setScaleType(ImageView.ScaleType.FIT_XY);
            obstaces[i].setTag(R.id.id, i);

            frame2.addView(obstaces[i], params);
        }
    }

    private void makeObstaces(){

        //Wenn außerhalb des spielfeld ist => WEG
        //Wenn vom spieler eingefangen => WEG + PUNKTE
        if (isVisible_USR)
        {
            loop : for (int i = 0; i < ANZAHL_OBSTACES; i++)
            {
                if (User_hit(obstaces[i]))
                {

                    leben--;
                    TextView tvLeben = (TextView)findViewById(R.id.tv_Leben);
                    tvLeben.setText(Integer.toString(leben));
                    if (leben == 0)
                    {
                        //GAME OVER
                        gameOver();
                    }

                    //KOORDINATEN ÄNDERN DAS AUF 0 und wieder kommen
                    //create_Obstaces(false, obstaces[i]);
                    FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();
                    params.topMargin = dp_height;
                    obstaces[i].setLayoutParams(params);

                    add_Index_obstaces_draussen(i);

                    GESCHWINDIGKEIT = Math.round(GESCHWINDIGKEIT / 2);
                    isVisible_USR = false;
                    flashUSER();

                    break loop;
                }
            }
        }
    }

    private void checkObstacle_MAP()
    {
        for (int i = 0; i < ANZAHL_OBSTACES; i++)
        {
            if (obstaces[i] != null)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();

                if (params.topMargin >= dp_height)
                {
                    add_Index_obstaces_draussen(i);
                }
            }
        }
    }

    private void check_draussen_cnt()
    {
        /*if (obstaces_random_grp_count == 0)
        {
            //erstell neuen random
            Random rnd = new Random();
            obstaces_random_grp_count = 6;//rnd.nextInt(ANZAHL_OBSTACES);
        }
        else
        {*/
        if (isok)
        {
            int x = 0;
            loop : for (int i = 0; i < ANZAHL_OBSTACES; i++)
            {
                if (index_draussen_obstaces[i] != -1)
                {
                    x++;
                    if (MAXIMALE_ANZAHL_ROW_OBSTACLE -1 == x)
                    {
                        add_Obstacle_row();
                        //create_Obstacles_on_Fleck(obstaces_random_grp_count);
                        //obstaces_random_grp_count = 0;
                        break loop;
                    }
                }
            }
        }
        //}
    }

    private void add_Index_obstaces_draussen(int index)
    {
        index_draussen_obstaces[index] = index;
    }

    private void create_Obstacles_as_wall()
    {
        Random rnd = new Random();

        int row_cnt = MAXIMALE_ANZAHL_ROW_OBSTACLE;
        int row_pos; // WO 1 FREE IS
        row_pos = rnd.nextInt(MAXIMALE_ANZAHL_ROW_OBSTACLE);

        int[] x = spurEinteilen_Obstacle(row_cnt);
        int col_number = 0; // COL
        int r = 0; //ROW

        int[] y = y_Einteilung(false);

        loop : for (int i = 0; i < ANZAHL_OBSTACES; i++)
        {
            if (index_draussen_obstaces[i] != -1) //<- NICHT DRIN
            {

                if (r == row_cnt) {
                    //k++;
                    row_pos = rnd.nextInt(MAXIMALE_ANZAHL_ROW_OBSTACLE);
                    r = 0;
                    col_number++;
                }
                if (r != row_pos)
                {
                    FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();
                    params.leftMargin = x[r];
                    params.topMargin = y[col_number];


                    index_draussen_obstaces[i] = -1; //<- DRIN
                    obstaces[i].setLayoutParams(params);

                }
                else
                {
                    i--;
                }
                r++;
            }

        }
        isok = true;
    }

    private void add_Obstacle_row()
    {
        Random rnd = new Random();

        int row_cnt = MAXIMALE_ANZAHL_ROW_OBSTACLE;
        int row_pos; // WO 1 FREE IS
        row_pos = rnd.nextInt(MAXIMALE_ANZAHL_ROW_OBSTACLE);

        int[] x = spurEinteilen_Obstacle(row_cnt);
        int r = 0; //ROW

        int[] y = y_Einteilung(false);


        loop : for (int i = 0; i < ANZAHL_OBSTACES; i++)
        {
            if (index_draussen_obstaces[i] != -1) //<- NICHT DRIN
            {
                if (r == row_cnt) {
                    row_pos = rnd.nextInt(MAXIMALE_ANZAHL_ROW_OBSTACLE);
                    r = 0;
                }
                if (r != row_pos)
                {
                    FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();
                    params.leftMargin = x[r];
                    params.topMargin = y[ANZAHL_Y_POINTS -2];

                    index_draussen_obstaces[i] = -1; //<- DRIN
                    obstaces[i].setLayoutParams(params);

                }
                else if(ALK_MILES <= Miles && !move_ALK)
                {
                    create_ALK(x[r], y[ANZAHL_Y_POINTS - 2]);
                    calc_alk();
                    i--;
                }
                else if(ExtraLife_MILES <= Miles && !move_ExtraLife)
                {
                    create_ExtraLife(x[r], y[ANZAHL_Y_POINTS - 2]);
                    calc_extraLife();
                    i--;
                }
                else if(Diamonds_MILES <= Miles && !move_Diamonds)
                {
                    create_Diamonds(x[r], y[ANZAHL_Y_POINTS - 2]);
                    calc_Diamonds();
                    i--;
                }
                else
                {
                    i--;
                }
                r++;
            }

        }
    }

    private void flashUSER()
    {
        if (cnt_flashingUSR >= FLASHCNT_USR)
        {
            if (imgUSER.getVisibility() == View.INVISIBLE)
            {
                imgUSER.setVisibility(View.VISIBLE);
            }
            cnt_flashingUSR = 0;
            isVisible_USR = true;
            GESCHWINDIGKEIT = GESCHWINDIGKEIT * 2;
        }
        else
        {
            Handler flashHandler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int timeToBlink = FLASHTIME_USR;    //in milissegunds
                    try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (imgUSER.getVisibility() == View.VISIBLE)
                            {
                                imgUSER.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                imgUSER.setVisibility(View.VISIBLE);
                            }
                            cnt_flashingUSR++;
                            flashUSER();
                        }
                    });
                }
            }).start();
        }
    }

    private int[] spurEinteilen_Obstacle(int row_cnt)
    {
        int[] x = new int[row_cnt];
        int k = 0;

        int ab2 = (int)Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_OBSTACLE);

        for (int i = 0; i < row_cnt; i++)
        {
            x[k] = way_X[i] + ab2;
            k++;
        }

        return x;
    }

    private int aufrunden(float a, int row, double max_cnt)
    {
        int d = Math.round(a);
        int e = Math.round(d * row);

        if (max_cnt != e || d == 0)
        {
            d += 1;
        }

        return d;
    }

    //##############################################################################################
    //                             EINSTELLUNGEN MONEY & HINDERNISSE
    //##############################################################################################

    private int spurEinteilen(boolean money)
    {
        Random rnd_spur = new Random();
        int spur = rnd_spur.nextInt(MAXIMALE_ANZAHL_ROW_OBSTACLE);
        int x = 0;

        int ab2;

        if (money) {
            ab2 = (int) Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_MONEY);
        } else {
            ab2 = (int)Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_OBSTACLE);
        }

        for (int i = 0; i < MAXIMALE_ANZAHL_ROW_OBSTACLE; i++)
        {
            if (spur == i)
            {
                x = way_X[i] + ab2;
            }
        }

        return x;
    }

    private int[] wegEinteilen()
    {
        int x = calcWidth_BackPNG(true);
        int x2 = calcWidth_BackPNG(false);
        int width_way = x2 - x;
        int teil_way = width_way / MAXIMALE_ANZAHL_ROW_OBSTACLE +1;
        int way_x[] = new int[MAXIMALE_ANZAHL_ROW_OBSTACLE];

        for (int i = 0; i < MAXIMALE_ANZAHL_ROW_OBSTACLE; i++)
        {
            way_x[i] = x + i * teil_way;
        }

        return way_x;
    }

    private int[] y_Einteilung(boolean money)
    {
        int[] y = new int[ANZAHL_Y_POINTS];

        int ab;

        FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();


        if (money)
        {
            ab = (int)Math.round(params.width * MONEY_PERCENT_FROM_USER);
            for (int i = 0; i < ANZAHL_Y_POINTS; i++)
            {
                y[i] = -ab * (i +1);
            }
        }
        else
        {
            ab = (int)Math.round(params.width * OBSTACLE_PERCENT_FROM_USER);
            for (int i = 0; i < ANZAHL_Y_POINTS; i++)
            {
                y[i] =  dp_height -ab -ab * (i +1);
            }
        }

        return y;
    }

    private boolean User_hit(ImageView v)
    {
        if (v != null)
        {
            //WENN USER AUF GELD/ HINDERNIS oder sonstiges trifft kann man hier abfragen
            FrameLayout.LayoutParams paramsUSER = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();
            FrameLayout.LayoutParams paramsV = (android.widget.FrameLayout.LayoutParams) v.getLayoutParams();

            double x1_USER = paramsUSER.leftMargin;
            double x2_USER = (paramsUSER.leftMargin + paramsUSER.width);
            double x1_V = paramsV.leftMargin;
            double x2_V = (paramsV.leftMargin + paramsV.width);

            if (paramsUSER.topMargin <= (paramsV.topMargin + paramsV.height) && (paramsUSER.topMargin + paramsUSER.height)   >= paramsV.topMargin)
            {
                if (x1_USER <= x1_V && x2_USER >= x1_V || x1_USER <= x2_V && x2_USER >= x2_V)
                {
                    //HIT
                    return true;
                }
            }
        }
        return false; // NO HIT
    }

    private boolean ueberlappung(FrameLayout.LayoutParams paramsV)
    {
        if (paramsV != null)
        {
            int x1_V = paramsV.leftMargin;
            int x2_V = paramsV.leftMargin + paramsV.width;

            for (int i = 0; i < ANZAHL_OBSTACES; i++) {
                if (index_draussen_obstaces[i] == -1)
                {
                    FrameLayout.LayoutParams paramsV2 = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();
                    int x1_USER = paramsV2.leftMargin;
                    int x2_USER = paramsV2.leftMargin + paramsV2.width;

                    if (paramsV2.topMargin <= paramsV.topMargin + paramsV.height && paramsV2.topMargin >= paramsV.topMargin ||
                            paramsV.topMargin <= paramsV2.topMargin + paramsV2.height && paramsV.topMargin >= paramsV2.topMargin) {
                        if (x1_USER <= x1_V && x2_USER >= x1_V || x1_USER <= x2_V && x2_USER >= x2_V) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean check_col_space(FrameLayout.LayoutParams paramsV)
    {
        if (paramsV != null)
        {
            int cnt = 0;//ANZAHL DER HITS IN DER REIHE, WENN MAX = CNT dann is zu viel
            for (int i = 0; i < ANZAHL_OBSTACES; i++) {
                if (index_draussen_obstaces[i] == -1)
                {
                    FrameLayout.LayoutParams paramsV2 = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();

                    if (paramsV2.topMargin <= paramsV.topMargin + paramsV.height && paramsV2.topMargin >= paramsV.topMargin ||
                            paramsV.topMargin <= paramsV2.topMargin + paramsV2.height && paramsV.topMargin >= paramsV2.topMargin) {
                        cnt++;
                        if (cnt == MAXIMALE_ANZAHL_ROW_OBSTACLE - 1)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    //##############################################################################################
    //                                  BEWEGUNG DER BILDER
    //##############################################################################################

    private void MovePNG()
    {
        if (!isPause)
        {
            for (int i = 0; i < AnzahlBackPic; i++)
            {
                FrameLayout.LayoutParams paramsLEFT = (android.widget.FrameLayout.LayoutParams) backgroundLEFT[i].getLayoutParams();
                paramsLEFT.topMargin += GESCHWINDIGKEIT;
                backgroundLEFT[i].setLayoutParams(paramsLEFT);

                FrameLayout.LayoutParams paramsRIGHT = (android.widget.FrameLayout.LayoutParams) backgroundRIGHT[i].getLayoutParams();
                paramsRIGHT.topMargin += GESCHWINDIGKEIT;
                backgroundRIGHT[i].setLayoutParams(paramsRIGHT);

                FrameLayout.LayoutParams paramsWEG = (android.widget.FrameLayout.LayoutParams) backgroundWay[i].getLayoutParams();
                paramsWEG.topMargin += GESCHWINDIGKEIT;
                backgroundWay[i].setLayoutParams(paramsWEG);
            }


            for (int i = 0; i < ANZAHL_MONEY; i++)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) bonus_money[i].getLayoutParams();
                params.topMargin += GESCHWINDIGKEIT;
                bonus_money[i].setLayoutParams(params);
            }

            for (int i = 0; i < ANZAHL_OBSTACES; i++)
            {
                if (obstaces[i] != null)
                {
                    FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) obstaces[i].getLayoutParams();
                    params.topMargin += GESCHWINDIGKEIT;
                    obstaces[i].setLayoutParams(params);
                }
            }

            if(move_ALK)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ALK.getLayoutParams();
                params.topMargin += GESCHWINDIGKEIT;
                iv_ALK.setLayoutParams(params);
            }
            if (move_ExtraLife)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_ExtraLife.getLayoutParams();
                params.topMargin += GESCHWINDIGKEIT;
                iv_ExtraLife.setLayoutParams(params);
            }
            if (move_Diamonds)
            {
                FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) iv_Diamonds.getLayoutParams();
                params.topMargin += GESCHWINDIGKEIT;
                iv_Diamonds.setLayoutParams(params);
            }

            Miles ++;
            tv = (TextView)findViewById(R.id.tv_miles);
            tv.setText(Integer.toString(Miles) + " km");
        }
        handler.postDelayed(this, 100);
    }

    // #############################################################################################
    //                                 HINTERGRUND UND USER
    //##############################################################################################
    private void checkBackgroundPNG_Margin()
    {
        for (int i = 0; i < AnzahlBackPic; i++)
        {
            FrameLayout.LayoutParams paramsLEFT = (android.widget.FrameLayout.LayoutParams) backgroundLEFT[i].getLayoutParams();
            FrameLayout.LayoutParams paramsRIGHT = (android.widget.FrameLayout.LayoutParams) backgroundRIGHT[i].getLayoutParams();
            FrameLayout.LayoutParams paramsWEG = (android.widget.FrameLayout.LayoutParams) backgroundWay[i].getLayoutParams();

            int ii;
            if (i == 0)
            {
                ii = AnzahlBackPic -1;
            }
            else
            {
                ii = i -1;
            }

            FrameLayout.LayoutParams paramsLEFT2 = (android.widget.FrameLayout.LayoutParams) backgroundLEFT[ii].getLayoutParams();
            FrameLayout.LayoutParams paramsRIGHT2 = (android.widget.FrameLayout.LayoutParams) backgroundRIGHT[ii].getLayoutParams();
            FrameLayout.LayoutParams paramsWEG2 = (android.widget.FrameLayout.LayoutParams) backgroundWay[ii].getLayoutParams();

            if (paramsLEFT.topMargin > dp_height || paramsRIGHT.topMargin > dp_height)
            {
                paramsLEFT.topMargin = paramsLEFT2.topMargin - Background_Hight;
                paramsRIGHT.topMargin = paramsRIGHT2.topMargin - Background_Hight;
                backgroundLEFT[i].setLayoutParams(paramsLEFT);
                backgroundRIGHT[i].setLayoutParams(paramsRIGHT);
            }

            if (paramsWEG.topMargin > dp_height)
            {
                paramsWEG.topMargin = paramsWEG2.topMargin - Background_Hight;
                backgroundWay[i].setLayoutParams(paramsWEG);
            }

        }

    }

    private int calcWidth_BackPNG(boolean side)
    {
        if (side)
        {
            return dp_width * 1/9; //Beiden ränder
        }
        else
        {
            return dp_width * 8/9; //Mittelgang (eig 3/5 aber weil is ja startpunkt der rechten seite darum +1
        }
    }

    private void createBackground()
    {
        if (!isPause)
        {
            frame = (FrameLayout)findViewById(R.id.frame);

            //LINKS
            createBackgroundLEFT();
            //RECHTS
            createBackgroundRIGHT();
            //WEG
            createBackgroundWay();
            //USER
            createUserIMG();

        }
        handler.postDelayed(this, 100);

    }

    private void createBackgroundLEFT()
    {
        for(int i = 0; i < AnzahlBackPic; i++)
        {
            FrameLayout.LayoutParams paramsLEFT = new FrameLayout.LayoutParams(calcWidth_BackPNG( true), Background_Hight);
            paramsLEFT.leftMargin = 0;
            paramsLEFT.topMargin = dp_height - (i+1) * Background_Hight;
            paramsLEFT.gravity = 51;


            backgroundLEFT[i] = new ImageView(this);
            backgroundLEFT[i].setImageResource(R.drawable.tree_background_left);
            backgroundLEFT[i].setScaleType(ImageView.ScaleType.FIT_XY);

            frame.addView(backgroundLEFT[i], paramsLEFT);
        }
    }

    private void createBackgroundRIGHT()
    {

        for(int i = 0; i < AnzahlBackPic; i++)
        {
            FrameLayout.LayoutParams paramsRIGHT = new FrameLayout.LayoutParams(calcWidth_BackPNG( true), Background_Hight);
            paramsRIGHT.leftMargin = calcWidth_BackPNG(false);
            paramsRIGHT.topMargin = dp_height - (i+1) * Background_Hight;
            paramsRIGHT.gravity = 51;

            backgroundRIGHT[i] = new ImageView(this);
            backgroundRIGHT[i].setImageResource(R.drawable.tree_background_right);
            backgroundRIGHT[i].setScaleType(ImageView.ScaleType.FIT_XY);


            frame.addView(backgroundRIGHT[i], paramsRIGHT);
        }
    }

    private void createBackgroundWay()
    {
        for(int i = 0; i < AnzahlBackPic; i++)
        {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(calcWidth_BackPNG(false) - calcWidth_BackPNG(true), Background_Hight);
            params.leftMargin = ((int)Math.round(calcWidth_BackPNG(true))) ;
            params.topMargin = dp_height - (i+1) * Background_Hight;
            params.gravity = 51;

            backgroundWay[i] = new ImageView(this);
            backgroundWay[i].setImageResource(R.drawable.background_4);
            backgroundWay[i].setScaleType(ImageView.ScaleType.FIT_XY);

            frame.addView(backgroundWay[i], params);
        }
    }

    private void createUserIMG()
    {
        FrameLayout frame2 = findViewById(R.id.frameUSER);

        int ab = (int)Math.round((way_X[1] - way_X[0]) * GROESSE_USER_IMG);
        int ab2 = (int)Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_USER);
        FrameLayout.LayoutParams params_imgUSER = new FrameLayout.LayoutParams(ab, ab);
        params_imgUSER.leftMargin = way_X[akt_row_usr] + ab2;
        params_imgUSER.topMargin = dp_height - ab - 150;

        imgUSER = new ImageView(this);
        imgUSER.setImageResource(R.drawable.test_klein);
        imgUSER.setScaleType(ImageView.ScaleType.FIT_XY);

        frame2.addView(imgUSER, params_imgUSER);
    }

    private void IMG_UserLEFT()
    {
        if (!isPause)
        {
            int ab = (int)Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_USER);
            FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

            for (int i = 1; i < MAXIMALE_ANZAHL_ROW_OBSTACLE; i++)
            {
                if (way_X[i] + ab == params.leftMargin)
                {
                    params.leftMargin = way_X[i-1] + ab;
                    akt_row_usr = i-1;
                    imgUSER.setLayoutParams(params);
                    break;
                }
            }
        }
    }

    private void IMG_UserRIGHT() {

        if (!isPause) {
            int ab = (int) Math.round((way_X[1] - way_X[0]) * ABSTAND_SPURLINIE_USER);
            FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) imgUSER.getLayoutParams();

            for (int i = 0; i < MAXIMALE_ANZAHL_ROW_OBSTACLE - 1; i++) {
                if (way_X[i] + ab == params.leftMargin) {
                    params.leftMargin = way_X[i + 1] + ab;
                    akt_row_usr = i + 1;
                    imgUSER.setLayoutParams(params);
                    break;
                }
            }
        }
    }

}
