import React from 'react'
import { menLevelThree } from '../../data/category/levelThree/menLevelThree'
import { womenLevelThree } from '../../data/category/levelThree/womenLevelThree'
import { electronicsLevelThree } from '../../data/category/levelThree/electronicsLevelThree'
import { furnitureLevelThree } from '../../data/category/levelThree/furnitureLevelThree'
import { Box } from '@mui/material'
import { useNavigate } from 'react-router-dom'

// const categoryTwo = {
//     men:menLevelTwo,
//     women:womenLevelTwo,
//     home_furniture:furnitureLevelTwo,
//     electronics:electronicsLevelTwo,
// }

const categoryThree:{[key:string]:any[]} = {
    men:menLevelThree,
    women:womenLevelThree,
    electronics:electronicsLevelThree,
    home_furniture:furnitureLevelThree,

}

// Define the types for data
interface LevelThreeCategory {
    name: string;
    categoryId: string;
    parentCategoryId: string;
    level: number;
}

interface LevelTwoCategory {
    name: string;
    categoryId: string;
    level: number;
    levelTwoCategory: LevelThreeCategory[];
}

const CategorySheet = ({selectedCategory, setShowSheet}:any) => {
  const navigate = useNavigate();
  
  return (
    <Box sx={
        {zIndex:2}
    } className='bg-white shadow-lg lg:h-[500px] overflow-y-auto'>
        <div className='flex text-sm flex-wrap '>
            {
                categoryThree[selectedCategory]?.map((category: LevelTwoCategory, index)=>
                <div className={`p-8 lg:w-[20%] ${index%2===0?"bg-slate-50":"bg-white"}`}>
                    <p className='text-primary-color mb-5 font-semibold'>{category.name}</p>
                    <ul className='space-y-3'>
                        {category.levelTwoCategory.map((subCategory) => (
                            <li onClick={()=>navigate("products/"+subCategory.categoryId)} key={subCategory.categoryId} className='hover:text-primary-color cursor-pointer'>
                                {subCategory.name}
                            </li>
                        ))}

                        
                    </ul>
                </div>)
            }

        </div>


    </Box>
  )
}

export default CategorySheet