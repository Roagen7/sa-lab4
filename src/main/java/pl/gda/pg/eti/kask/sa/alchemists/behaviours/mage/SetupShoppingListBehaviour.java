package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.FindServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GetItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellItem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author psysiu
 */
public class SetupShoppingListBehaviour extends SimpleBehaviour {

    private final Behaviour behaviour;

    boolean impossible = false;

    private final Mage myAgent;

    public SetupShoppingListBehaviour(Behaviour behaviour, Mage agent) {
        super(agent);
        this.behaviour = behaviour;
        this.myAgent = agent;
    }

    @Override
    public void action() {
        if (!behaviour.done()) {
            System.out.println("Waiting for info");
            return;
        }

        try {
            generateShoppingList();
        } catch(Exception e){
            System.out.println("Not possible to buy all of the products. Aborting...");
            return;
        }


        SequentialBehaviour behaviour2 = new SequentialBehaviour(myAgent);

        for(Map.Entry<AID, Integer> et : myAgent.getShoppingList().entrySet()){
            AID id = et.getKey();
            behaviour2.addSubBehaviour(new RequestItemBehaviour(myAgent, id, new SellItem(et.getValue())));
        }

        myAgent.addBehaviour(behaviour2);
    }

    @Override
    public boolean done() {
        return behaviour.done();
    }
    
    public void generateShoppingList(){
        HashMap<String, Integer> todo = myAgent.getTodoList();
        HashMap<String, HashMap<AID, Integer>> priceMap = myAgent.getPriceMap();
        HashMap<String, HashMap<AID, Integer>> quantityMap = myAgent.getQuantityMap();

        int requiredBudget = 0;

        for(Map.Entry<String, Integer> et : todo.entrySet()){
            // get
            int quantityToGet = et.getValue();
            HashMap<AID, Integer> quantityProductMap = quantityMap.get(et.getKey());
            HashMap<AID, Integer> priceProductMap = priceMap.get(et.getKey());

            while(quantityToGet != 0){
                AID cheapestID = null;
                int cheapestPrice = Integer.MAX_VALUE;
                int cheapestQuantity = 0;

                for(AID vendor : priceProductMap.keySet()){
                    int price = priceProductMap.get(vendor);
                    int quantity = quantityProductMap.get(vendor);
                    if(quantity == 0){
                        continue;
                    }

                    if(price < cheapestPrice){
                        cheapestPrice = price;
                        cheapestID = vendor;
                        cheapestQuantity = quantity;
                    }
                }

                if(cheapestID == null){
                    throw new RuntimeException("not possible");
                }

                int gotQuantity = Integer.min(cheapestQuantity, quantityToGet);
                requiredBudget += cheapestPrice * gotQuantity;
                quantityProductMap.replace(cheapestID, cheapestQuantity - gotQuantity);
                quantityToGet -= gotQuantity;
                myAgent.addToShoppingList(cheapestID, gotQuantity);
            }
        }
        if(requiredBudget > myAgent.getCash()){
            throw new RuntimeException("not possible");
        }

    }
}
