import { Radio } from '@mui/material'
import React from 'react'

const AddressCard = () => {
    const handleChange = (e:any) => {
        console.log(e.target.checked)
    }
  return (
    <div className='p-5 border rounded-md flex'>
        <div>
            <Radio
            checked={true}
            onChange={handleChange}
            value=""
            name='radio-button'
            />
        </div>
        <div className='space-y-3 pt-3'>
            <h1>Ben</h1>
            <p className='w-[320px]'>Near Railway Station, Margao, South Goa, Goa - 403411</p>
            <p><strong>Mobile : </strong> 9435214532</p>

        </div>
    </div>
  )
}

export default AddressCard