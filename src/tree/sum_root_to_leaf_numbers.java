/**
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
**/

/**
这道求根到叶节点数字之和的题跟之前的求 Path Sum 很类似，都是利用DFS递归来解，这道题由于不是单纯的把各个节点的数字相加，

而是每遇到一个新的子结点的数字，要把父结点的数字扩大10倍之后再相加。如果遍历到叶结点了，就将当前的累加结果sum返回。

如果不是，则对其左右子结点分别调用递归函数，将两个结果相加返回即可，参见代码如下：

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
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        
        int sum = helper(root, 0);
        
        return sum;
    }
    
    private int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        
        sum = sum * 10 + root.val;
        
        if (root.left == null && root.right == null) return sum;
        
        return helper(root.left, sum) + helper(root.right, sum);
    }
}