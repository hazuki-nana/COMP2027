package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.support.Heal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {
    private HeroAircraft heroAircraft;
    private MobEnemy mobEnemy;
    @BeforeEach
    void setUp() {
        heroAircraft = HeroAircraft.getHeroAircraft();
    }

    @AfterEach
    void tearDown() {
        heroAircraft = null;
        mobEnemy = null;
    }

    @Test
    void decreaseHp() {
        heroAircraft.decreaseHp(12);
        assertEquals(100-12, heroAircraft.getHp());
        heroAircraft.decreaseHp(102);
        assertEquals(0, heroAircraft.getHp());
    }

    @Test
    void crash() {
        mobEnemy = new MobEnemy(
                Main.WINDOW_WIDTH / 2 + 10,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() + 5,
                0, 0, 100);
        assertTrue(heroAircraft.crash(mobEnemy));
        mobEnemy = new MobEnemy(125, 30, 0, 0, 100);
        assertEquals(false, heroAircraft.crash(mobEnemy));
    }

    @Test
    void increaseHp() {
        heroAircraft.increaseHp(20);
        assertEquals(100, heroAircraft.getHp());
        heroAircraft.decreaseHp(50);
        heroAircraft.increaseHp(20);
        assertEquals(100-50+20, heroAircraft.getHp());
        heroAircraft.increaseHp(100);
    }
}