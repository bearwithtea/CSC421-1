import java.util.HashMap;

/**
 * A class that determines the expected # of tokens for the player at the game's end,
 * using a top-down, divide & conquer approach.
 * @author Owen McGrath
 * @version 11/19/2024
 */
public class 
Evensies 
{
  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a top-down, divide & conquer approach.
   *  @param tokens the number of tokens currently held by the player
   *  @param rounds the number of rounds left to be played
   *  @return the expected # of tokens for the player at the game's end
   */
  public static double 
  expectedTopDown(int tokens, int rounds)   
  {
    //if the player has no tokens or no rounds left, return the number of tokens
    if (tokens <= 0 || rounds <= 0)
    {
      return tokens; 
    }
    else
    {
      //everytime it is in the map, just return the value, otherwise calculate it and save it to the map, for caching
      return  /*win*/ (expectedTopDown(tokens + 1, rounds - 1) * 16/36.0) + 
              /*loss*/ (expectedTopDown(tokens - 1, rounds - 1) * 16/36.0) + 
              /*l+b*/ (expectedTopDown(tokens - 2, rounds - 1) * 2/36.0) + 
              /*w+b*/ (expectedTopDown(tokens, rounds - 1) * 2/36.0);
    }  
  }

  public static double expectedBottomUp(int tokens, int rounds) {
    double[][] roundTokenMap = new double[rounds + 1][tokens + 2 * rounds + 1];  //create a massive array to account for winning streaks
    
    //initialize the base case
    //this accounts for the player winning/losing every round by multiplying rtounds by 2
    for (int t = 0; t <= tokens + 2 * rounds; t++) {
        roundTokenMap[0][t] = t;
    }
    
    for (int r = 1; r <= rounds; r++) { //every round
        for (int t = 0; t <= tokens + 2 * rounds - r; t++) { //accounts for a decrease in tokens as the game progresses
            if (t <= 1) {
                roundTokenMap[r][t] = 0;
            } else {
                roundTokenMap[r][t] = /*win*/(roundTokenMap[r-1][t+1] * 16/36.0) + //row above, column to the right
                                    /*loss*/ (roundTokenMap[r-1][t-1] * 16/36.0) + //row above, column to the left
                                    /*w+b*/  (roundTokenMap[r-1][t] * 2/36.0)    + //row above, same column      
                                    /*l+b*/  (roundTokenMap[r-1][t-2] * 2/36.0);   //row above, two columns to the left
            }
        }
    }
  return roundTokenMap[rounds][tokens];
}

  /**
   * Calls the expectedCachingCalculator method, which uses caching to determine the expected
   * # of tokens for the player at the game's end. This is done to avoid redeclaring the cache map
   * each time the method is called.
   * @param tokens the number of tokens currently held by the player
   * @param rounds the number of rounds left to be played
   * @return the expected # of tokens for the player at the game's end
   */
  public static double expectedCaching(int tokens, int rounds) 
  {
    return expectedCachingCalculator(tokens, rounds, new HashMap<String, Double>());
  }

  /**
   * Determines the expected # of tokens for the player at the game's end,
   * using a top-down, divide and conquer approach, combined with caching.
   * @param tokens the number of tokens currently held by the player
   * @param rounds the number of rounds left to be played
   * @param cache a map that stores the expected # of tokens for each round and token value
   * @return the expected # of tokens for the player at the game's end
   */
  public static
  double expectedCachingCalculator(int tokens, int rounds, HashMap<String, Double> cache)
  {
    String key = rounds + "," + tokens;

    if (tokens <= 0 || rounds <= 0)
    {
      return tokens; 
    }

    //if the key has already been found, just return that value
    if (cache.containsKey(key))
    {
      return cache.get(key);
    }

    //otherwise, store it and return the value
    double cachedValue =  /*win*/ (expectedCachingCalculator(tokens + 1, rounds - 1, cache) * 16/36.0) + 
                          /*loss*/ (expectedCachingCalculator(tokens - 1, rounds - 1, cache) * 16/36.0) + 
                          /*l+b*/ (expectedCachingCalculator(tokens - 2, rounds - 1, cache) * 2/36.0) + 
                          /*w+b*/ (expectedCachingCalculator(tokens, rounds - 1, cache) * 2/36.0);
    cache.put(key, cachedValue);
    return cachedValue;
  }
}