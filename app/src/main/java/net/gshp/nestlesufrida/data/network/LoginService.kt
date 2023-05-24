package net.gshp.nestlesufrida.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    suspend fun getToken() : String {
        return withContext(Dispatchers.IO){
            val response: String = "eyJraWQiOiJhZXMta2V5LXJlbW8iLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJHU0hQIiwiYXVkIjoiZGV2aWNlcyIsImp0aSI6ImhscWtCSWFWS1Baa3lhejgwcnpDbVEiLCJpYXQiOjE2ODQ4ODQ1MzMsInVzZXJuYW1lIjoiTVhDbGF1ZGlhUyIsIlVVSUQiOiIwYTNhYWZkZWIzZGU0MWQzIiwicGVyc29uIjoiQ2xhdWRpYSBTw6FuY2hleiBEdXLDoW4iLCJhdXRob3JpdGllcyI6WyJyZXN0X3Byb21vdG9yIiwidmlld19pbmRleCIsInZpZXdfZXN0YWRpc3RpY2FzIiwidmlld19nb2RfbW9kZV9tYXAiXSwicm9sZXMiOlsidXNlciIsImthbSJdLCJjb250cm9sIjoibmVzdGxlYWlxYS5nc2hwLWFwcHMuY29tIiwicm9sZUlkcyI6WyIxIiwiOCJdLCJ1c2VySWQiOiIxMTA4NyJ9.jKldCquA3s5JJI1zaZ7ajXM8KaMBoGvaB1oWS0D8zNk"
            response
        }
    }
}