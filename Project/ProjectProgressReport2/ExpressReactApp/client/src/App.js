import React, {useEffect, useState} from 'react'

function App() {

  const [backendData, setBackendData] = useState([{}])
  const [backendData2, setBackendData2] = useState([{}])
  useEffect(()=> {
    fetch("/locations").then(
      response => response.json()
    ).then(
      data => {
          setBackendData(data)
      }
    )

  },[])
  useEffect(()=> {
    fetch("/restaurants").then(
      response => response.json()
    ).then(
      data2 => {
          setBackendData2(data2)
      }
    )

  },[])

  return (
    <div>
    
      {(typeof backendData.locations === 'undefined') ? (
        <p>Loading....</p>
      ):(
       backendData.locations.map((locations,i) =>
       (
        <p key ={i}> {locations}</p>
       )
      ))
       }
         {(typeof backendData2.restaurants === 'undefined') ? (
        <p>Loading....</p>
      ):(
       backendData2.restaurants.map((restaurants,i) =>
       (
        <p key ={i}> {restaurants}</p>
       )
      ))
       }
    </div>

    
  )}


export default App