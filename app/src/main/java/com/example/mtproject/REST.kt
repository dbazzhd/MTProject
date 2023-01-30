import com.example.mtproject.Country
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import kotlin.reflect.typeOf


object REST {

    private val m_client: OkHttpClient = OkHttpClient()
    private lateinit var m_APIKey: String
    private lateinit var m_host: String
    private lateinit var m_gson: Gson

    fun initialize(pAPIKey: String, pHost: String) {
        m_APIKey = pAPIKey
        m_host = pHost
        m_gson = GsonBuilder().create()
    }

    fun getCountry(pCountry: String, onResponse: (country: Country) -> Unit) {
        val request = Request.Builder()
            .url("https://$m_host/v1/$pCountry")
            .get()
            .addHeader("X-RapidAPI-Key", m_APIKey)
            .addHeader("X-RapidAPI-Host", m_host)
            .build()

        m_client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val country: Country = m_gson.fromJson(response.body()!!.string(), Country::class.java)
                onResponse(country)
            }
        })
    }

    fun getCountries(onResponse: (countries: List<Country>) -> Unit) {
        val request = Request.Builder()
            .url("https://$m_host/v1")
            .get()
            .addHeader("X-RapidAPI-Key", m_APIKey)
            .addHeader("X-RapidAPI-Host", m_host)
            .build()

        m_client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val countries: List<Country> = m_gson.fromJson(response.body()!!.string(), object: TypeToken<List<Country?>?>() {}.type)
                onResponse(countries)
            }
        })
    }
}