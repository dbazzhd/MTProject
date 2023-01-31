import API.m_APIKey
import API.m_host
import com.example.mtproject.Country
import com.example.mtproject.NetworkCountry
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import retrofit2.Call
import retrofit2.http.Headers
import java.io.IOException



interface APIService {
    @Headers("X-RapidAPI-Key: " + m_APIKey, "X-RapidAPI-Host: " + m_host)
    @GET("v1")
    suspend fun getCountries(): List<NetworkCountry>
}

object API {
    const val m_APIKey: String = "7aba35782bmsh2a3eadbb2d7d16cp1de603jsnd2acc5b0a930"
    const val m_host: String = "covid-19-tracking.p.rapidapi.com"


    private val m_moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val m_retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(m_moshi))
        .baseUrl("https://${m_host}/")
        .build()

    val retrofitService: APIService = m_retrofit.create(APIService::class.java)
}