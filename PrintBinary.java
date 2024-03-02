/* MEDIUM
 * 
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree. The formatted layout matrix should be constructed using the following rules:

The height of the tree is height and the number of rows m should be equal to height + 1.
The number of columns n should be equal to 2height+1 - 1.
Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
Continue this process until all the nodes in the tree have been placed.
Any empty cells should contain the empty string "".
Return the constructed matrix res.

Input: root = [1,2]
Output: 
[["","1",""],
 ["2","",""]]

 */

 class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        int m = height + 1;
        int n = (int)Math.pow(2, height + 1) - 1;
        String[][] res = new String[m][n];
        List<List<String>> list = new ArrayList<>();

        for(String[] row: res) {
            Arrays.fill(row, "");
        }
        populateMatrix(res, root, 0, 0, n - 1);
        
        for(String[] row: res)
            list.add(Arrays.asList(row));

        return list;
    }

    private void populateMatrix(String[][] res, TreeNode node, int r, int start, int end) {
        if (node == null) return;
        int c = start + (end - start) / 2;
        res[r][c] = String.valueOf(node.val);
        populateMatrix(res, node.left, r + 1, start, c - 1);
        populateMatrix(res, node.right, r + 1, c + 1, end);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}