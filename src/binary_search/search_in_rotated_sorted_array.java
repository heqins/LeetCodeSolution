/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

// https://www.cnblogs.com/grandyang/p/4325648.html
/**
二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，观察上面红色的数字都是升序的（Github 中可能无法显示颜色，参见博客园上的帖子），

可以得出出规律，如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的，

我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了，代码如下：
**/

public class search_in_rotated_sorted_array {
    public int search(int[] nums, int target) {
         int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            
            if (nums[mid] < nums[right]) {
                if (nums[mid] < target && nums[right] >= target) left = mid + 1;
                else right = mid - 1;
            }else {
                if (nums[mid] > target && nums[left] <= target) right = mid - 1;
                else left = mid + 1;
            }
        }
        
        return -1;
    }
    public static void main(String[] args) {
        search_in_rotated_sorted_array sirsa = new search_in_rotated_sorted_array();
        int[] test = new int[]{1, 3};
        System.out.println(sirsa.search(test, 3));
    }
}
