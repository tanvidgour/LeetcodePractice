/* MEDIUM
 * 
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106

 */

 class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0)
         return 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1,n2) -> (n1 - n2));
        Arrays.sort(intervals, (int[] a,int[] b) -> (a[0] - b[0]));
        heap.add(intervals[0][1]);
        
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= heap.peek())
                heap.poll();
            heap.add(intervals[i][1]);
        }

        return heap.size();
    }
    
}