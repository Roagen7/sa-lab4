package pl.gda.pg.eti.kask.sa.alchemists.behaviours.shopkeeper;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.ItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellItem;

/**
 *
 * @author psysiu
 */
public class SellItemBehaviour extends ActionBehaviour<SellItem, Shopkeeper> {

    public SellItemBehaviour(Shopkeeper agent, SellItem action, String conversationId, AID participant) {
        super(agent, action, conversationId, participant);
    }

    @Override
    protected Predicate performAction() {
        myAgent.setQuantity(myAgent.getQuantity() - action.getQuantity());
        return new Result(action, new ItemInfo(
                myAgent.getItem(),
                action.getQuantity(),
                myAgent.getPrice() * action.getQuantity()));
    }
}
