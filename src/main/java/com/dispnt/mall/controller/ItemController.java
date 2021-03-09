package com.dispnt.mall.controller;

import com.dispnt.mall.model.User;
import com.dispnt.mall.repository.ItemRepository;
import com.dispnt.mall.repository.UserRepository;
import com.dispnt.mall.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // {"name":"test","price":"123","imgUrl":"https://...","productComment":"苹果"}

    @GetMapping("/list")
    public List<Item> list(Model model){
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }

}
