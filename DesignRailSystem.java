/* MEDIUM 
 * 
 * 
 * An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.

Implement the UndergroundSystem class:

void checkIn(int id, string stationName, int t)
A customer with a card ID equal to id, checks in at the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card ID equal to id, checks out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time it takes to travel from startStation to endStation.
The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.

 

Example 1:

Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
*/

class UndergroundSystem {
    Map<Integer, Pair<String,Integer>> checkin;
    Map<String, Pair<Double,Double>> trips;

    public UndergroundSystem() {
        checkin = new HashMap<>();
        trips = new HashMap<>();    
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkin.put(id, new Pair<>(stationName,t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String,Integer> checkinPair = checkin.get(id);
        String startStation = checkinPair.getKey();
        Integer checkinTime = checkinPair.getValue();

        String tripPath = startStation + "->" + stationName;
        Pair<Double,Double> tripStat = trips.getOrDefault(tripPath, new Pair<>(0.0,0.0));
        Double totalTripTime = tripStat.getKey();
        Double totalTrips = tripStat.getValue();

        double tripTime = t - checkinTime;
        trips.put(tripPath, new Pair<>(totalTripTime + tripTime, totalTrips + 1));

        checkin.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String tripPath = startStation+ "->" + endStation;
        Pair<Double,Double> tripData = trips.get(tripPath);
        Double totalTime = tripData.getKey();
        Double totalTrips = tripData.getValue();
        
        return totalTime / totalTrips;
    }
}