package pl.gda.pg.eti.kask.sa.alchemists.behaviours.shopkeeper;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GetItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.ItemInfo;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellItem;

public class GetItemInfoBehaviour extends ActionBehaviour<GetItemInfo, Shopkeeper> {

    public GetItemInfoBehaviour(Shopkeeper agent, GetItemInfo action, String conversationId, AID participant) {
        super(agent, action, conversationId, participant);
    }

    @Override
    protected Predicate performAction() {
        return new Result(action, new ItemInfo(
                myAgent.getItem(),
                myAgent.getQuantity(),
                myAgent.getPrice()));
    }
}