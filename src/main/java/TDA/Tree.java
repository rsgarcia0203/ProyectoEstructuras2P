package TDA;

import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author rsgar
 * @param <T>
 */
public class Tree<T> {
    
    // insertar, eliminar, localizar elementos
    // recorrer un arbol 
    private NodeTree<T> root;

    public Tree() {
        this.root = null;
    }

    public Tree(NodeTree root) {
        this.root = root;
    }

    public NodeTree getRoot() {
        return root;
    }

    public void setRoot(NodeTree root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean isLeaf() {
        return root != null && root.getChildren().isEmpty();
    }

    public boolean hasChildrens() {
        return !this.root.getChildren().isEmpty();
    }

    public int countLevels() {

        if (isEmpty()) {
            return 0;
        }
        
        if (isLeaf()) {
            return 1;
        }

        int maxLevel = 0;

        if (hasChildrens()) {
            maxLevel++;
                        
            for(Tree<T> tree: this.root.getChildren()){
                
                if(tree.hasChildrens()){
                    maxLevel = tree.countLevels();
                }            
            }         
        }

        return 1 + maxLevel;

    }

    public NodeTree<T> recursiveSearch(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                NodeTree<T> tmp = null;
                
                if (this.root.getChildren().isEmpty()) {
                    return tmp;
                } else {
                   
                    for(Tree<T> tree: this.root.getChildren()){
                        
                        if(cmp.compare(tree.getRoot().getContent(), content) == 0){
                            
                        }
                    }
                }
                
                
                return tmp;
            }
        }
    }

    public NodeTree<T> iterativeSearch(T content, Comparator<T> cmp) {

        Stack<Tree<T>> stack = new Stack();
        if (this.isEmpty()) {
            return null;
        } else {
            stack.push(this);
            NodeTree<T> search = null;
            while (!stack.isEmpty()) {
                Tree<T> subtree = stack.pop();
                if (cmp.compare(subtree.getRoot().getContent(), content) == 0) {
                    search = subtree.getRoot();
                }
                if (subtree.getLeft() != null) {
                    stack.push(subtree.getLeft());
                }
                if (subtree.getRight() != null) {
                    stack.push(subtree.getRight());
                }
            }
            return search;
        }

    }

    public NodeTree<T> recursiveGetMin(Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else if (this.isLeaf()) {
            return root;
        } else {

            //NodeTree<T> rightMin = this.getRight().recursiveGetMin(cmp);
            NodeTree<T> minChild = null;
            if (cmp.compare(leftMin.getContent(), rightMin.getContent()) < 0) {
                minChild = leftMin;
            } else {
                minChild = rightMin;
            }
            return cmp.compare(minChild.getContent(), root.getContent()) < 0 ? minChild : root;

        }
    }

    public NodeTree<T> iterativeGetMin(Comparator<T> cmp) {

        if (this.isEmpty()) {
            return null;
        }

        Stack<Tree<T>> tree = new Stack();
        tree.push(this);
        NodeTree<T> minimal = root;

        while (!tree.isEmpty()) {

            Tree<T> subtree = tree.pop();
            if (cmp.compare(subtree.getRoot().getContent(), minimal.getContent()) < 0) {
                minimal = subtree.getRoot();
            }

            if (subtree.getLeft() != null) {
                tree.push(subtree.getLeft());
            }
            if (subtree.getRight() != null) {
                tree.push(subtree.getRight());
            }

        }

        return minimal;

    }

    public int recursiveCountDescendants() {
        if (this.isEmpty() || this.isLeaf()) {
            return 0;
        } else {
            int leftDescendants = 0;
            int rightDescendants = 0;

            if (this.getLeft() != null) {
                leftDescendants = 1 + this.getLeft().recursiveCountDescendants();
            }
            if (this.getRight() != null) {
                rightDescendants = 1 + this.getRight().recursiveCountDescendants();
            }

            return leftDescendants + rightDescendants;
        }
    }

    public int iterativeCountDescendants() {
        Stack<Tree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                Tree<T> subtree = stack.pop();

                if (subtree.getLeft() != null) {
                    stack.push(subtree.getLeft());
                    count++;
                }
                if (subtree.getRight() != null) {
                    stack.push(subtree.getRight());
                    count++;
                }
            }
        }
        return count;
    }

    public Tree<T> findParent(NodeTree<T> nodo) {
        if (this.isEmpty()) {
            return null;
        } else if (this.isLeaf()) {
            return null;
        } else {
            Tree<T> padre = null;
            if (this.root.getLeft() != null) {
                if (!this.root.equals(nodo)) {
                    padre = this.root.getLeft().findParent(nodo);
                } else {
                    padre = this;
                }
            }
            if (this.root.getRight() != null) {
                if (!this.root.equals(nodo)) {
                    padre = this.root.getRight().findParent(nodo);
                } else {
                    padre = this;
                }
            }
            return padre;
        }
    }

    public boolean isIdentical(Tree<T> otherTree) {

        if (this.isEmpty() && otherTree.isEmpty()) {
            return true;
        } else if (this.isLeaf() && otherTree.isLeaf()) {
            return this.root.equals(otherTree.getRoot());
        } else {
            if (this.root.getLeft() != null && otherTree.getRoot().getLeft() != null) {
                this.root.getLeft().isIdentical(otherTree.getRoot().getLeft());
            } else {
                return false;
            }

            if (this.root.getRight() != null && otherTree.getRoot().getRight() != null) {
                this.root.getRight().isIdentical(otherTree.getRoot().getRight());
            } else {
                return false;
            }

            return true;
        }
    }

    public void largestValueOfEachLevel(Comparator<T> cmp) throws Exception {
        if (this.isEmpty()) {
            throw new Exception("...");
        } else if (this.isLeaf()) {
            System.out.println(this.root.getContent());
        } else {
            Tree<T> right = this.root.getRight();
            Tree<T> left = this.root.getLeft();

            if (right != null && left != null) {
                if (cmp.compare(right.getRoot().getContent(), left.getRoot().getContent()) == -1) {
                    System.out.println(left.getRoot().getContent());
                } else if (cmp.compare(right.getRoot().getContent(), left.getRoot().getContent()) == -1) {
                    System.out.println(right.getRoot().getContent());
                }
            } else if (right == null && left != null) {
                System.out.println(left.getRoot().getContent());
            } else if (right != null && left == null) {
                System.out.println(right.getRoot().getContent());
            }

            if (this.getRight() != null) {
                right.largestValueOfEachLevel(cmp);
            }

            if (this.getRight() != null) {
                left.largestValueOfEachLevel(cmp);
            }

        }
    }

    public int countNodesWithOnlyChild() {
        Stack<Tree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                Tree<T> subtree = stack.pop();

                if (subtree.getLeft() != null && subtree.getRight() == null) {
                    stack.push(subtree.getLeft());
                    count++;
                }

                if (subtree.getRight() != null && subtree.getLeft() == null) {
                    stack.push(subtree.getRight());
                    count++;
                }
            }
        }
        return count;
    }
    
    public void insert(NodeTree nt, T content){
        
    }
    
    public void delete(T content){
        
    }
    
}
