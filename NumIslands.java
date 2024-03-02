/* MEDIUM
 * 
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

 */

 class NumIslands {

    public int numIslands(char[][] grid) {
  
      if (grid == null || grid.length == 0) {
        return 0;
      }
  
      int m = grid.length;
      int n = grid[0].length;
      int count = 0;
  
      for(int r = 0; r < m; r++){
        for(int c = 0; c < n; c++){
          if(grid[r][c] == '1'){
            count++;
            dfs(r,c,grid);
          }
        }
      }
      return count;
      
    }
  
    public void dfs(int i, int j, char[][] grid){
      int m = grid.length;
      int n = grid[0].length;
  
      if(i < 0 || j < 0 || i >= m || j >= n ||  grid[i][j] == '0'){
        return;
      }
      
        grid[i][j] = '0';
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
  }