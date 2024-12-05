import { Delete } from "@mui/icons-material";
import { Avatar, Box, Grid2, IconButton, Rating } from "@mui/material";
import { red } from "@mui/material/colors";
import React from "react";

const ReviewCard = () => {
  return (
    <div className="flex justify-between">
      <Grid2 container spacing={9} >
        <Grid2 size={{ xs: 1 }}>
          <Box>
            <Avatar
              className="text-white"
              sx={{ width: 56, height: 56, bgcolor: "#9155FD" }}
            >
              B
            </Avatar>
          </Box>
        </Grid2>
        <Grid2 size={{ xs: 9 }}>
          <div className="space-y-2 ">
            <div>
              <p className="font-semibold text-lg">Ben</p>
              <p className="opacity-70">2024-11-23 T-22:20:07.478333</p>
            </div>
          </div>
          <Rating
          readOnly
          value={4.5}
          precision={0.5}/>
          <p>value for money product, great product</p>
          <div>
            <img className="w-24 h-24 object-cover" src="\images\tshirt\white_tshirt_1.jpg" alt="" />
          </div>

        </Grid2>
      </Grid2>
      <div>
        
      <IconButton>
        <Delete sx={{color:red[700]}}/>
      </IconButton>
      </div>
    </div>
  );
};

export default ReviewCard;
