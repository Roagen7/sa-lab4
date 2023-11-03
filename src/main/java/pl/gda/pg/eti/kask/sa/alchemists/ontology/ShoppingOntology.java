package pl.gda.pg.eti.kask.sa.alchemists.ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import java.util.logging.Level;
import lombok.Getter;
import lombok.extern.java.Log;

/**
 *
 * @author psysiu
 */
@Log
public class ShoppingOntology extends BeanOntology {

    public static final String NAME = "alchemy-ontology";

    @Getter
    private static final ShoppingOntology instance = new ShoppingOntology(NAME);

    private ShoppingOntology(String name) {
        super(name);
        try {
            add(Item.class);
            add(ItemInfo.class);
            add(SellItem.class);
            add(GetItemInfo.class);
        } catch (BeanOntologyException ex) {
            log.log(Level.SEVERE, null, ex);
        }

    }

}
