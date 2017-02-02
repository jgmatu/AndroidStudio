package es.domainjavito.gitexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

import static es.domainjavito.gitexample.R.mipmap.mine;


public class BuscaMinas extends Activity {
    private static String TAG = "Debug Callback() : ";

    private static int ROWS = 8;
    private static int FIELDS = 5;

    private enum STATES {FREE, MINE};
    private STATES[] map;
    private int frees;

    private class MsgBoton implements View.OnClickListener {
        private int pos;
        private final int LIMIT = ROWS * FIELDS - 1;

        public MsgBoton (int npos) {
            pos = npos;
        }

        private boolean isBorder(int numPos) {
            return numPos % FIELDS == 0;
        }

        private int numMines () {
            int mines = 0;

            int upLeft = pos + FIELDS - 1;
            int up = pos + FIELDS;
            int upRigth = pos + FIELDS + 1;
            int left = pos - 1;
            int rigth = pos + 1;
            int downLeft = pos - FIELDS - 1;
            int down = pos - FIELDS;
            int downRigth = pos - FIELDS + 1;


            if (upLeft < LIMIT) {
                if (map[upLeft] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla abajo izq... : " + upLeft);
            }

            if (up < LIMIT) {
                if (map[up] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla abajo... : " + up);
            }

            if (upRigth < LIMIT && !isBorder(upRigth)) {
                if (map[upRigth] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla abajo der... : " + upRigth);
            }

            if (left > 0) {
                if (map[left] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla abajo izq... : " + left);
            }

            if (rigth < LIMIT && !isBorder(rigth)) {
                if (map[rigth] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla der... : " + rigth);
            }

            if (downLeft > 0) {
                if (map[downLeft] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla arriba izq... : " + downLeft);
            }

            if (down > 0) {
                if (map[down] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla arriba... : " + down);
            }

            if (downRigth > 0 && !isBorder(downRigth)) {
                if (map[downRigth] == STATES.MINE) mines++;
                Log.v(TAG, "Mina en casilla arriba der... : " + downRigth);
            }

            Log.v(TAG , "Mines , pos  : " + mines + pos);
            return mines;
        }

        private void squareFrees() {
            ;
        }

        @Override
        public void onClick(View v) {
            ImageButton imgBut = (ImageButton) v;
            int mines = 0;

            if (map[pos] == STATES.FREE) {
                mines = numMines();
                if (mines == 0) squareFrees();
                if (mines == 1) imgBut.setImageResource(R.mipmap.mines1);
                if (mines == 2) imgBut.setImageResource(R.mipmap.mines2);
                if (mines == 3) imgBut.setImageResource(R.mipmap.mines3);
                if (mines == 4) imgBut.setImageResource(R.mipmap.mines4);
                if (mines == 5) imgBut.setImageResource(R.mipmap.mines5);
                if (mines == 6) imgBut.setImageResource(R.mipmap.mines6);
                if (mines == 7) imgBut.setImageResource(R.mipmap.mines7);
                if (mines == 8) imgBut.setImageResource(R.mipmap.mines8);
            } else if (map[pos] == STATES.MINE) {
                imgBut.setImageResource(mine);
            }
        }
    }

    private void createMap() {
        map = new STATES[FIELDS * ROWS];
        Random random = new Random();
        random.setSeed(7);
        frees = 0;

        for (int i = 0 ; i < ROWS ; i++) {
            for (int j = 0 ; j < FIELDS ; j++) {
                if (random.nextLong() % 3 == 0) {
                    map[i * FIELDS + j] = STATES.MINE;
                } else {
                    map[i * FIELDS + j] = STATES.FREE;
                    frees++;
                }
            }
        }
    }

    private void createUI () {
        TableLayout table = (TableLayout) findViewById(R.id.table);

        for (int i = 0 ; i < ROWS ; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lr = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lr);
            for (int j = 0 ; j < FIELDS ; j++) {
                ImageButton imgbut = new ImageButton(this);

                imgbut.setImageResource(R.mipmap.square_green);
                imgbut.setOnClickListener(new MsgBoton(i * FIELDS + j));
            }
            table.addView(row);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mines);
        createMap();
        createUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }
}
