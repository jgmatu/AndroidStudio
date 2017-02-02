package es.domainjavito.gitexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;


public class BuscaMinas extends Activity {
    private static String TAG = "Debug Callback() : ";

    private static int ROWS = 8;
    private static int FIELDS = 5;
    private static int EASY = 4;

    private boolean[][] map;
    private int frees;

    private class Casilla {
        private int row;
        private int field;
        private ImageButton imgBut;
        private boolean ismine;

        public Casilla (int nrow , int nfield , boolean state) {
            row = nrow;
            field = nfield;
            ismine = state;
        }

        public ImageButton getImgBut() {
            return imgBut;
        }

        public int getRow() {
            return row;
        }

        public int getField() {
            return field;
        }

        public boolean ismine() {
            return ismine;
        }

        private String printState() {
            String txt;

            if (ismine) {
                txt = String.format("Mine!");
            } else {
                txt = String.format("Not mine");
            }
            return txt;
        }

        @Override
        public String toString() {
            return String.format("%s : (%d , %d)", printState() , row , field);
        }
    }

    private class MsgBoton implements View.OnClickListener {
        private Casilla casilla;

        public MsgBoton (ImageButton imagebut ,Casilla c) {
            casilla = c;
        }

        private boolean isCasilla(int i , int j) {
            return i == 0 && j == 0;
        }

        private boolean isLimit(Casilla c , int row , int field){
            return c.getRow() + row >= ROWS || c.getField() + field >= FIELDS ||
                    c.getField() + field < 0 || c.getRow() + row < 0;
        }

        private int numMines (Casilla c) {
            int mines = 0;

            for (int i = 1; i >= -1 ; i--) {
                for (int j = 1; j >= -1 ; j--) {
                    Log.v(TAG, String.format("Position : (%d , %d)" , c.getRow() + i , c.getField() + j));
                    if (isCasilla(i , j) || isLimit(c, i , j)) {
                        continue;
                    }
                    if (map[c.getRow() + i][c.getField() + j]) {
                        mines++;
                    }
                }
            }
            return mines;
        }

        private boolean[][] initPaint() {
            boolean[][] paint = new boolean[ROWS][FIELDS];

            for (int i = 0 ; i < ROWS ; i++) {
                for (int j = 0 ; j < FIELDS ; j++) {
                    paint[i][j] = false;
                }
            }
            return paint;
        }

        private void squareFrees(Casilla c) {
            boolean[][] paint = initPaint();

            if (isLimit(c , c.getRow() , c.getField()) || c.ismine) {
                return;
            }

            if (numMines(c) > 0) {
                paint[c.getRow()][c.getField()] = true;
                return;
            }
            squareFrees();
        }


        private void setMines (ImageButton imgBut) {
            int mines = numMines(casilla);

            // Dibujo este boton...
            if (mines == 0) squareFrees(casilla);
            if (mines == 1) imgBut.setImageResource(R.mipmap.mines1);
            if (mines == 2) imgBut.setImageResource(R.mipmap.mines2);
            if (mines == 3) imgBut.setImageResource(R.mipmap.mines3);
            if (mines == 4) imgBut.setImageResource(R.mipmap.mines4);
            if (mines == 5) imgBut.setImageResource(R.mipmap.mines5);
            if (mines == 6) imgBut.setImageResource(R.mipmap.mines6);
            if (mines == 7) imgBut.setImageResource(R.mipmap.mines7);
            if (mines == 8) imgBut.setImageResource(R.mipmap.mines8);
        }

        @Override
        public void onClick(View v) {
            ImageButton imgBut = (ImageButton) v;

            if (casilla.ismine()) {
                imgBut.setImageResource(R.mipmap.mine);
            } else {
                setMines(imgBut);
            }
            Log.v(TAG , casilla.toString());
        }
    }

    private boolean[][] createMap() {
        boolean[][] m = new boolean[ROWS][FIELDS];
        Random random = new Random();
        random.setSeed(17);

        for (int i = 0 ; i < ROWS ; i++) {
            for (int j = 0 ; j < FIELDS ; j++) {
                m[i][j] = random.nextLong() % EASY == 0;
            }
        }
        return m;
    }

    private void createUI (boolean[][] m) {
        TableLayout table = (TableLayout) findViewById(R.id.table);

        for (int i = 0 ; i < ROWS ; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lr = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lr);
            for (int j = 0 ; j < FIELDS ; j++) {
                ImageButton imgbut = new ImageButton(this);

                imgbut.setImageResource(R.mipmap.square_green);
                imgbut.setOnClickListener(new MsgBoton(imgbut , new Casilla(i , j , m[i][j])));
                row.addView(imgbut);
            }
            table.addView(row);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mines);
        map = createMap();
        createUI(map);
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
