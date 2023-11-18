package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 交易逆序对的总数_2 {

    /**
     * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
     * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     */

    /**
     * 首先你要知道归并排序的流程：
     *
     * 先将数组分成两部分，然后分别将这两部分排好序；
     * 然后开一个 helper 数组，指针 a 和 b 分别指向这两个部分的第一个元素；
     * 比较指针 a 所指元素与指针 b 所指元素的大小，将小的元素放进 helper 数组中；
     * 如果某个指针遍历到对应部分的末尾的话，则需要将另一个指针所指元素以及它后面的元素直接添加到 helper 数组中；
     * 最后再将 helper 数组中的数据拷贝到原数组中即可。
     */

    // 主要的时间开销发生在归并排序的过程中，而递归的深度是 O(log n)。在每一层递归中，我们需要合并两个长度为 n/2 的子数组，
    // 合并的过程需要 O(n) 的时间。因此，总的时间复杂度可以表示为 O(n log n)。
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int[] copy = new int[nums.length];
        return mergeSortAndCount(nums, copy, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int[] copy, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(nums, copy, left, mid) + mergeSortAndCount(nums, copy, mid + 1, right);

        int i = left, j = mid + 1;

        // 在归并排序的过程中，计数
        while (i <= mid) {
            while (j <= right && nums[i] > nums[j]) {
                j++;
            }
            count += j - (mid + 1);
            i++;
        }

        i = left;
        j = mid + 1;
        int k = left;

        // 计数完后，归并排序
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                copy[k++] = nums[i++];
            } else {
                copy[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            copy[k++] = nums[i++];
        }

        while (j <= right) {
            copy[k++] = nums[j++];
        }

        System.arraycopy(copy, left, nums, left, right - left + 1);

        return count;
    }


    public static void main(String[] args) {
        交易逆序对的总数_2 t = new 交易逆序对的总数_2();
        t.reversePairs2(new int[]{5, 3, 4, 1, 2});
    }
    public int reversePairs2(int[] nums) {
        int count = 0;
        Map<Integer, Integer> ranks = getRanks(nums);
        int length = nums.length;
        BinaryIndexedTree bit = new BinaryIndexedTree(length);
        for (int i = 0; i < length; i++) {
            int rank = ranks.get(nums[i]);
            count += bit.getCount(rank + 1, length - 1);
            bit.add(rank);
        }
        return count;
    }

    public Map<Integer, Integer> getRanks(int[] nums) {
        int length = nums.length;

        int[] sorted = new int[length];

        System.arraycopy(nums, 0, sorted, 0, length);

        Arrays.sort(sorted);

        Map<Integer, Integer> ranks = new HashMap<Integer, Integer>();

        for (int i = 0; i < length; i++) {
            int num = sorted[i];
            if (i == 0 || num > sorted[i - 1]) {
                ranks.put(num, i);
            }
        }
        return ranks;
    }
}

class BinaryIndexedTree {
    int length;
    int[] tree;

    public BinaryIndexedTree(int length) {
        this.length = length;
        this.tree = new int[length + 1];
    }

    public int getCount(int start, int end) {
        return getPrefixSum(end + 1) - getPrefixSum(start);
    }

    public void add(int index) {
        index++;
        while (index <= length) {
            tree[index]++;
            index += lowbit(index);
        }
    }

    private int getPrefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowbit(index);
        }
        return sum;
    }

    private static int lowbit(int x) {
        return x & (-x);
    }
}
