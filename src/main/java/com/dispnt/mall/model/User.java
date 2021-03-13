package com.dispnt.mall.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.OneToMany;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;


    @Column(name = "json_web_token")
    private String jsonWebToken;

    @Column(name = "stu_id")
    private int stuId;

    @Column(name = "password")
    private String password;

    @Column(name = "intro")
    private String intro;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Collection<Purchase_history> purchaseHistory;

    public void addPurchase(Purchase_history purchase){
        if(purchaseHistory == null){
            purchaseHistory = new ArrayList<>();
        }
        purchaseHistory.add(purchase);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Item> items;
    public Collection<Item> getitems() {
        return items;
    }


}

