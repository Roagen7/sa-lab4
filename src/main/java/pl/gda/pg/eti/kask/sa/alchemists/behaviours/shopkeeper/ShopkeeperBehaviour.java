package pl.gda.pg.eti.kask.sa.alchemists.behaviours.shopkeeper;

import jade.content.onto.basic.Action;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.WaitingBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GetItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellItem;

/**
 *
 * @author psysiu
 */
public class ShopkeeperBehaviour extends WaitingBehaviour<Shopkeeper>{

    public ShopkeeperBehaviour(Shopkeeper agent) {
        super(agent);
    }

    @Override
    protected void action(Action action, String conversationId, AID participant) {
        if (action.getAction() instanceof SellItem) {
            myAgent.addBehaviour(new SellItemBehaviour(myAgent, (SellItem) action.getAction(), conversationId, participant));
        } else if(action.getAction() instanceof GetItemInfo){
            myAgent.addBehaviour(new GetItemInfoBehaviour(myAgent, (GetItemInfo) action.getAction(), conversationId, participant));
        }
    }
    
}
