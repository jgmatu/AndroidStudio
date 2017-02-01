package es.domainjavito.gitexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class BuscaMinas extends Activity {

    private static String TAG = "Debug Callback() : ";

    private static int ROWS = 8;
    private static int FIELDS = 5;

    private class MsgBoton implements View.OnClickListener {
        private int pos;
        private int state;

        public MsgBoton (int npos) {
            state = 0;
            pos = npos;
        }

        @Override
        public void onClick(View v) {
            Log.v(TAG , "Button pressed...");
            Log.v(TAG , String.valueOf(pos));
            ImageButton imgBut = (ImageButton) v;
            imgBut.setImageResource(R.mipmap.square_yellow);
        }
    }

    private ImageButton createBut(int pos) {
        ImageButton imgbut = new ImageButton(this); // Click listener... y luego add..

        imgbut.setImageResource(R.mipmap.square_green);
        imgbut.setOnClickListener(new MsgBoton(pos));

        return imgbut;
    }

    private TableRow createRows (int nrow) {
        TableRow row = new TableRow(this);

        TableRow.LayoutParams lr = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);

        row.setLayoutParams(lr);
        for (int j = 0 ; j < FIELDS ; j++) {
            row.addView(createBut(nrow * FIELDS + j));
        }
        return row;
    }

    private void createUI () {
        TableLayout table = (TableLayout) findViewById(R.id.table);

        for (int nrow = 0 ; nrow < ROWS ; nrow++) {
            table.addView(createRows(nrow));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git);
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
