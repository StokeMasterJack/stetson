package ss.app1.model;

import java.util.Objects;

public class Card1 {

    private int value;
    private int suit;

    public Card1(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card1 card1 = (Card1) o;
        return value == card1.value &&
                suit == card1.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, suit);
    }

    @Override
    public String toString() {
        return "Card1{" +
                "value=" + value +
                ", suit=" + suit +
                '}';
    }
}
