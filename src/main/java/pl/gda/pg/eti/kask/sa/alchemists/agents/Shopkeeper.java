package pl.gda.pg.eti.kask.sa.alchemists.agents;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RegisterServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.shopkeeper.ShopkeeperBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Item;

/**
 *
 * @author psysiu
 */
public class Shopkeeper extends BaseAgent {

    @Setter
    @Getter
    protected Item item;

    @Setter
    @Getter
    protected int quantity;

    @Getter
    protected int price;

    public Shopkeeper() {
    }

    protected void parseOpts(){
        item = new Item((String) getArguments()[0]);
        price = Integer.parseInt((String) getArguments()[2]);
        quantity = Integer.parseInt((String) getArguments()[1]);
    }

    @Override
    protected void setup() {
        super.setup();
        parseOpts();
        addBehaviour(new RegisterServiceBehaviour(this, item.getName()));
        addBehaviour(new ShopkeeperBehaviour(this));
    }
}
