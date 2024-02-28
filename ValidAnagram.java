/* EASY
 * 
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

 */

 class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int[] occurence = new int[26];

        if(s.length() != t.length())
            return false;
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            occurence[ch - 'a']++;
        }

        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            occurence[ch - 'a']--;
            if(occurence[ch - 'a'] < 0)
                return false;
        }

        return true;
    }
    // ans to the follow up - use hashmap instead of fixed counter
}