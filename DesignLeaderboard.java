/* MEDIUM
 * 
 * class UndergroundSystem {
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
 */

 class Leaderboard {
    Map<Integer,Integer> scores;
    TreeMap<Integer,Integer> sortedScores;

    public Leaderboard() {
        scores = new HashMap<>();
        sortedScores = new TreeMap<>(Collections.reverseOrder());
    }
    
    public void addScore(int playerId, int score) {
        if(!scores.containsKey(playerId)){
            scores.put(playerId,score);
            sortedScores.put(score,sortedScores.getOrDefault(score, 0) + 1);
        }else{
            int previousScore = scores.get(playerId);
            int newScore = previousScore + score;
            int playerCount = sortedScores.get(previousScore);
            scores.put(playerId,newScore);

            if(playerCount == 1)
                sortedScores.remove(previousScore);
            else
                sortedScores.put(previousScore, playerCount - 1);

            sortedScores.put(newScore, sortedScores.getOrDefault(newScore, 0) + 1);
        }
    }
    
    public int top(int K) {
        int sum = 0;

        for(Map.Entry<Integer,Integer> entry : sortedScores.entrySet()){
            int score = entry.getKey();
            int players = entry.getValue();
            if( K <= players){
                sum += K * score;
                break;
            } else{
                sum += players * score;
                K -= players;
            }
        }
        return sum;

    }
    
    public void reset(int playerId) {
        int prevscores = scores.get(playerId);
        sortedScores.put(prevscores,sortedScores.get(prevscores) - 1);
        if(sortedScores.get(prevscores) == 0)
            sortedScores.remove(prevscores);
        scores.remove(playerId);
    }
}