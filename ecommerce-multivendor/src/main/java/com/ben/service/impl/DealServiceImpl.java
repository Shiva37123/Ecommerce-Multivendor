package com.ben.service.impl;

import com.ben.modal.Deal;
import com.ben.modal.HomeCategory;
import com.ben.repository.DealRepository;
import com.ben.repository.HomeCategoryRepository;
import com.ben.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {

        HomeCategory homeCategory = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(homeCategory);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepository.findById(id).orElse(null);
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if(existingDeal!=null){
            if(deal.getDiscount()!=null)
                existingDeal.setDiscount(deal.getDiscount());
            if (deal.getCategory()!=null)
                existingDeal.setCategory(deal.getCategory());
            return dealRepository.save(existingDeal);
        }
        throw new Exception("deal not found");

    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepository.findById(id).orElseThrow(()-> new Exception("deal not found"));
        dealRepository.delete(deal);
    }
}
