const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')

//allows access to data passed from app
app.use(bodyParser.urlencoded({extended: false}))

//Creating new user
app.post("/signup",(req,res)=>{
    const connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'Timetable@324',
        database: 'timetable'
    })

    const studentNo = req.body.add_student
    const studentEmail = req.body.add_email
    const studentPassword = req.body.add_password

    const queryString = "INSERT INTO user (student_no,user_email,user_password) VALUES (?,?,?)"
    connection.query(queryString,[studentNo,studentEmail,studentPassword],(err,results,fields)=>{
        if(err){
            console.log("failed to insert new user "+ err)
            res.sendStatus(500)
            return
        }

        console.log("new user created")
        res.end()
    })

})

 
app.listen(3000,()=>{
    console.log("server is live on 3000")
})