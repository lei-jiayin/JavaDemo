package likou;

/**
 * 83.删除排序链表中的重复元素 链表 循环 mianhua
 * @author adv
 * @date 2021/3/26 14:03
 */
public class RemoveDuplicatesfromSortedList {
    /**
     * 自写
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        if(temp == null){
            return head;
        }
        while(temp.next != null){
            int first = temp.val;
            while(temp.next.val == first){
                temp.next = temp.next.next;
                if(temp.next == null){
                    break;
                }
            }
            temp = temp.next;
            if(temp == null){
                break;
            }
        }
        return head;
    }

    /**
     * 标解
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/solution/shan-chu-pai-xu-lian-biao-zhong-de-zhong-49v5/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/




    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
