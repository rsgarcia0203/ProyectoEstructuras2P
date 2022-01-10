/*
 * Copyright 2022 rsgar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package TDA;

import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author rsgar
 * @param <T>
 */
public class Tree<T> implements Comparable<Tree<T>> {

    // insertar, eliminar, localizar elementos
    // recorrer un arbol 
    private NodeTree<T> root;

    public Tree() {
        this.root = null;
    }

    public Tree(NodeTree root) {
        this.root = root;
    }

    public NodeTree<T> getRoot() {
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

            for (Tree<T> tree : this.root.getChildren()) {

                if (tree.hasChildrens()) {
                    maxLevel = tree.countLevels();
                }
            }
        }

        return 1 + maxLevel;

    }

    public NodeTree<T> recursiveSearch(T content, Comparator<T> cmp) throws EmptyTreeException {
        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        } else {

            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                NodeTree<T> tmp = null;

                if (this.root.getChildren().isEmpty()) {
                    return tmp;
                } else {

                    for (Tree<T> tree : this.root.getChildren()) {

                        T contentTree = tree.getRoot().getContent();
                        if (cmp.compare(contentTree, content) == 0) {
                            tmp = tree.getRoot();
                        }
                    }
                }

                return tmp;
            }
        }
    }

    public NodeTree<T> iterativeSearch(T content, Comparator<T> cmp) throws EmptyTreeException {

        Stack<Tree<T>> stack = new Stack();

        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        } else {
            stack.push(this);
            NodeTree<T> search = null;

            while (!stack.isEmpty()) {

                Tree<T> subtree = stack.pop();
                T contentTree = subtree.getRoot().getContent();

                if (cmp.compare(contentTree, content) == 0) {
                    search = subtree.getRoot();
                }

                if (subtree.hasChildrens()) {

                    for (Tree tree : subtree.getRoot().getChildren()) {
                        stack.push(tree);
                    }
                }

            }
            return search;
        }

    }

    //revisar
    public NodeTree<T> recursiveGetMin(Comparator<T> cmp) throws EmptyTreeException {

        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        } else if (this.isLeaf()) {
            return root;
        } else {

            //NodeTree<T> rightMin = this.getRight().recursiveGetMin(cmp);
            NodeTree<T> minChild = null;

            if (this.hasChildrens()) {

                for (Tree subtree : this.root.getChildren()) {
                    minChild = subtree.recursiveGetMin(cmp);
                }

            }

            return cmp.compare(minChild.getContent(), root.getContent()) < 0 ? minChild : root;
        }
    }

    public NodeTree<T> iterativeGetMin(Comparator<T> cmp) throws EmptyTreeException {

        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        }

        Stack<Tree<T>> stack = new Stack();
        stack.push(this);
        NodeTree<T> minimal = root;

        while (!stack.isEmpty()) {

            Tree<T> subtree = stack.pop();
            T content = (T) subtree.getRoot().getContent();

            if (cmp.compare(content, minimal.getContent()) < 0) {
                minimal = subtree.getRoot();
            }

            if (subtree.hasChildrens()) {

                for (Tree tree : this.root.getChildren()) {
                    stack.push(tree);
                }

            }
        }

        return minimal;

    }

    public int recursiveCountDescendants() {
        if (this.isEmpty() || this.isLeaf()) {
            return 0;
        } else {
            int descendants = 0;

            if (this.hasChildrens()) {

                for (Tree subtree : this.root.getChildren()) {

                    if (subtree.hasChildrens()) {
                        descendants += subtree.recursiveCountDescendants();
                    } else {
                        descendants++;
                    }

                }

            }

            return descendants;
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

                if (subtree.hasChildrens()) {

                    for (Tree tree : this.root.getChildren()) {
                        stack.push(tree);
                    }

                }
            }
        }

        return count;
    }

    public Tree<T> findParent(T content) throws EmptyTreeException, ParentException {

        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        } else if (this.isLeaf()) {
            throw new ParentException("");
        } else {
            Tree<T> padre = null;

            if (this.hasChildrens()) {

                for (Tree subtree : this.root.getChildren()) {

                    if (subtree.hasChildrens()) {
                        padre = subtree.findParent(content);
                    } else {

                        if (subtree.getRoot().getContent().equals(content)) {
                            padre = this;
                        }

                    }
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
            if (this.hasChildrens() && otherTree.hasChildrens()) {

                for (Tree tree : this.root.getChildren()) {

                    if (!otherTree.getRoot().getChildren().contains(tree)) {
                        return false;
                    }

                }

            } else {
                return false;
            }

            return true;
        }
    }

    /*public void largestValueOfEachLevel(Comparator<T> cmp) throws Exception {
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
     */
    public int countNodesWithOnlyChild() {

        Stack<Tree<T>> stack = new Stack();
        int count = 0;

        if (this.isEmpty() || this.isLeaf()) {
            return count;
        } else {
            stack.push(this);

            while (!stack.empty()) {
                Tree<T> subtree = stack.pop();

                if (subtree.hasChildrens()) {

                    for (Tree tree : subtree.getRoot().getChildren()) {
                        stack.push(tree);
                    }
                }

                if (subtree.getRoot().getChildren().size() == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void insert(NodeTree nt, T content) {

        if (this.isEmpty()) {
            this.root = new NodeTree(content);
        } else if (this.isLeaf()) {
            this.root.getChildren().add(new Tree(new NodeTree(content)));
        } else {

            if (this.hasChildrens()) {

                for (Tree tree : this.root.getChildren()) {
                    int i = 0;

                    if (this.compareTo(new Tree(nt)) == 0) {
                        this.root.getChildren().get(i).getRoot().getChildren().addLast(new Tree(new NodeTree(content)));
                    } else {

                    }

                    i++;
                }
            }

        }

    }

    public void delete(T content) throws EmptyTreeException, TreeContentException {

        if (this.isEmpty()) {
            throw new EmptyTreeException("");
        } else if (this.isLeaf()) {

            if (this.root.getContent().equals(content)) {
                this.root = null;
            } else {
                throw new TreeContentException("");
            }
        } else {

            if (this.hasChildrens()) {
                
                for (Tree tree : this.root.getChildren()) {
                    int i = 0;

                    if (tree.getRoot().getContent().equals(content)) {
                        this.root.getChildren().remove(i);
                    } else {
                        tree.delete(content);
                    }

                    i++;
                }
                
            }
        }
    }

    @Override
    public int compareTo(Tree<T> otherTree) {

        if (this.root.getContent().equals(otherTree.getRoot().getContent())) {
            return 0;
        }

        return 1;
    }

}
