/* MEDIUM 
 * 
 * Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/

class DecodeStr {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int num = 0;

        for(char ch: s.toCharArray()){
            if(Character.isDigit(ch))
                num = num * 10 + ch - '0';
            else if(ch == '['){
                countStack.push(num);
                stringStack.push(currentString);
                num = 0;
                currentString = new StringBuilder();
            }else if(ch == ']'){
                int k = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                while(k-- > 0)
                    decodedString.append(currentString);
                currentString = decodedString;
            }else
                currentString.append(ch);
        }

        return currentString.toString();
    }
}