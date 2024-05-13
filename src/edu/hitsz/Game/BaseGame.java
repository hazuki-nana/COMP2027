package edu.hitsz.Game;

import edu.hitsz.Graph.Rank;
import edu.hitsz.Music.LoopPlay;
import edu.hitsz.airFac.*;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.Music.MusicPlay;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.support.AbstractSupport;
import edu.hitsz.support.Bomb;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import javax.swing.plaf.LabelUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class BaseGame extends JPanel {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public void setDiff(String diff) {
        this.diff = diff;
    }

    private String diff = "Simple";

    int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    int timeInterval = 40;

    final HeroAircraft heroAircraft;
    final List<AbstractEnemy> enemyAircrafts;
    final List<BaseBullet> heroBullets;
    final List<BaseBullet> enemyBullets;
    final List<AbstractSupport> supports;
    EnemyFactory enemyFactory;
    boolean flag = false;
    /**
     * 屏幕中出现的敌机最大数量
     */
    int enemyMaxNumber = 5;

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    boolean gameOverFlag = false;

    private LoopPlay lp;

    double probability = 0.6;

    public BaseGame() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        supports = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

        lp = new LoopPlay();
        lp.start();
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public final void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            enemyAppear();

            // 子弹移动
            bulletsMoveAction();

            //道具移动
            supportMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 切换音乐
            bossVanish();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                lp.stopMusic();
                new MusicPlay("game_over", false);
                Main.displayOver(score, diff);
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************


    public abstract void eliteChange();
    public abstract void bossChange();

    private void enemyAppear(){
        if (timeCountAndNewCycleJudge()) {
            System.out.println(time);
            if (time % 6000 <= 500 && time % 6000 >=0 && !diff.equals("Simple"))
            {
                eliteChange();
                System.out.println("精英机出现概率：" + (1 - probability));
                System.out.println("最大敌机数量：" + enemyMaxNumber);
            }
            // 新敌机产生
            if (score % 500 < 350 && score % 500 > 0 && score >= 500 && flag && !diff.equals("Simple")) {
                flag = false;
                bossChange();
                lp.updateIsBoss();
            } else if (enemyAircrafts.size() < enemyMaxNumber) {
                if (Math.random() < probability)
                    enemyFactory = new MobFactory();
                else if (Math.random() < 0.85)
                    enemyFactory = new EliteFactory();
                else enemyFactory = new PlusFactory();
            }
            enemyAircrafts.add(enemyFactory.CreatEnemy());
            if (score % 500 > 350 && !flag) {
                flag = true;
                for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                    if (enemyAircraft instanceof BossEnemy) {
                        flag = false;
                        break;
                    }
                }
            }
            // 飞机射出子弹
            shootAction();
        }
    }

    private void bossVanish(){
        for (AbstractEnemy enemyAircraft : enemyAircrafts){
            if(enemyAircraft instanceof BossEnemy)
                if(enemyAircraft.notValid())
                    lp.updateIsBoss();
        }
    }

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft:enemyAircrafts)
            enemyBullets.addAll(enemyAircraft.shoot());
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void supportMoveAction() {
        for (AbstractSupport support : supports) {
            support.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }



    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets){
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 敌机撞击到英雄机子弹
                // 敌机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }

        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    new MusicPlay("bullet_hit", false);
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score += enemyAircraft.getScore();
                        supports.addAll(enemyAircraft.Drop());
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (AbstractSupport support : supports){
            if (support.notValid())
                continue;
            if (heroAircraft.crash(support)){
                if(support instanceof Bomb){
                    ((Bomb) support).registerObservers(enemyAircrafts);
                    ((Bomb) support).registerObservers(enemyBullets);
                    for(AbstractEnemy enemyAircraft :enemyAircrafts)
                        score += enemyAircraft.getScore();
                    new MusicPlay("bomb_explosion", false);
                }else new MusicPlay("get_supply", false);
                support.Effect(heroAircraft);
                support.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        supports.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public abstract void paint(Graphics g);

    void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

}
