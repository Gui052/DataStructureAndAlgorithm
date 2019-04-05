package Algorithm.Search;

import java.util.LinkedList;
import java.util.Queue;

/**二分搜索树<br/>
 * 特点：每一个结点的键值大于做孩子，小于右孩子。以左右孩子为根的子树仍然是二分搜索树。不一定是完全二叉树。包含递归特性<br/>
 * 缺点：如果插入的数据是顺序的，二分查找树将退化成链表。所以有引入平衡二叉树，比如红黑树，AVL树
 * @author lan
 * @version 1.0.0
 * @since 2019/3/20  14:06
 */
public class BinarySearchTree<K extends Comparable<K>,V extends Comparable<V>> {
    class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;
    private int count;

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 插入
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    /**
     * 向以node为根的二叉搜索树中，插入结点（key，value）的递归实现<br/>
     * 从根节点开始插入，如果没有结点，则生成新节点并返回，如果这个值比根节点小，往左孩子插，比根节点大，往右孩子插，以此类推。
     * 如果遇到键相同，则覆盖
     * @param node
     * @param key
     * @param value
     * @return 插入新节点后的二叉搜索树的根
     */
    private Node insert(Node node, K key, V value) {
        if (node == null) { //没有节点返回新创建的node
            count++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node; //递归回去的时候这个就会是第一次传进来的node
    }

    public boolean contain(K key) {
        return contain(root, key);
    }
    //查找以node为根的二叉搜索树中是否有键值为key的结点
    private boolean contain(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }
    }

    public V search(K key) {
        return search(root, key);
    }

    //寻找node为根的二叉搜索树种以key所对应的value
    private V search(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }
    //前序递归遍历以node为节点的二叉搜索树
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }
    //中序递归遍历以node为节点的二叉搜索树
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }
    //后序递归遍历以node为节点的二叉搜索树
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    /**
     * 层次遍历
     */
    public void leverOrder() {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {

            Node node = ((LinkedList<Node>) nodes).removeFirst();

            System.out.println(node.key);

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    /**
     * 寻找二叉搜索树最小结点
     * @return
     */
    public K minimum() {
        assert (count != 0);
        Node minNode = minimum(root);
        return minNode.key;
    }
    //递归查找最小值
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二叉搜索树最大结点
     * @return
     */
    public K maxmum() {
        assert (count != 0);
        Node maxNode = maxmum(root);
        return maxNode.key;
    }
    //递归查找最小值
    private Node maxmum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    /**
     * 移除最小结点
     */
    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }
    //删除掉node为根的二分搜索树中最小的结点
    private Node removeMin(Node node) {
        if (node.left == null) { //说明已经是最小的了。
            Node rightNode = node.right;
            count--;
            return rightNode; //如果删除了最小结点，最小节点的右孩子就是它父节点的左孩子
        }
        node.left = removeMin(node.left); //如果没有发生交换那么左结点依然是左结点
        return node;
    }

    /**
     * 移除最大结点
     */
    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除特定key的结点
     * @param key
     */
    public void remove(K key) {
        remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { //key==node.key
            if (node.left == null) {
                Node rightNode = node.right;
                count--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                count--;
                return leftNode;
            }
            //删除一个左右子树都不为null的结点，是使用这棵树的右子树的最小值代替此位置。
            //在Java中JVM自动维护对象，所以不需要进行释放操作，也就不需要记录被删除的结点和复制右子树的最小节点。
            Node successor = minimum(node.right);

            successor.right = removeMin(node.right);  //Java中此依然被引用，不需要进行拷贝.
            //count已经在这里面减一了，这里不需要再动了
            successor.left = node.left;
            return successor;
        }
    }
}
