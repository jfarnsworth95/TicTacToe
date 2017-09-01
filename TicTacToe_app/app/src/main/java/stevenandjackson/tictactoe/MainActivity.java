package stevenandjackson.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GameCore gc;

    Button tl;
    Button tm;
    Button tr;
    Button ml;
    Button m;
    Button mr;
    Button bl;
    Button bm;
    Button br;

    static final int CROSS = 1, CIRCLE =2;

    ArrayList<Button> btnLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLst = new ArrayList<Button>();

        tl = (Button) findViewById(R.id.topLeft);
        btnLst.add(tl);
        tm = (Button) findViewById(R.id.topMid);
        btnLst.add(tm);
        tr = (Button) findViewById(R.id.topRight);
        btnLst.add(tr);
        ml = (Button) findViewById(R.id.midLeft);
        btnLst.add(ml);
        m = (Button) findViewById(R.id.mid);
        btnLst.add(m);
        mr = (Button) findViewById(R.id.midRight);
        btnLst.add(mr);
        bl = (Button) findViewById(R.id.botLeft);
        btnLst.add(bl);
        bm = (Button) findViewById(R.id.botMid);
        btnLst.add(bm);
        br = (Button) findViewById(R.id.botRight);
        btnLst.add(br);

        gc = new GameCore();

    }

    public void setButtons(){
        if(gc.board[0][0] == CROSS){
            tl.setText("X");
            tl.setEnabled(false);
        } else if(gc.board[0][0] == CIRCLE){
            tl.setText("O");
            tl.setEnabled(false);
        }

        if(gc.board[0][1] == CROSS){
            tm.setText("X");
            tm.setEnabled(false);
        } else if(gc.board[0][1] == CIRCLE){
            tm.setText("O");
            tm.setEnabled(false);
        }

        if(gc.board[0][2] == CROSS){
            tr.setText("X");
            tr.setEnabled(false);
        } else if(gc.board[0][2] == CIRCLE){
            tr.setText("O");
            tr.setEnabled(false);
        }

        if(gc.board[1][0] == CROSS){
            ml.setText("X");
            ml.setEnabled(false);
        } else if(gc.board[1][0] == CIRCLE){
            ml.setText("O");
            ml.setEnabled(false);
        }

        if(gc.board[1][1] == CROSS){
            m.setText("X");
            m.setEnabled(false);
        } else if(gc.board[1][1] == CIRCLE){
            m.setText("O");
            m.setEnabled(false);
        }

        if(gc.board[1][2] == CROSS){
            mr.setText("X");
            mr.setEnabled(false);
        } else if(gc.board[1][2] == CIRCLE){
            mr.setText("O");
            mr.setEnabled(false);
        }

        if(gc.board[2][0] == CROSS){
            bl.setText("X");
            bl.setEnabled(false);
        } else if(gc.board[2][0] == CIRCLE){
            bl.setText("O");
            bl.setEnabled(false);
        }

        if(gc.board[2][1] == CROSS){
            bm.setText("X");
            bm.setEnabled(false);
        } else if(gc.board[2][1] == CIRCLE){
            bm.setText("O");
            bm.setEnabled(false);
        }

        if(gc.board[2][2] == CROSS){
            br.setText("X");
            br.setEnabled(false);
        } else if(gc.board[2][2] == CIRCLE){
            br.setText("O");
            br.setEnabled(false);
        }

    }

    public void playerMove(View view){
        String btnPressed = view.getTag().toString();
        if(btnPressed.equals("tl")){
            gc.board[0][0] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("tm")){
            gc.board[0][1] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("tr")){
            gc.board[0][2] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("ml")){
            gc.board[1][0] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("m")){
            gc.board[1][1] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("mr")){
            gc.board[1][2] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("bl")){
            gc.board[2][0] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("bm")){
            gc.board[2][1] = GameCore.CROSS;
            gc.MainProcedure();

        }else if(btnPressed.equals("br")){
            gc.board[2][2] = GameCore.CROSS;
            gc.MainProcedure();

        }else{
            Log.e("PlayerMove","Unknown tag encountered");
        }
        //gc.isPlayerDone = true;
        //gc.t.notify();

        setButtons();

        gc.gameStatus = GameCore.inspectGameStatus(gc.board);

        if (gc.gameStatus == GameCore.AI_WON) {
            Toast.makeText(MainActivity.this, "You Lost", Toast.LENGTH_LONG).show();
            requestReset();
        } else if (gc.gameStatus == GameCore.PLAYER_WON) {
            Toast.makeText(MainActivity.this, "You Won", Toast.LENGTH_LONG).show();
            requestReset();
        } else if (gc.gameStatus == GameCore.DRAW) {
            Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_LONG).show();
            requestReset();
        }
    }

    public void requestReset(){
        Log.i("Request", "About to send request to restart");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("Request", "AlertDialog being built");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // set title
                alertDialogBuilder.setTitle("New Game?");
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Restart",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                reset();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close the dialog box and do nothing
                                finishAndRemoveTask();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        }, 3000);
    }

    public void reset(){
        gc = new GameCore();
        for(int i = 0; i < btnLst.size(); i++){
            btnLst.get(i).setText("");
            btnLst.get(i).setEnabled(true);
        }
    }
}
