package com.ben.controller;

import com.ben.modal.*;
import com.ben.service.HomeCategoryService;
import com.ben.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class HomeCategoryController {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategories(
            @RequestBody List<HomeCategory> homeCategories) throws Exception {

        List<HomeCategory> categories = homeCategoryService.createHomeCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-categories")
    public ResponseEntity<List<HomeCategory>> createHomeCategories() throws Exception {

        List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
        return  ResponseEntity.ok(categories);
    }


    @PostMapping("/admin/home-categories/{id}")
    public ResponseEntity<HomeCategory> createHomeCategories(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory) throws Exception {

        HomeCategory category = homeCategoryService.updateHomeCategory(homeCategory, id);
        return ResponseEntity.ok(category);
    }


}
