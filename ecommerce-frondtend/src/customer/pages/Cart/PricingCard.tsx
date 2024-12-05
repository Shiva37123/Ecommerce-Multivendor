import { Divider } from "@mui/material";
import React from "react";

const PricingCard = () => {
  return (
    <div>
      <div className="space-y-3 p-5">
        <div className="flex justify-between items-center">
          <span>Subtotal</span>
          <span>₹899</span>
        </div>
        <div className="flex justify-between items-center">
          <span>Discount</span>
          <span className="line-through">₹699</span>
        </div>
        <div className="flex justify-between items-center">
          <span>Shipping</span>
          <span>₹69</span>
        </div>
        <div className="flex justify-between items-center">
          <span>Platform fee</span>
          <span>Free</span>
        </div>
        
      </div>
      <Divider/>
        <div className="flex justify-between items-center p-5 text-primary-color">
          <span>Total</span>
          <span>₹799</span>
        </div>
    </div>
  );
};

export default PricingCard;
