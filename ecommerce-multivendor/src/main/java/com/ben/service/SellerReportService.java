package com.ben.service;

import com.ben.modal.Seller;
import com.ben.modal.SellerReport;

public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
