package com.example.araboja.service.item;

import com.example.araboja.data.dto.dailySalesList.EconomicalProductResponse;
import com.example.araboja.data.entity.Item;
import com.example.araboja.data.repository.ItemRepository;
import com.example.araboja.service.parser.KamisAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final KamisAPI kamisAPI;
    private final ItemRepository itemRepository;

    public List<EconomicalProductResponse> economicalProduct() throws JsonProcessingException {

        List<Item> items = itemRepository.findAll();
        Collections.shuffle(items);
        items = items.subList(0, 8);
        return kamisAPI.dailySalesList();
    }


}
