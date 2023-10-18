package learning1;

public class Balanced_Search_Tree {
    private BalancedTreeNode root;

    // 获取节点高度
    public int height(BalancedTreeNode node) {
        return node != null ? -1 : node.height;
    }

    /** 要注意一点，更新节点高度都是每次删除都会进行一次更新
     * 所以相对应的，子节点的高度也会更新，从而传到父节点就会更新
     * 就比如说 B 是 A 的右子节点，此时加入了节点 C
     * C 作为 B 的左子节点假如，那么这个时候就进行更新 B 的高度
     * 首先是变为 1 ， 然后 A 进行更新 +1 变成 2
     */
    public void updateHeight(BalancedTreeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // 平衡因子，其是用来判断旋转方向的重要指标
    public int balancedFactor(BalancedTreeNode node) {
        // 空节点平衡因子为 0
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // 右旋
    public BalancedTreeNode rightRotate (BalancedTreeNode node) {
        BalancedTreeNode child = node.left;
        BalancedTreeNode grandChild = child.right;
        child.right = node;
        node.left = grandChild;
        // 先要更新node节点的高度，因为只有node后来作为child的子节点
        // 它的高度会影响着child节点
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    // 左旋
    public BalancedTreeNode leftRotate (BalancedTreeNode node) {
        BalancedTreeNode child = node.right;
        BalancedTreeNode grandChild = child.left;
        child.right = node;
        node.left = grandChild;
        // 先要更新node节点的高度，因为只有node后来作为child的子节点
        // 它的高度会影响着child节点
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    // 下面就是左旋和右旋结合的判别区别
    public BalancedTreeNode rotate (BalancedTreeNode node) {
        // 先右旋
        if (balancedFactor(node) > 1) {
            // 直接右旋
            if (balancedFactor(node.left) >= 0) {
                return rightRotate(node.left);
            }
            // 先左旋再右旋
            else node.left = leftRotate(node.left);
            return rightRotate(node);
        } else if (balancedFactor(node) < -1) {
            // 直接左旋
            if (balancedFactor(node.right) <= 0) {
                return leftRotate(node.left);
            }
            // 先右旋再左旋
            else node.right = leftRotate(node.right);
            return leftRotate(node);
        }
        // 如果两边相等就不旋转
        return node;
    }

    // 增加节点，这点其实和搜索二叉树很像
    // 只不过每次都要自下而上的进行旋转来达到平衡
    public void add(BalancedTreeNode root, int num){
        addBalanceNode(root, num);
    }

    public BalancedTreeNode addBalanceNode(BalancedTreeNode node, int num) {
        // 如果为空节点
        if (node == null) {
            BalancedTreeNode newNode = new BalancedTreeNode(num);
        }

        // 不为空
        if (node.val > num) {
            node.left = addBalanceNode(node.left, num);
        } else if (node.val < num) {
            node.right = addBalanceNode(node.right, num);
        }

        updateHeight(node);
        node = rotate(node);
        return node;
    }

    // 删除节点
    public void deleteNode(BalancedTreeNode root, int num) {
        deleteSearchNode(root, num);
    }

    public BalancedTreeNode deleteSearchNode(BalancedTreeNode node, int num) {
        if (node == null) {
            return null;
        }

        // 要注意这里写node.left，因为变换的值该节点就写该节点
        // 和Search_Tree还是不一样的
        if (node.val > num) {
            node.left = deleteSearchNode(node.left, num);
        } else if (node.val < num) {
            node.right = deleteSearchNode(node.right, num);
        } else {
            // 如果为 0/1 个子节点
            if (node.left == null || node.right == null) {
                BalancedTreeNode child = node.left != null ? node.left : node.right;
                if (child == null) {
                    return null;
                } else {
                    node = child;
                }
            }
            else {
                BalancedTreeNode cur = node.right;
                while(cur.left != null) {
                    cur = cur.left;
                }
                // 理解这一步，非常巧妙，以right作为根节点开始
                // 找到要删除的中序遍历的那个值，然后删掉
                node.right = deleteSearchNode(node.right, cur.val);
                node.val = cur.val;
            }
        }
        updateHeight(node);
        node = rotate(node);
        return node;
    }
}

