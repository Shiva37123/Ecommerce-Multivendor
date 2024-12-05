/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors:{
        "primary-color": "#3a06bd",
        "secondary-color": "#c7d4f7"
      }
    },
  },
  plugins: [],
}