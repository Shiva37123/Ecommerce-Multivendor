package com.ben.controller;

import com.ben.modal.Deal;
import com.ben.response.ApiResponse;
import com.ben.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeal(
            @RequestBody Deal deal
    ){
        Deal createdDeal = dealService.createDeal(deal);
        return new ResponseEntity<>(createdDeal, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception{
        Deal updatedDeal = dealService.updateDeal(deal,id);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeal(
            @PathVariable Long id
    ) throws Exception {
        dealService.deleteDeal(id);

        ApiResponse res = new ApiResponse();
        res.setMessage("Deal deleted");

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
