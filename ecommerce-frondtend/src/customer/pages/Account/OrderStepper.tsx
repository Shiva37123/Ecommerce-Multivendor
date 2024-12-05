import React, { useEffect, useState } from "react";
import { Box, Step, StepLabel, Stepper } from "@mui/material";
import PropTypes from "prop-types";
import { CheckCircle, FiberManualRecord } from "@mui/icons-material";

const steps = [
  {
    name: "Order Placed",
    description: "Your order was placed on Sun, 3 Dec",
    value: "PLACED",
  },
  {
    name: "Order Confirmed",
    description: "Confirmed on Mon, 4 Dec",
    value: "CONFIRMED",
  },
  { name: "Packed", description: "Packed on Tue, 5 Dec", value: "PACKED" },
  { name: "Shipped", description: "Shipped on Wed, 6 Dec", value: "SHIPPED" },
  {
    name: "Arriving",
    description: "Expected by Thu, 7 Dec",
    value: "ARRIVING",
  },
  {
    name: "Delivered",
    description: "Delivery completed on Fri, 8 Dec",
    value: "DELIVERED",
  },
];

const canceledStep = [
  {
    name: "Order Placed",
    description: "Your order was placed on Thu, 28 Nov",
    value: "PLACED",
  },
  {
    name: "Order Canceled",
    description: "Canceled on Thu, 28 Nov",
    value: "CANCELLED",
  }
];

const currentStep = 2;

const OrderStepper = ({ orderStatus }: any) => {
  const [statusStep, setStatusStep] = useState(steps);

  useEffect(() => {
    if (orderStatus === "CANCELLED") {
      setStatusStep(canceledStep);
    } else {
      setStatusStep(steps);
    }
  }, [orderStatus]);

  return (
    <Box className="my-10">
      {statusStep.map((step, index) => (
        <>
          <div key={index} className={`flex px-4`}>
            <div className="flex flex-col items-center">
              <Box
                sx={{ zIndex: -1 }}
                className={`w-8 h-8 rounded-full flex items-center justify-center z-10
                ${
                  index <= currentStep
                    ? "bg-gray-200 text-primary-color"
                    : "bg-gray-300 text-gray-500"
                }`}
              >
                {step.value === orderStatus ? (
                  <CheckCircle />
                ) : (
                  <FiberManualRecord sx={{ zIndex: -1 }} />
                )}
              </Box>
              {index < statusStep.length - 1 && (
                <div
                  className={`border h-20 w-[2px] ${
                    index < currentStep
                      ? "bg-primary-color"
                      : "bg-gray-300 text-gray-600"
                  }`}
                ></div>
              )}
            </div>
            <div className="ml-2 w-full">
              <div
                className={`${
                  step.value === orderStatus
                    ? "bg-primary-color p-2 text-white font-medium rounded-md -translate-y-3"
                    : ""
                }
                    ${
                      orderStatus === "CANCELLED" && step.value === orderStatus
                        ? "bg-red-500"
                        : ""
                    } w-full`}
              >
                <p className={``}>{step.name}</p>
                <p
                  className={` ${
                    step.value === orderStatus
                      ? "text-gray-200"
                      : "text-gray-500"
                  } text-xs`}
                >
                  {step.description}
                </p>
              </div>
            </div>
          </div>
        </>
      ))}
    </Box>
  );
};

export default OrderStepper;
