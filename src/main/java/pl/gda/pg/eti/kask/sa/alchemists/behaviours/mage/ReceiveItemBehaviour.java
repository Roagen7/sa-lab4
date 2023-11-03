package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.ItemInfo;

/**
 *
 * @author psysiu
 */
public class ReceiveItemBehaviour extends ReceiveResultBehaviour<Mage> {

    public ReceiveItemBehaviour(Mage agent, String conversationId) {
        super(agent, conversationId);
    }

    @Override
    protected void handleResult(Predicate predicate, AID participant) {
        assert predicate instanceof Result;
        Result result = (Result) predicate;
        ItemInfo transactionInfo = (ItemInfo) result.getValue();
        myAgent.setCash(myAgent.getCash() - transactionInfo.getPrice());

        System.out.printf("Bought!!! %s amount: %d for total of %d \n", transactionInfo.getItem().getName(),
                transactionInfo.getQuantity(), transactionInfo.getPrice());
        System.out.printf("My current balance is %d$ \n", myAgent.getCash());
    }

}
