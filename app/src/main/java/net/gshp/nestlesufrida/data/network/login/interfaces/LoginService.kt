package net.gshp.nestlesufrida.data.network.login.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("service/rest/login/authentication")
    fun getToken(@Body tokenRequest: TokenRequest): Call<String>
}
data class TokenRequest(val brand:String)
/*
{
    "brand": "samsung",
    "cameraFront": true,
    "deviceId": "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBJZCI6IjE6MTM2NTA1MjI0NjgwOmFuZHJvaWQ6YWNkZjdhMWRkOWNmNGU2ZDA5NDg1ZCIsImV4cCI6MTY2OTA4MTI2NSwiZmlkIjoiZjc0cTBXSnlUT2VCcTdDYXFSR3dfaCIsInByb2plY3ROdW1iZXIiOjEzNjUwNTIyNDY4MH0.AB2LPV8wRgIhANBzlLuok7VqQ4JxUDSVwQvI-FzX1lSEhhQC1MSxK6EYAiEA6N5CVm6JLXWm8J5P0PlLhlLvEKu2aVMLCns9Mws5NVU",
    "imei": "0a3aafdeb3de41d3",
    "model": "SM-N975F",
    "os": "4.14.113-24230781",
    "osVersion": "12",
    "password": "Nesmovil2_2023",
    "phone": "",
    "username": "MXClaudiaS"
}
 */