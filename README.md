# Determine if two cities are connected

Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.
List of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

* Deploy as Spring Boot App and expose one endpoint:
http://localhost:8080/connected?origin=city1&destination=city2
    * respond ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.
    * any unexpected input should result in a ’no’ response.

### For Example: 
* city.txt content is:
    ```
    Boston, New York
    Philadelphia, Newark
    Newark, Boston
    Trenton, Albany
    ```

* http://localhost:8080/connected?origin=Boston&destination=Newark
    * should return yes
* http://localhost:8080/connected?origin=Boston&destination=Philadelphia
    * should return yes
* http://localhost:8080/connected?origin=Philadelphia&destination=Albany
    * should return no

