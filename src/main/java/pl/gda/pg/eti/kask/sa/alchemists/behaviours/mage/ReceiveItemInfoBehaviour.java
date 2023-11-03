package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.ItemInfo;

public class ReceiveItemInfoBehaviour extends ReceiveResultBehaviour<Mage> {
    public ReceiveItemInfoBehaviour(Mage agent, String conversationId) {
        super(agent, conversationId);
    }

    @Override
    protected void handleResult(Predicate predicate, AID participant) {
        assert predicate instanceof Result;
        Result result = (Result) predicate;
        ItemInfo info = (ItemInfo) result.getValue();

        System.out.printf("(SHOPKEEPER %s) name: %s quantity: %s price: %s\n",
                participant.getName(),
                info.getItem().getName(),
                info.getQuantity(),
                info.getPrice());
        myAgent.addItemInfo(participant, info);
    }
}
