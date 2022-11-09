const express = require ('express')
const app = express()

app.get("/locations", (req,res) => {
    res.json({"locations":["Boston", "Lowell", "Cambridge"]})
})

app.get("/restaurants", (req,res) => {
    res.json({"restaurants":["Rest1", "Rest2", "Rest3"]})
})


app.listen(5000,() => {console.log("Server started on port 5000")} )