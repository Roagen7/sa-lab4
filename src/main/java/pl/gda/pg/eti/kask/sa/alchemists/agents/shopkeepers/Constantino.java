package pl.gda.pg.eti.kask.sa.alchemists.agents.shopkeepers;

import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Item;

public class Constantino extends Shopkeeper {
    @Override
    protected void parseOpts(){
        item = new Item("wilcze jagody");
        quantity = 1;
        price = 1;
    }
}
