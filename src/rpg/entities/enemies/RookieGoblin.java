package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;
import rpg.enu.Stats;
import rpg.exceptions.EnemyDeathException;
import rpg.utils.cache.ImageCache;

import javax.swing.*;
import java.awt.*;

public class RookieGoblin extends Enemy {

    public RookieGoblin() {
        super("Rookie Goblin", 30, 7, EnemyType.BASIC);
        ImageCache.addImage("rookie_goblin", "hongo.png");
        initCharacter(); /** Inicializa las estadísticas del goblin **/
    }

    @Override
    public void getLoot() {
        System.out.println("The Rookie Goblin drops a small bag of coins.");
    }

    @Override
    protected void initCharacter() {
        this.type = EnemyType.BASIC;
        stats.put(Stats.HP, 30);
        stats.put(Stats.MAX_HP, 30);
        stats.put(Stats.ATTACK, 7);
        stats.put(Stats.DEFENSE, 3);
        stats.put(Stats.EXPERIENCE, 10);
        stats.put(Stats.GOLD, 5);
    }

    @Override
    public String attack(GameCharacter enemy) {
        String message = "";
        int damage = Math.max(0, stats.get(Stats.ATTACK) - enemy.getStats().get(Stats.DEFENSE));
        if (damage > 0) {
            try {
                int newHP = reduceHP(enemy, damage);
                message += String.format("""
                        The %s attacks for %d damage!
                        The enemy now has %d HP.
                        """, this.name, damage, newHP);
            } catch (EnemyDeathException e) {
                message += String.format("""
                        The %s attacks for %d damage!
                        The enemy has 0 HP and has died.
                        """, this.name, damage);
            }
        } else {
            message += String.format("The %s attacks, but it was ineffective!", this.name);
        }
        return message;
    }

    public ImageIcon getSprite() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/hongo.png"));
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Error al cargar la imagen RookieGoblin: " + icon.getImageLoadStatus());
        }

        /** Redimensionar la imagen **/
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }


}
