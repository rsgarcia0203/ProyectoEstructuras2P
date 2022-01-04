/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author rsgar
 * @param <T>
 */

public class Tree<T> {
    private NodeTree root;

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
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean isLeaf(){
        return root != null && root.getChildren().isEmpty();
    }
    
    public int countLevels() {

        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }

        int maxLevelLeft = 0;
        int maxLevelRight = 0;

        if (hasLeft()) {
            maxLevelLeft = getLeft().countLevels();
        }
        if (hasRight()) {
            maxLevelRight = getRight().countLevels();
        }

        return 1 + Math.max(maxLevelLeft, maxLevelRight);

    }
    
    public NodeTree<T> recursiveSearch(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }
    
    public NodeTree<T> iterativeSearch(T content, Comparator<T> cmp){
        
        Stack<Tree<T>> stack = new Stack();
        if (this.isEmpty()) {
            return null;
        } else {
            stack.push(this);
            NodeTree<T> search = null;
            while (!stack.isEmpty()) {                                    
                Tree<T> subtree = stack.pop();
                if(cmp.compare(subtree.getRoot().getContent(), content) == 0)
                    search = subtree.getRoot();
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
    
    public NodeTree<T> recursiveGetMin(Comparator<T> cmp){        
        if(this.isEmpty())
            return null;
        else if(this.isLeaf())
            return root;
        else {

            if(this.getLeft() != null && this.getRight() == null){ // solo tiene hijo izquierdo
                NodeTree<T> leftMin = getLeft().recursiveGetMin(cmp);
                return cmp.compare(leftMin.getContent(), root.getContent()) < 0 ? leftMin : root;
            }
            else if (this.getRight() != null && this.getLeft() == null) { // solo tiene hijo derecho
                NodeTree<T> rightMin = getRight().recursiveGetMin(cmp);
                return cmp.compare(rightMin.getContent(), root.getContent()) < 0 ? rightMin : root;
            }
            
            else { //tiene ambos hijos
                NodeTree<T> leftMin = this.getLeft().recursiveGetMin(cmp);
                NodeTree<T> rightMin = this.getRight().recursiveGetMin(cmp);
                NodeTree<T> minChild = null;
                if(cmp.compare(leftMin.getContent(), rightMin.getContent()) < 0)
                    minChild = leftMin;
                else
                    minChild = rightMin;
                return cmp.compare(minChild.getContent(), root.getContent())<0 ? minChild : root; 
                
            }
                                    
            
        }                                  
    }
    
    public NodeTree<T> iterativeGetMin(Comparator<T> cmp){
        
        if(this.isEmpty())
            return null;
        
        Stack<Tree<T>> tree = new Stack();
        tree.push(this);
        NodeTree<T> minimal = root;
        
        while (!tree.isEmpty()){
            
            Tree<T> subtree = tree.pop();
            if(cmp.compare(subtree.getRoot().getContent(), minimal.getContent()) < 0)
                minimal = subtree.getRoot();
            
            
            if(subtree.getLeft()!=null)
                tree.push(subtree.getLeft());
            if(subtree.getRight()!=null)
                tree.push(subtree.getRight());
                                                         
        }
        
        return minimal;
        
    }
    
    public int recursiveCountDescendants(){
        if(this.isEmpty() || this.isLeaf())
            return 0;
        else {
            int leftDescendants = 0;
            int rightDescendants = 0;
            
            if(this.getLeft() != null)
                leftDescendants = 1 + this.getLeft().recursiveCountDescendants();
            if(this.getRight() != null)
                rightDescendants = 1 + this.getRight().recursiveCountDescendants();
            
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

}
