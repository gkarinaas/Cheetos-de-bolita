package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;
import rpg.enu.Stats;
import rpg.exceptions.EnemyDeathException;
import rpg.utils.cache.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WarriorOrc extends Enemy {

    public WarriorOrc() {
        super("Warrior Orc", 50, 12, EnemyType.INTERMEDIATE);
        ImageCache.addImage("warrior_orc", "monoenojao.png");
        initCharacter(); /** Inicializa las estadísticas del orco **/
    }

    @Override
    public void getLoot() {
        System.out.println("The Warrior Orc drops a shiny sword.");
    }

    @Override
    protected void initCharacter() {
        this.type = EnemyType.INTERMEDIATE;
        stats.put(Stats.HP, 50);
        stats.put(Stats.MAX_HP, 50);
        stats.put(Stats.ATTACK, 12);
        stats.put(Stats.DEFENSE, 5);
        stats.put(Stats.EXPERIENCE, 20);
        stats.put(Stats.GOLD, 15);
    }

    @Override
    public String attack(GameCharacter enemy) {
        String message = "";
        int damage = Math.max(0, stats.get(Stats.ATTACK) - enemy.getStats().get(Stats.DEFENSE));
        if (damage > 0) {
            try {
                int newHP = reduceHP(enemy, damage);
                message += String.format("""
                        The %s swings its weapon for %d damage!
                        The enemy now has %d HP.
                        """, this.name, damage, newHP);
            } catch (EnemyDeathException e) {
                message += String.format("""
                        The %s swings its weapon for %d damage!
                        The enemy has 0 HP and has died.
                        """, this.name, damage);
            }
        } else {
            message += String.format("The %s attacks, but it was ineffective!", this.name);
        }
        return message;
    }

    public ImageIcon getSprite() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/monoenojao.png"));
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Error al cargar la imagen RookieGoblin: " + icon.getImageLoadStatus());
        }

        /** Redimensionar la imagen **/
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }


}
