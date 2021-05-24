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

@CrossOrigin("*")
@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public Item addItem(@RequestHeader (value="Authorization") String jwt,@RequestBody Item item){
        User user2 = userRepository.findByJsonWebToken(jwt);
        item.setSellerId(user2.getId());
        return itemRepository.save(item);
    }



    @GetMapping("/list")
    public List<Item> list(Model model){
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }

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

    @GetMapping("/search")
    public List<Item> search(@RequestParam("content") String searchContent){
        List<Item> results = itemRepository.findByNameContaining(searchContent);
        return results;
    }

    @PostMapping("/get")
    public Item getItem(@RequestParam("itemid") int itemId){
        Item result= itemRepository.findById(itemId);
        return result;
    }

}