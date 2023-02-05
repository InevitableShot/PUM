package pl.edu.uwr.restcountriesapp.api

import retrofit2.http.GET

interface RestCountriesApi {
    @GET("v3/all?fields=name,capital,flag")
    suspend fun getCountries() : List<Country>
}