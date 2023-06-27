package com.example.projeto;

import android.content.Context;

public class CardButton extends androidx.appcompat.widget.AppCompatButton {

    private int id;
    private boolean isMatched;
    private boolean isFlipped;

    public CardButton(Context context, int id) {
        super(context);
        this.id = id;
        this.isMatched = false;
        this.isFlipped = false;
    }

    public int getId() {
        return id;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public void flip() {
        if (isFlipped) {
            flipToBack();
        } else {
            flipToFront();
        }
        isFlipped = !isFlipped;
    }

    public void flipToFront() {
        setBackgroundResource(R.drawable.card_bg);
    }

    public void flipToBack() {
        setBackgroundResource(R.drawable.card_bg);
    }
}
