package com.ben.service.impl;

import com.ben.modal.HomeCategory;
import com.ben.repository.HomeCategoryRepository;
import com.ben.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

    private HomeCategoryRepository homeCategoryRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createHomeCategories(List<HomeCategory> homeCategories) {
        if(homeCategoryRepository.findAll().isEmpty())
            return homeCategoryRepository.saveAll(homeCategories);

        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(()-> new Exception("category not found"));

        if(homeCategory.getImage()!=null)
            existingCategory.setImage(homeCategory.getImage());

        if (homeCategory.getCategoryId()!=null)
            existingCategory.setCategoryId(homeCategory.getCategoryId());

        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
