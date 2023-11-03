package pl.gda.pg.eti.kask.sa.alchemists.agents;

import jade.core.AID;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.FindServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.SetupShoppingListBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestItemInfoBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GetItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.ItemInfo;

import java.util.HashMap;

/**
 *
 * @author psysiu
 */
public class Mage extends BaseAgent {

    public Mage() {
    }

    static final String potionPrefix = "potion";
    static final String herbPrefix = "herb";
    static final String staffPrefix = "staff";

    @Getter
    @Setter
    private int cash;

    // item name -> number of items of the type to buy
    @Getter
    HashMap<String, Integer> todoList = new HashMap<>();

    // AID -> number of items to buy from the vendor

    @Getter
    HashMap<AID, Integer> shoppingList = new HashMap<>();

    // item name -> (AID -> price of the item)
    @Getter
    HashMap<String, HashMap<AID, Integer>> priceMap = new HashMap<>();

    // item name -> (AID -> quantity of the item)
    @Getter
    HashMap<String, HashMap<AID, Integer>> quantityMap = new HashMap<>();

    private void parseOpts(){
        cash = Integer.parseInt((String) getArguments()[0]);

        for(int i = 1; i < getArguments().length; i+=2){
            String name = (String) getArguments()[i];
            int quantity = Integer.parseInt((String) getArguments()[i+1]);
            todoList.put(name, quantity);
        }
    }

    private void setupPriceQuery(SequentialBehaviour behaviour){
        for(String product : todoList.keySet()){
            behaviour.addSubBehaviour(new FindServiceBehaviour(this, product) {
                @Override
                protected void onResult(DFAgentDescription[] services) {
                    if(services == null){
                        return;
                    }

                    for(DFAgentDescription service : services){
                        AID id = service.getName();
                        RequestItemInfoBehaviour request = new RequestItemInfoBehaviour(Mage.this, id, new GetItemInfo());
                        ((SequentialBehaviour) getParent()).addSubBehaviour(request);
                    }
                }
            });

            priceMap.put(product, new HashMap<>());
            quantityMap.put(product, new HashMap<>());
        }
    }

    @Override
    protected void setup() {
        super.setup();
        SequentialBehaviour behaviour = new SequentialBehaviour(this);
        parseOpts();
        setupPriceQuery(behaviour);

        addBehaviour(behaviour);
        addBehaviour(new SetupShoppingListBehaviour(behaviour, this));
    }


    public void addItemInfo(AID id, ItemInfo info){
        quantityMap.get(info.getItem().getName()).put(id, info.getQuantity());
        priceMap.get(info.getItem().getName()).put(id, info.getPrice());
    }

    public void addToShoppingList(AID id, int quantity){
        shoppingList.put(id, quantity);
    }
}
