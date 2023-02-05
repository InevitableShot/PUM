package pl.edu.uwr.restcountriesapp.api

data class Country(
    var name: Name,
    var capital: List<String>,
    var flag: String
)

data class Name(
    val common: String
)