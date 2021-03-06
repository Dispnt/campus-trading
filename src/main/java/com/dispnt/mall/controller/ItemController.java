package com.dispnt.mall.controller;

import com.dispnt.mall.model.User;
import com.dispnt.mall.repository.ItemRepository;
import com.dispnt.mall.repository.PurchaseHistoryRepository;
import com.dispnt.mall.repository.UserRepository;
import com.dispnt.mall.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Item API
 */
@CrossOrigin("*")
@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add item to sell
     * @param item
     */
    @PostMapping("/add")
    public Item addItem(@RequestHeader (value="Authorization") String jwt,@RequestBody Item item){
        User user = userRepository.findByJsonWebToken(jwt);
        item.setSellerId(user.getId());
        return itemRepository.save(item);
    }


    /**
     * return all items
     */
    @GetMapping("/list")
    public List<Item> list(){
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }

    /**
     * return all catalogs
     */
    @GetMapping("/catalog")
    public HashSet<String> catalog(){
        List<Item> itemList = itemRepository.findAll();
        HashSet<String> types = new HashSet<>();
        for (Item i:itemList){
            String currentType = i.getType();
            if(!types.contains(currentType)){
                types.add(currentType);
            }
        }
        return types;
    }

    /**
     * return search result
     * @param searchContent
     */
    @GetMapping("/searchitem")
    public List<Item> search(@RequestParam("content") String searchContent){
        List<Item> results = itemRepository.findByNameContaining(searchContent);
        System.out.println(results);
        return results;
    }

    /**
     * get item detail
     * @param itemId
     */
    @PostMapping("/get")
    public Item getItem(@RequestParam("itemid") int itemId){
        Item result= itemRepository.findById(itemId);
        return result;
    }


    /**
     * show items in same catalog
     * @param type
     */
    @GetMapping("/cataloggoods")
    public List<Item> getItembyCatalog(@RequestParam("type") String type){
        List<Item> result= itemRepository.findAllByType(type);
        return result;
    }


}