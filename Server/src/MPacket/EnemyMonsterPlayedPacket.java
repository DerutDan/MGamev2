package MPacket;

import GameCard.GameCard;

/**
 * Created by Danila on 01.04.17.
 */
public class EnemyMonsterPlayedPacket extends MPacket {
    GameCard card;
    public EnemyMonsterPlayedPacket(GameCard card) {
        this.card = card;
    }

    @Override
    public String getContain() {
        return card.serialize();
    }

    @Override
    public void setT() {
        type = "EnemyMonsterPlayedPacket";
    }
}
