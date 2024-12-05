import { ElectricBolt } from "@mui/icons-material";
import { Avatar } from "@mui/material";
import React from "react";

const OrderItem = () => {
  return (
    <div className="text-sm bg-white p-5 space-y-4 border rounded-md cursor-pointer">
      <div className="flex items-center gap-5">
        <div>
          <Avatar sizes="small" sx={{ bgcolor: "#3a06bd" }}>
            <ElectricBolt />
          </Avatar>
        </div>
        <div>
          <h1 className="font-bold text-primary-color">PENDING</h1>
          <p>Arriving By Mon, 9 Dec</p>
        </div>
      </div>
      <div className="p-5 bg-teal-50 flex gap-3">
        <div>
          <img
            className="w-[70px]"
            src="/images/tshirt/white_tshirt_1.jpg"
            alt=""
          />
        </div>
        <div className="w-full space-y-2">
          <h1 className="font-bold">Raam Clothing</h1>
          <p>
            Classic White Full-Sleeve T-Shirt crafted from premium, breathable
            fabric for all-day comfort. Featuring a sleek, minimalist design,
            this wardrobe essential pairs effortlessly with any outfit for a
            clean and timeless look.
          </p>
          <p><strong>size : </strong>Free</p>
        </div>
      </div>
    </div>
  );
};

export default OrderItem;
