package pl.gda.pg.eti.kask.sa.alchemists.agents.shopkeepers;

import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Item;

public class Abuyin extends Shopkeeper {
    @Override
    protected void parseOpts(){
        item = new Item("wilcze jagody");
        quantity = 10;
        price = 10;
    }
}
