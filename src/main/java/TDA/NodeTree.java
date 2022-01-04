/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.util.LinkedList;

/**
 *
 * @author rsgar
 * @param <E>
 */
public class NodeTree<E> {
    
    private E content;
    private LinkedList<Tree<E>> children;
    // NO EN ESTE CURSO: private LinkedList<NodeTree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }
    
    
    
    
}
