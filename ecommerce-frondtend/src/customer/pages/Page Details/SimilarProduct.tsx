import React from 'react'
import SimilarProductCard from './SimilarProductCard'

const SimilarProduct = () => {
  return (
    <div className='grid lg:grid-cols-5 md:grid-cols-3 sm:grid-cols-1 grid-cols-1 justify-between gap-4 gap-y-8 gap-x-5'>
        {[1,1,1,1,1,1,1,1,1,1,1].map((item)=><SimilarProductCard/>)}
        
    </div>
  )
}

export default SimilarProduct