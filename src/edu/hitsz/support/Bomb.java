package edu.hitsz.support;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.Observer;

import java.util.LinkedList;
import java.util.List;

public class Bomb extends AbstractSupport {

    private List<Observer> observers = new LinkedList<>();
    public Bomb(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void Effect(HeroAircraft heroAircraft){
        notifyObservers();
    }

    public void registerObservers(List<? extends Observer> observers){
        this.observers.addAll(observers);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(){
        for(Observer observer : observers){
            observer.update();
        }
    }
}
