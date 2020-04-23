/**
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
**/

/**
这道题给了一棵二叉树，问是否存在一条从跟结点到叶结点到路径，使得经过到结点值之和为一个给定的 sum 值，

这里需要用深度优先算法 DFS 的思想来遍历每一条完整的路径，也就是利用递归不停找子结点的左右子结点，

而调用递归函数的参数只有当前结点和 sum 值。首先，如果输入的是一个空结点，则直接返回 false，

如果如果输入的只有一个根结点，则比较当前根结点的值和参数 sum 值是否相同，若相同，返回 true，否则 false。

这个条件也是递归的终止条件。下面就要开始递归了，由于函数的返回值是 Ture/False，可以同时两个方向一起递归，

中间用或 || 连接，只要有一个是 True，整个结果就是 True。递归左右结点时，这时候的 sum 值应该是原 sum 值减去当前结点的值，参见代码如下：

**/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
