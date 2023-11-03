package pl.gda.pg.eti.kask.sa.alchemists.agents.shopkeepers;

import pl.gda.pg.eti.kask.sa.alchemists.agents.Shopkeeper;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Item;

public class CorAngar extends Shopkeeper {

    @Override
    protected void parseOpts(){
        item = new Item("bagienne ziele");
        quantity = 2;
        price = 5;
    }
}

