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
