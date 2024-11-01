package com.ben.service;

import com.ben.modal.Home;
import com.ben.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    Home createHomePageData(List<HomeCategory> allCategories);
}
