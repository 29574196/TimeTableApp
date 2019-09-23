const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')

//allows access to data passed from app
app.use(bodyParser.urlencoded({extended: false}))
//Global connection string
var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Timetable@324',
    database: 'timetable'
})


//Creating new user
app.post("/signup",(req,res)=>{

    var studentNo = req.body.add_student
    var studentEmail = req.body.add_email
    var studentPassword = req.body.add_password
    //var length = studentNo.length

    var queryString = "SELECT * from user where student_no = ?"
    //var queryString = "INSERT INTO user (student_no,user_email,user_password) VALUES (?,?,?)"
    connection.query(queryString,[studentNo],(err,results,fields)=>{
        if(err){
            console.log("failed to insert new user "+ err)
            res.sendStatus(500)
           return
        }
        if(results)
        {
            res.json('Duplicate student number')
            console.log("duplicate student number")
            return 
        }else
        {
            //Checking email unique
            queryString = "SELECT * from user where user_email = ?"
            connection.query(queryString,[studentEmail],(err,results,fields)=>{
                if(err){
                    console.log("failed to insert new user "+ err)
                    res.sendStatus(500)
                    return  
                }
                if(results)
                {
                    res.json('Duplicate email address')
                    console.log("duplicate email")
                    return
                }else
                {
                    queryString = "INSERT INTO user (student_no,user_email,user_password) VALUES (?,?,?)"
                    connection.query(queryString,[studentNo,studentEmail,studentPassword],(err,results,fields)=>{
                        if(err){
                            console.log("failed to insert new user "+ err)
                            res.sendStatus(500)
                            return  
                        }
                        console.log("new user created")
                        res.json('User created successfully')
                        res.end()
                    })  
                }   
            })
        }
    })
})

//Login with user password and student number
app.get("/login",(req,res)=> {

    const studentNo = req.body.username
    const studentPassword = req.body.password
    const queryString = "SELECT * FROM user where student_No = ? AND user_Password = ?"

    connection.query(queryString,[studentNo,studentPassword],(err,rows,fields)=>{
        if(err){
            console.log("failed to retrieve user: "+ err)
            res.sendStatus(500)
            return
        }
        console.log("fetched user successfully")
        res.json(rows)
    })
})
 
app.listen(3000,()=>{
    console.log("server is live on 3000")
})