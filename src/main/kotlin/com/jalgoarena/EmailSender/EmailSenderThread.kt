package com.jalgoarena.EmailSender

import com.jalgoarena.domain.User
import com.jalgoarena.web.HttpUsersClient
import java.time.LocalDateTime
import com.jalgoarena.web.UsersClient;
import com.netflix.discovery.EurekaClient
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class EmailSenderThread(pStartDate: LocalDateTime, pSendPeriodInSeconds : Long) : Thread(){
    val limit = 50
    val startDate = pStartDate
    val sendPeriodInSeconds = pSendPeriodInSeconds
    var lastSendDate = pStartDate


    public override fun run(){
        println("New thread started: ${Thread.currentThread()} | ${startDate}")
        var i = 0
        while(true) {
            SendMail(lastSendDate, sendPeriodInSeconds)
            logInfo(0)
            Thread.sleep(1000)
        }
    }

    private fun GetAllUsers(){
        val usersClient : UsersClient
        var users : List<User>
        val url = "http://localhost:5002/problems"
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection){
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use{
                val response = StringBuffer()
                var inputLine = it.readLine()
                while(inputLine != null){
                    inputLine = it.readLine()
                    response.append(inputLine)
                }
                println(response.toString())
            }
        }
    }

    private fun SendMail(pLastSendDate: LocalDateTime, pSendPeriodInSeconds: Long){
        var currentDate = LocalDateTime.now()
        var dateMinusOneDay = currentDate.minusSeconds(pSendPeriodInSeconds)
        if(dateMinusOneDay > pLastSendDate){
            //TODO: Send mail
            println(">>>> Mail will be send <<<<<")
            lastSendDate = currentDate
            GetAllUsers()
        }
    }

    private fun logInfo(i: Int){
        println(">>>>> I'm working... : ${i}")
    }


}