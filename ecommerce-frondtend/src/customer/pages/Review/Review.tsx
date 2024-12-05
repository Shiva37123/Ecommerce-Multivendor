import React from "react";
import ReviewCard from "./ReviewCard";
import { Box, Divider, LinearProgress, Rating } from "@mui/material";
import { normalize } from "path";

type Reviews = {
  label: string; // Category (e.g., Excellent, Very Good)
  color: String;
  value: number; // Star rating
  count: number; // Number of reviews for this rating
};

const review: Reviews[] = [
  { label: "Excellent", color:"#14958f", value: 5, count: 120 },
  { label: "Very Good", color:"#14958f", value: 4, count: 90 },
  { label: "Good",color:"#72bfbc", value: 3, count: 45 },
  { label: "Average",color:"#fcb301", value: 2, count: 20 },
  { label: "Poor",color:"#f16565", value: 1, count: 10 },
];

// Calculate total reviews
const totalReviews = review.reduce((sum, r) => sum + r.count, 0);
const totalRatings = review.reduce((sum, r) => sum + r.value * r.count, 0);
const totalCount = review.reduce((sum, r) => sum + r.count, 0);

const averageRating = totalRatings / totalCount;

const Review = () => {
  return (
    <div className="p-5 lg:px-20 flex flex-col lg:flex-row gap-20 mt-[70px]">
      <section className="w-full md:w-1/2 lg:w-[30%] space-y-2">
        <img src="\images\tshirt\white_tshirt_2.jpg" alt="" />
        <div>
          <div>
            <p className="font-bold text-xl">Raam Clothing</p>
            <p className="text-lg text-gray-600">Men's White T-Shrt</p>
          </div>
          <div className="px-3">
            <div className="price flex items-center gap-3 mt-5 text-2xl">
              <span className="font-sans text-gray-800">₹ 400</span>
              <span className="line-through text-gray-400">₹ 999</span>
              <span className="text-primary-color font-semibold">60%</span>
            </div>
          </div>
        </div>
      </section>
      <section className="space-y-5 w-full">
        <section className="space-y-7">
          <p className="font-semibold text-lg">Review & Rating</p>
          <div className='ml-10 w-full'>
            <div className="flex gap-6 mb-8">
              <Rating readOnly value={averageRating} precision={0.5} />
              <p>Ratings</p>
            </div>
            {review.map((item) => (
              <div
                key={item.label}
                className="flex flex-row items-center justify-between border-b border-gray-200 pb-2 gap-16"
              >
                <div className="flex flex-row items-center gap-4">
                  <span className="font-semibold w-24">{item.label}</span>

                  {/* <Rating readOnly value={item.value} precision={0.5} /> */}
                </div>

                <div className="flex items-center flex-1 gap-32 space-x-8">
                  <Box  sx={{ width: "60%", mr: 1 }}>
                    <LinearProgress
                      variant="determinate"
                      value={(item.count / totalReviews) * 100}
                      sx={{
                        height: 5,
                        borderRadius: 4,
                        backgroundColor: "#e0e0e0", // Optional: Unfilled background
                        "& .MuiLinearProgress-bar": {
                          backgroundColor: item.color as string, // Filled color (use your primary color)
                        },
                      }}
  
                    />
                  </Box>
                  <span className="text-gray-500 ">{`${item.count} reviews`}</span>
                </div>
              </div>
            ))}
          </div>
        </section>
        {[1, 1, 1, 1, 1, 1, 1, 1].map((item) => (
          <div className="space-y-3">
            <ReviewCard />
            <Divider />
          </div>
        ))}
      </section>
    </div>
  );
};

export default Review;
