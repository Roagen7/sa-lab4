package pl.gda.pg.eti.kask.sa.alchemists.ontology;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ItemInfo {
    Item item;
    int quantity;
    int price;
}
