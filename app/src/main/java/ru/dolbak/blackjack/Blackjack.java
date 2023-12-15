package ru.dolbak.blackjack;

import static java.lang.Math.abs;

import java.util.Random;

class Deck{
    int pointer = 0;
    Card[] cards = new Card[52];

    public Deck() {
        Suit[] suits = {Suit.Clubs, Suit.Spades, Suit.Diamonds, Suit.Hearts};
        Rate[] rates = {Rate.TWO, Rate.THREE, Rate.FOUR, Rate.FIVE, Rate.SIX, Rate.SEVEN, Rate.EIGHT, Rate.NINE, Rate.TEN, Rate.JACK, Rate.QUEEN, Rate.KING, Rate.ACE};

        for(int i=0; i<suits.length; i++){
            for (int j = 0; j<rates.length; j++){
                cards[i*13+j] = new Card(rates[j], suits [i]);
            }
        }

        Random rnd = new Random();
        for(int i=0; i< cards.length; i++){
            int r = abs(rnd.nextInt())%(cards.length-i);
            Card t = cards[r];
            cards[r]=cards[cards.length-i-1];
            cards[cards.length-i-1]=t;
        }
    }

    public Card take(){
        pointer++;
        return cards[pointer-1];
    }


}

enum Suit{
    Diamonds,
    Hearts,
    Spades,
    Clubs
}

enum Rate{
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(1),
    COVER(0);
    private int points;
    Rate(int points){
        this.points = points;
    }
    public int getPoints(){
        return this.points;
    }
}

class Card{
    Rate rate;
    Suit suit;
    int points = 0;
    public Card(Rate rate, Suit suit){
        this.rate = rate;
        this.suit = suit;
        this.points = rate.getPoints();
    }

}

class Game{
    //TODO переменные для очков игрока и крупье
    //методы для взятия карты игроком и крупье
    //метод для вывода очков игрока и крупье
    //метод для вывода карт игрока и крупье
    //определить, кто победил

}

