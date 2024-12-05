import React from 'react'
import ProfileFieldCard from '../../../component/ProfileFieldCard';
import { Divider } from '@mui/material';

const UserDetails = () => {
    const details = [
        {key: "Name", value: "Ben"},
        {key: "Email", value: "ben@gmail.com"},
        {key: "Mobile", value: "9453678235"},
    ];
  return (
    <div className='flex justify-center py-10'>
        <div className="w-full lg:w-[70%]">
            <div className="flex items-center pb-3 justify-between">
                <h1 className='text-2xl font-bold text-gray-600'>Personal Details</h1>
            </div>
            <div className=''>
                {details.map((detail)=>
                <div>
                    <ProfileFieldCard keys={detail.key} value={detail.value}/>
                    <Divider/>
                </div>
                )
                }
            </div>
        </div>
        
    </div>
  )
}

export default UserDetails