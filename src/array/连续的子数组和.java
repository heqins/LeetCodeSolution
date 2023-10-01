package array;

import java.util.HashSet;
import java.util.Set;

public class 连续的子数组和 {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     *
     * 子数组大小 至少为 2 ，且
     * 子数组元素总和为 k 的倍数。
     * 如果存在，返回 true ；否则，返回 false 。
     *
     * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int N = nums.length, cache = 0;
        int[] sum = new int[N+1];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            sum[i+1] = sum[i] + nums[i];
            int res = k == 0 ? sum[i+1] : sum[i+1] % k;
            if (set.contains(res)) {
                return true;
            }
            set.add(cache);
            cache = res;
        }

        return false;
    }
}
