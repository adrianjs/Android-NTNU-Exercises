package pegsolitairecom.adrst.pegsolitaire;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PegLayout extends LinearLayout {

    private int row, col;

    public PegLayout(Context context, int r, int c) {
        super(context);
        row = r;
        col = c;
    }

    public PegLayout(Context context, @Nullable AttributeSet attrs, int r, int c) {
        super(context, attrs);
        row = r;
        col = c;
    }

    public PegLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int r, int c) {
        super(context, attrs, defStyleAttr);
        row = r;
        col = c;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isEmpty() {
        return this.getChildCount() == 0;
    }
}
