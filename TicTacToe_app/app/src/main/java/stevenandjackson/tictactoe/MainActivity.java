package stevenandjackson.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tl = (Button) findViewById(R.id.topLeft);
        tm = (Button) findViewById(R.id.topMid);
        tr = (Button) findViewById(R.id.topRight);
        ml = (Button) findViewById(R.id.midLeft);
        m = (Button) findViewById(R.id.mid);
        mr = (Button) findViewById(R.id.midRight);
        bl = (Button) findViewById(R.id.botLeft);
        bm = (Button) findViewById(R.id.botMid);
        br = (Button) findViewById(R.id.botRight);

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

        if (gc.gameStatus == GameCore.AI_WON) {
            Toast.makeText(MainActivity.this, "You Lost", Toast.LENGTH_LONG).show();
        } else if (gc.gameStatus == GameCore.PLAYER_WON) {
            Toast.makeText(MainActivity.this, "You Won", Toast.LENGTH_LONG).show();
        } else if (gc.gameStatus == GameCore.DRAW) {
            Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_LONG).show();
        }
    }
}
