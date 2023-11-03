package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RequestActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GetItemInfo;

public class RequestItemInfoBehaviour extends RequestActionBehaviour<GetItemInfo, Mage> {
    public RequestItemInfoBehaviour(Mage agent, AID participant, GetItemInfo action) {
        super(agent, participant, action);
    }

    @Override
    protected ReceiveResultBehaviour createResultBehaviour(String conversationId) {
        return new ReceiveItemInfoBehaviour(myAgent, conversationId);
    }
}
