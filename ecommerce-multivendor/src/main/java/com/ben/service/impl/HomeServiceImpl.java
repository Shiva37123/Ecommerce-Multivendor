package com.ben.service.impl;

import com.ben.domain.HomeCategorySection;
import com.ben.modal.Deal;
import com.ben.modal.Home;
import com.ben.modal.HomeCategory;
import com.ben.repository.DealRepository;
import com.ben.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {

        List<HomeCategory> gridCategories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.GRID)
                .toList();

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.SHOP_BY_CATEGORY)
                .toList();

        List<HomeCategory> electricCategories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.ELECTRIC_CATEGORY)
                .toList();

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(category->
                        category.getSection() == HomeCategorySection.DEALS)
                .toList();

        List<Deal> createdDeals = new ArrayList<>();

        if(dealRepository.findAll().isEmpty()){
            List<Deal> deals = allCategories.stream()
                    .filter(category-> category.getSection() == HomeCategorySection.DEALS)
                    .map(category-> new Deal(null, 10, category))
                    .toList();
            createdDeals = dealRepository.saveAll(deals);
        }
        else
            createdDeals = dealRepository.findAll();

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDeals(createdDeals);
        home.setDealCategories(dealCategories);

        return home;
    }
}
