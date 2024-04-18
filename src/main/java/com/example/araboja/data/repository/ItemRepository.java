package com.example.araboja.data.repository;

import com.example.araboja.data.entity.Item;
import com.example.araboja.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
