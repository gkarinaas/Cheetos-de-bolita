package rpg.entities.enemies;

import rpg.GameCharacter;
import rpg.enu.EnemyType;
import rpg.enu.Stats;
import rpg.utils.cache.ImageCache;

import javax.swing.*;

public class RookieGoblin extends Enemy {

    public RookieGoblin() {
        super("Rookie Goblin"); // Nombre del enemigo
        initCharacter();       // Inicializar atributos del enemigo
    }

    protected void initCharacter() {
        this.type = EnemyType.BASIC; // Tipo del enemigo
        // Establecer estadísticas del enemigo
        this.stats.put(Stats.MAX_HP, 50);
        this.stats.put(Stats.HP, 50);
        this.stats.put(Stats.ATTACK, 10);
        this.stats.put(Stats.DEFENSE, 5);
        this.stats.put(Stats.EXPERIENCE, 15);
        this.stats.put(Stats.GOLD, 20);

        // Agregar la imagen del enemigo al caché
        ImageCache.addImage("rookie_goblin", "C:\\Users\\HUAWEI\\IdeaProjects\\Cheetos-de-bolita\\Resource\\RookieGoblin.png");
    }

    @Override
    public ImageIcon getSprite() {
        // Recuperar la imagen del caché
        return ImageCache.getImageIcon("rookie_goblin");
    }

    @Override
    public void getLoot() {
        // Implementación para obtener el botín
        System.out.println("El Rookie Goblin dejó caer oro y un objeto básico.");
    }

    @Override
    public void attack(GameCharacter enemy) {
        // Implementación del ataque
        int damage = this.stats.get(Stats.ATTACK) - enemy.getStats().getOrDefault(Stats.DEFENSE, 0);
        damage = Math.max(damage, 0); // Asegurarse de que no haya daño negativo
        enemy.takeDamage(damage);
        System.out.println(this.name + " atacó a " + enemy.getName() + " causando " + damage + " de daño.");
    }

    @Override
    protected void initializeStats() {

    }
}
