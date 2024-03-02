/* MEDIUM
 * 
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */

 class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> allpaths = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        backtrack(0,graph,graph.length-1, allpaths,list);
        return allpaths;
    }

    public void backtrack(int source,int[][] graph,int target, List<List<Integer>> allpaths, List<Integer> paths){
        if(source == target)
            allpaths.add(new ArrayList<>(paths));
        for(int nei: graph[source]){
            paths.add(nei);
            backtrack(nei, graph, target, allpaths, paths);
            paths.remove(paths.size()-1);
            
        }
    }
}