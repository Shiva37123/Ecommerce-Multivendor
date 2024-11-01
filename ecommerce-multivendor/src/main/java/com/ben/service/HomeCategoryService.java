package com.ben.service;

import com.ben.modal.HomeCategory;

import java.util.List;

public interface HomeCategoryService {
    HomeCategory createHomeCategory(HomeCategory homeCategory);
    List<HomeCategory> createHomeCategories(List<HomeCategory> homeCategories);
    HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception;
    List<HomeCategory> getAllHomeCategories();
}
