# country-fetch-api

## About
The country data has to be fetched from this REST API https://gitlab.com/restcountries/restcountries: and show the below results
- Sorted list of countries by population density in descending order.
- Country in Asia containing the most bordering countries of a different region.


## Features

This API provides HTTP endpoint's for the following:

* Sorted list of countries by population density in descending order : GET/api/country/getPopulationByDensity
* Country in Asia containing the most bordering countries of a different region : GET/api/country/getMostBordersCountry 



### Compile, Test and Package

The API also was developed to run with an 'jar'. In order to generate this 'jar', you should run:

mvn install

It will clean, compile, test and generate a 'jar' at target directory, e.g. 'country-1.0.0.jar'


### Run

In order to run the API, run the jar simply as following:

java -jar country-1.0.0.jar --spring.profiles.active=prd
    
or

java -jar country-1.0.0.jar --spring.profiles.active=dev

By default, the API will be available at [http://localhost:8080/api/country/]
