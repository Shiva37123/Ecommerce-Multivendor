package com.ben.service.impl;

import com.ben.modal.Seller;
import com.ben.modal.SellerReport;
import com.ben.repository.SellerReportRepository;
import com.ben.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private SellerReportRepository sellerReportRepository;


    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());

        if(sellerReport==null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }
}
