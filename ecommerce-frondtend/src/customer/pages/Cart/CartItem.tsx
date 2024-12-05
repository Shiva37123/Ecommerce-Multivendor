import { Add, Close, Remove } from "@mui/icons-material";
import { Button, Divider, IconButton } from "@mui/material";
import React, { useState } from "react";

const CartItem = () => {
  const handleUpdateQuantity = () => {
    // update cart Quantity
  };

  const [quantity, setQuantity] = useState(1);
  return (
    <div className="border rounded-md relative">
      <div className="p-5 flex gap-3">
        <div>
          <img
            className="w-[90px] rounded-md"
            src="images\tshirt\white_tshirt_1.jpg"
            alt=""
          />
        </div>
        <div className="space-y-2">
          <h1 className="font-semibold text-lg">Kalapi Clothing</h1>
          <p className="text-gray-600 font-medium text-sm">Men white shirt</p>
          <p className="text-gray-400 text-xs">
            <strong>Sold by : </strong> XYZ Lifestyle Products Private Limited
          </p>
          <p className="text-sm">7 days replacement available</p>
          <p className="text-sm text-gray-500">
            <strong>Quantity : </strong>5
          </p>
        </div>
      </div>
      <Divider />
      <div className="flex justify-between items-center">
        <div className="px-5 py-2 flex justify-between items-center">
          <div className="flex items-center gap-2 w-[140px] justify-between">
            <Button
              disabled={quantity === 1}
              onClick={() => setQuantity(quantity - 1)}
            >
              <Remove />
            </Button>
            <span>{quantity}</span>
            <Button onClick={() => setQuantity(quantity + 1)}>
              <Add />
            </Button>
          </div>
        </div>
        <div className="pr-5">
          <p className="text-gray-700 font-medium">₹ 799</p>
        </div>
      </div>
      <div className="absolute top-1 right-1">
        <IconButton color="primary">
          <Close />
        </IconButton>
      </div>
    </div>
  );
};

export default CartItem;
