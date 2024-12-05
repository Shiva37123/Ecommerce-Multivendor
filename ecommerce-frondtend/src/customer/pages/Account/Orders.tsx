import React from 'react'
import OrderItem from './OrderItem'
import { useNavigate } from 'react-router-dom';

const Orders = () => {
  const navigate = useNavigate();

  return (
    <div className='text-sm min-h-screen'>
        <div className='pb-5'>
            <h1 className='font-semibold'>All Orders</h1>
            <p>from anytime</p>
        </div>
        <div onClick={()=>navigate("/account/order/2/3")} className='space-y-2'>
            {
                [1,1,1,1,1,1].map((item)=><OrderItem/>)
            }
        </div>
        
    </div>
  )
}

export default Orders