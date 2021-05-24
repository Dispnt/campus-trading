package com.dispnt.mall.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "product_comment")
    private String productComment;

    @Column(name = "seller_id")
    private int sellerId;

    @Column(name= "type")
    private String type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Collection<Purchase_history> purchaseHistory;
    public Collection<Purchase_history> getPurchaseHistory() {
        return purchaseHistory;
    }
}
