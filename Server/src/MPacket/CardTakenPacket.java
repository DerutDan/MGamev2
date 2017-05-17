package MPacket;

import GameCard.GameCard;

public class CardTakenPacket extends MPacket {
    GameCard card;
    public CardTakenPacket(GameCard card)
    {
        this.card = card;
    }
    @Override
    public String getContain() {
        return card.serialize();
    }

    @Override
    public void setT() {
        type = "CardTakenPacket";
    }
}
