package sjjg.hashtable;

/**
 * 哈希表的实现 拉链法 解决哈希冲突
 * @author adv
 * @date 2020/9/3 11:06
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new Emp(1, "xw"));
        hashTable.add(new Emp(2, "xm"));
        hashTable.add(new Emp(3, "qll"));
        hashTable.add(new Emp(4, "hf"));
        hashTable.add(new Emp(8, "ls"));
        hashTable.list();
        Emp emp = hashTable.searchEmp(5);
        System.out.println(emp);
    }
}

/**
 * 创建hashtable管理多条链表
 */
class HashTable{
    private EmpLinkedList[] empLinkedListArray;
    // 表示有多少条链表
    private int size;

    // 构造器
    public HashTable(int size){
        this.size = size;
        // 初始化 了链表的数组 链表未初始化
        this.empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //查找
    public Emp searchEmp(int id){
        int no = hashFun(id);
        Emp emp = empLinkedListArray[no].findEmpById(id);
        if (emp != null){
            return emp;
        }else {
            System.out.println("未从哈希表中找到该雇员！");
        }
        return null;
    }

    // 添加
    public void add(Emp emp){
        // 根据员工ID判断存放数组链表的索引
        int empLinkListNo = hashFun(emp.id);
        // 将emp添加到对应得链表中
        EmpLinkedList empLinkedList = empLinkedListArray[empLinkListNo];
        empLinkedList.add(emp);
    }

    //遍历hashtable
    public void list(){
        for (int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
            System.out.println();
        }
    }

    // 编写散列函数 取模法
    public int hashFun(int id){
        return id % size;
    }
}

/**
 * 表示链表
 */
class EmpLinkedList{
    // 增加头指针 指向第一个Emp
    private Emp head;
    //添加雇员
    // 1.假定添加雇员时 id 是自增长的，即id的分配总是从小到大
    // 因此我们将该雇员直接加入到本链表的最后。
    public void add(Emp emp){
        // 如果链表为空 即为添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        Emp tmpe = head;
        while (true){
            if (tmpe.next == null){
                break;
            }
            tmpe = tmpe.next;
        }
        tmpe.next = emp;
    }

    // 遍历链表的成员信息
    public void list(int no){
        if (head == null){
            System.out.println("链表为空！");
            return;
        }
        System.out.println("当前第"+no+"链表的信息为：");
        Emp curEmp = head;
        while (true){
            if (curEmp == null){
                break;
            }
            System.out.println("id为："+ curEmp.id +"，"+"name为：" + curEmp.name);
            curEmp = curEmp.next;
        }
    }

    // 根据id来查找雇员
    public Emp findEmpById(int id){

        if (head == null){
            System.out.println("链表为空！");
            return null;
        }

        Emp curEmp = head;
        while (true){
            if (curEmp == null){
                System.out.println("未找到雇员信息！");
                return null;
            }
            if (id == curEmp.id){
                return curEmp;
            }
            curEmp = curEmp.next;
        }
    }

}

/**
 * 表示一个雇员节点
 */
class Emp{
    public int id;
    public String name;
    public Emp next;// 默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
