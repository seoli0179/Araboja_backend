package com.example.araboja.controller;

import com.example.araboja.service.parser.KamisAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final KamisAPI jsonParser;

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.status(HttpStatus.OK).body("어드민 권한 있음");
    }

    @GetMapping("/admin/test")
    public ResponseEntity<String> test(
            @RequestParam("category") String category,
            @RequestParam("date") String date) {

        try {

            String str = "";
            str += jsonParser.dailyPriceByCategoryList(category, date);
            return ResponseEntity.ok(str);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.badRequest().body("실패");
    }


}
