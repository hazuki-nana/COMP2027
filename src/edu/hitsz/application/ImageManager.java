package edu.hitsz.application;


import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.support.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage BACKGROUND_IMAGE2;
    public static BufferedImage BACKGROUND_IMAGE3;
    public static BufferedImage BACKGROUND_IMAGE5;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage PLUS_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage Battery_ENEMY_IMAGE;
    public static BufferedImage PBOSS_ENEMY_IMAGE;
    public static BufferedImage MATE_IMAGE;
    public static BufferedImage HEAL_IMAGE;
    public static BufferedImage FIRE_IMAGE;
    public static BufferedImage BOMB_IMAGE;
    public static BufferedImage PLUS_FIRE_IMAGE;
    public static BufferedImage MATE_PROP_IMAGE;

    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            BACKGROUND_IMAGE2 = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            BACKGROUND_IMAGE3 = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
            BACKGROUND_IMAGE5 = ImageIO.read(new FileInputStream("src/images/bg5.jpg"));

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            PLUS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            Battery_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/battery.png"));
            PBOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/pboss.png"));
            MATE_IMAGE = ImageIO.read(new FileInputStream("src/images/mate.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            HEAL_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            FIRE_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            BOMB_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            PLUS_FIRE_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));
            MATE_PROP_IMAGE = ImageIO.read(new FileInputStream("src/images/mate_supply.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PlusEnemy.class.getName(), PLUS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Battery.class.getName(), Battery_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PlusBoss.class.getName(), PBOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Mate.class.getName(), MATE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Heal.class.getName(), HEAL_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Fire.class.getName(), FIRE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Bomb.class.getName(), BOMB_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PlusFire.class.getName(), PLUS_FIRE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MateProp.class.getName(), MATE_PROP_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
