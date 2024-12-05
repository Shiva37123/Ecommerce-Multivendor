import { Box, Button, Divider } from "@mui/material";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import OrderStepper from "./OrderStepper";
import { Payments } from "@mui/icons-material";

const OrderDetails = () => {
  const navigate = useNavigate();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <Box className="space-y-5">
      <section className="flex flex-col gap-5 justify-center items-center">
        <img
          className="w-[100px]"
          src="/images/tshirt/white_tshirt_1.jpg"
          alt=""
        />
        <div className="text-sm space-y-1 text-xenter">
          <h1 className="font-bold">Raam Clothing</h1>
          <p>
            Classic White Full-Sleeve T-Shirt crafted from premium, breathable
            fabric for all-day comfort. Featuring a sleek, minimalist design,
            this wardrobe essential pairs effortlessly with any outfit for a
            clean and timeless look.
          </p>
          <p>
            <strong>size : </strong>Free
          </p>
        </div>
        <div>
          <Button onClick={() => navigate(`/review/${5}/create`)}>
            Write Review
          </Button>
        </div>
      </section>
      <section className="border p-5">
        <OrderStepper orderStatus={"SHIPPED"} />
      </section>
      <div className="border p-5">
        <h1 className="font-bold pb-3">Delivery Address</h1>
        <div className="flex gap-5 font-medium">
          <p>Ben</p>
          <Divider flexItem orientation="vertical" />
          <p>9456235647</p>
        </div>
        <p>Near Railway Station, Margao, South Goa, Goa - 403411</p>
      </div>
      <div className="border space-y-4">
        <div className="flex justify-between text-sm pt-5 px-5">
          <div className="space-y-1">
            <p className="font-bold">Total Item Price</p>
            <p>
              You saved{" "}
              <span className="text-green-500 font-medium text-xs">
                ₹ 300.00 on this item
              </span>
            </p>
          </div>
          <p className="font-medium">₹ 699.00</p>
        </div>
        <div className="px-5">
            <div className="bg-teal-50 px-5 py-2 text-xs font-medium flex items-center gap-3">
                <Payments/>
                <p>Pay On Delivery</p>
            </div>
        </div>
        <Divider/>
        <div className="px-5 pb-5">
            <p className="text-xs"><strong>Sold by : </strong>Ram Clothing</p>
        </div>

        <div className="p-10">
            <Button
            disabled={true}
            // onClick={handleCancelOrder}
            color="error" sx={{py: " 0.7rem"}} className="" variant="outlined" fullWidth
            >
                {true?"order canceled":"Cancel Order"}
            </Button>
        </div>

      </div>
    </Box>
  );
};

export default OrderDetails;
