package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;

public class BuyProductsBehaviour extends SimpleBehaviour {

    SequentialBehaviour behaviour;
    BuyProductsBehaviour(SequentialBehaviour behaviour){
        this.behaviour = behaviour;
    }

    @Override
    public void action() {
        if(behaviour.done()){
            System.out.println("Got all the items!");
        } else {
            System.out.println("Waiting for items...");
        }
    }

    @Override
    public boolean done() {
        return behaviour.done();
    }
}
