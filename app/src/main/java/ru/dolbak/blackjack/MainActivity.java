package ru.dolbak.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonTakeCard, buttonStopGame;
    TextView playersTextView, dealersTextView, gameStatsTextView;
    boolean theGameIsActive = true;

    Card[] dealersArrayOfCards = {new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades)};

    void putCard(int slot, Card card){
        int[] slots = {R.id.dealer_card_4, R.id.dealer_card_5, R.id.dealer_card_3,
                R.id.dealer_card_2, R.id.dealer_card_1, R.id.player_card_5, R.id.player_card_4,
                R.id.player_card_2, R.id.player_card_3, R.id.player_card_1};
        ImageView imageView = findViewById(slots[slot]);
        int cardDrawableID = CardMisc.CardToImageID(card);
        imageView.setImageDrawable(getResources().getDrawable(cardDrawableID));
    }

    void stopGame(int playersPoints, int dealerPoints){
        for (int i = 0; i < dealersArrayOfCards.length; i++){
            putCard(i, dealersArrayOfCards[i]);
        }
        dealersTextView.setText("Очки крупье: " + dealerPoints);
        if(playersPoints>21 && dealerPoints>21){
            gameStatsTextView.setText("Ничья");
            //ничья
        }
        else if (playersPoints > 21){
            gameStatsTextView.setText("Игрок проиграл");
            //игрок проиграл
        }
        else if (dealerPoints >21){
            gameStatsTextView.setText("Игрок выиграл");
            //игрок выиграл
        }
        else if (dealerPoints > playersPoints){
            gameStatsTextView.setText("Игрок проиграл");
            //игрок проиграл
        }
        else if (dealerPoints == playersPoints){
            gameStatsTextView.setText("Ничья");
        }
        else  if (dealerPoints<playersPoints){
            gameStatsTextView.setText("Игрок выиграл");
            //игрок выиграл
        }
        theGameIsActive = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
buttonTakeCard = findViewById(R.id.button_take_card);
buttonStopGame = findViewById(R.id.button_stop_game);
playersTextView = findViewById(R.id.players_text_view);
dealersTextView = findViewById(R.id.dealers_text_view);
gameStatsTextView = findViewById(R.id.game_stats_text_view);

       Deck deck = new Deck();

       int dealerCards = 0, dealerPoints = 0;
        final int[] playerCards = {0};
        final int[] playerPoints = { 0 };


        for(int i = 0; i<2; i++){
           Card card = deck.take();
           putCard(playerCards[0] + 5, card);
           playerCards[0]++;
           playerPoints[0] += card.points;
            playersTextView.setText("Очки игрока: "+playerPoints[0]);
       }

       while (dealerCards<5 && dealerPoints <16){
           Card card = deck.take();
           if (dealerCards ==0){
               putCard(dealerCards, card);
           }
           dealersArrayOfCards[dealerCards] = card;
           dealerCards++;
           dealerPoints+=card.points;
           dealersTextView.setText("Очки крупье: ???");
       }

        int finalDealerPoints = dealerPoints;
       buttonTakeCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!theGameIsActive){
                   return;
               }
              Card card = deck.take();
              putCard(playerCards[0] +5, card);
              playerCards[0]++;
              playerPoints[0] += card.points;
               playersTextView.setText("Очки игрока: "+playerPoints[0]);

           if(playerCards[0] >=5 || playerPoints[0] >=21){
               stopGame(playerPoints[0], finalDealerPoints);
           }
           }
       });


        buttonStopGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!theGameIsActive){
                    return;
                }
                stopGame(playerPoints[0], finalDealerPoints);
            }
        });
    }
}