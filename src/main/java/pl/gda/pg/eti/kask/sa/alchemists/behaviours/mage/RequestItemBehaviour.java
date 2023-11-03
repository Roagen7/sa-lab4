package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RequestActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellItem;

/**
 *
 * @author psysiu
 */
public class RequestItemBehaviour extends RequestActionBehaviour<SellItem, Mage> {

    public RequestItemBehaviour(Mage agent, AID participant, SellItem action) {
        super(agent, participant, action);
    }

    @Override
    protected ReceiveResultBehaviour createResultBehaviour(String conversationId) {
        return new ReceiveItemBehaviour(myAgent, conversationId);
    }
    
}
