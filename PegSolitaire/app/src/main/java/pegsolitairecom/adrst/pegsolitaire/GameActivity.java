package pegsolitairecom.adrst.pegsolitaire;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private Drawable defaultSquare;

    private TableLayout gameTableLayout;
    private PegLayout[][] squares = new PegLayout[7][7];
    private TableRow[] row = new TableRow[7];
    private PegView[][] pieces = new PegView[7][7];
    private TextView scoreView;

    private int score = 32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        defaultSquare = getResources().getDrawable(R.drawable.square);
        gameTableLayout = findViewById(R.id.game_table_layout);

        int height = dpToPixels(45);
        int width = dpToPixels(45);

        /**
         * Loops through rows and columns to create TableRows and the Views inside
         * them, Sets rows and columns of PegLayouts and PegViews
         */
        for (int r = 0; r < 7; r++) {
            row[r] = new TableRow(this);
            for (int c = 0; c < 7; c++) {
                if (!((r == 0 || r == 1 || r == 5 || r == 6) &&
                        (c == 0 || c == 1 || c == 5 || c == 6))) {
                    squares[r][c] = new PegLayout(this, r, c);
                    squares[r][c].setBackground(defaultSquare);
                    squares[r][c].setOnDragListener(new SquareDragListener());
                    if (!((r == 3) && (c == 3))) {
                        pieces[r][c] = new PegView(this, r, c);
                        pieces[r][c].setImageResource(R.drawable.peg);
                        pieces[r][c].setLayoutParams(new TableLayout.LayoutParams(height, width));
                        pieces[r][c].setOnTouchListener(new PegTouchListener());
                        squares[r][c].addView(pieces[r][c]);
                    }
                    row[r].addView(squares[r][c]);
                    TableRow.LayoutParams params = (TableRow.LayoutParams) squares[r][c].getLayoutParams();
                    params.column = c;
                    params.height = height;
                    params.width = width;
                    squares[r][c].setLayoutParams(params);
                }
            }
            gameTableLayout.addView(row[r], new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        }
        scoreView = findViewById(R.id.textview_score);
        scoreView.setText(Integer.toString(getScore()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    /**
     * Function for converting dp to pixels. Needed to set height and width.
     *
     * @param dps the amount of dp to be converted
     * @return the pixel value of the dp parameter
     */
    public int dpToPixels(int dps) {
        float scale = getBaseContext().getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    /**
     * Getter for array of PegLayouts which make up the board
     *
     * @return squares
     */
    public PegLayout[][] getSquares() {
        return squares;
    }

    /**
     * Setter for score
     *
     * @param s the score amount to be set
     */
    public void setScore(int s) {
        score = s;
    }

    /**
     * Getter for score
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    public void updateScoreView() {
        int score = getScore();
        if (score == 1) {
            openDialog();
        }
        scoreView.setText(Integer.toString(getScore()));
    }

    public void onClickFinish(View view) {
        openDialog();
    }

    /**
     * Sets up the dialog box and related listener for when the game is finished.
     */
    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.game_over);
        builder.setMessage(String.format(getResources().getString(R.string.score_text), getScore()));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameActivity.this.finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private final class PegTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(null, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
              return false;
            }
        }
    }

    class SquareDragListener implements View.OnDragListener {
        Drawable defaultSquare = getResources().getDrawable(R.drawable.square);
        Drawable hoverSquare = getResources().getDrawable(R.drawable.square_hover);

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            PegView pegView;
            PegLayout oldSquare;

            switch (dragEvent.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view.setBackground(hoverSquare);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.setBackground(defaultSquare);
                    break;
                case DragEvent.ACTION_DROP:
                    // When peg is dropped, move() is called and score is updated
                    pegView = (PegView) dragEvent.getLocalState();
                    PegLayout newSquare = (PegLayout) view;
                    oldSquare = (PegLayout) pegView.getParent();
                    if (pegView.move(oldSquare, newSquare, getSquares())) {
                        int score = getScore();
                        score--;
                        setScore(score);
                        updateScoreView();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    pegView = (PegView) dragEvent.getLocalState();
                    pegView.setVisibility(View.INVISIBLE);
                    view.setBackground(defaultSquare);
                default:
                    break;
            }
            return true;
        }
    }
}
