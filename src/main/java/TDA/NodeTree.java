package TDA;

import java.util.LinkedList;

/**
 *
 * @author rsgar
 * @param <T>
 */
public class NodeTree<T> {
    
    private T content;
    private LinkedList<Tree<T>> children;
    // NO EN ESTE CURSO: private LinkedList<NodeTree<E>> children;

    public NodeTree(T content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public LinkedList<Tree<T>> getChildren() {
        return this.children;
    }

    public void setChildren(LinkedList<Tree<T>> children) {
        this.children = children;
    }

}
