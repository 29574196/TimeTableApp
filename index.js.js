const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')



 
app.listen(3000,()=>{
    console.log("server is live on 3000")
})