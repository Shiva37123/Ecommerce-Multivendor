import React from 'react';
import './App.css';
import './index.css'
import Navbar from './customer/components/Navbar';
import { ThemeProvider } from '@mui/material';
import customTheme from './Theme/customTheme';
import Product from './customer/pages/Products/Product';
import ProductDetails from './customer/pages/Page Details/ProductDetails';
import Home from './customer/pages/Home/Home';
import Review from './customer/pages/Review/Review';
import Cart from './customer/pages/Cart/Cart';
import Checkout from './customer/pages/Checkout/Checkout';
import Account from './customer/pages/Account/Account';
import { Route, Routes } from 'react-router-dom';
import BecomeSeller from './customer/pages/Become Seller/BecomeSeller';

function App() {
  return (

    <ThemeProvider theme={customTheme}>
      <div>
        
        {/* <Home/> */}
        {/* <Product/> */}
        {/* <ProductDetails/> */}
        {/* <Review/> */}
        {/* <Cart/> */}
        {/* <Checkout/> */}
        {/* <Account/> */}
        <Navbar/>
        <Routes>

        <Route path="/" element={<Home/>}/>
        <Route path="/products/:category" element={<Product/>}/>
        <Route path="/product-details/:categoryId/:name/:productId" element={<ProductDetails/>}/>
        <Route path="/reviews/:productId" element={<Review/>}/>
        <Route path="/cart" element={<Cart/>}/>
        <Route path="/checkout" element={<Checkout/>}/>
        <Route path="/account/*" element={<Account/>}/>
        <Route path="/become-seller" element={<BecomeSeller/>}/>

        </Routes>
      </div>
    </ThemeProvider>

      

  );
}

export default App;
