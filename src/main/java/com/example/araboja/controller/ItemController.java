package com.example.araboja.controller;

import com.example.araboja.data.dto.dailySalesList.EconomicalProductResponse;
import com.example.araboja.service.item.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/v1/ecoproduct")
    public ResponseEntity<List<EconomicalProductResponse>> economicalProduct() throws JsonProcessingException {

        try {
            return ResponseEntity.ok().body(itemService.economicalProduct());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return ResponseEntity.badRequest().body(null);
    }



}
