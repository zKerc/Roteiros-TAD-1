package tad.listasEncadeadas;

import java.lang.reflect.Array;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
    private NodoListaDuplamenteEncadeada<T> cabeca;
    private NodoListaDuplamenteEncadeada<T> cauda;

    public ListaDuplamenteEncadeadaImpl() {
        cabeca = new NodoListaDuplamenteEncadeada<>();
        cauda = new NodoListaDuplamenteEncadeada<>();
        cabeca.setProximo(cauda);
        cauda.setAnterior(cabeca);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo() == cauda;
    }

    public int size() {
        int count = 0;
        NodoListaDuplamenteEncadeada<T> current = cabeca.getProximo();
        while (current != cauda) {
            count++;
            current = current.getProximo();
        }
        return count;
    }

    public NodoListaDuplamenteEncadeada<T> search(T chave) {
        NodoListaDuplamenteEncadeada<T> current = cabeca.getProximo();
        while (current != cauda) {
            if (current.getChave().equals(chave)) {
                return current;
            }
            current = current.getProximo();
        }
        return null;
    }

    public void insert(T chave) {
        NodoListaDuplamenteEncadeada<T> novoNodo = new NodoListaDuplamenteEncadeada<>(chave);
        cauda.getAnterior().setProximo(novoNodo);
        novoNodo.setAnterior(cauda.getAnterior());
        novoNodo.setProximo(cauda);
        cauda.setAnterior(novoNodo);
        System.out.println("Inserido: " + chave + ". Lista agora é: " + imprimeEmOrdem());
    }
    

    public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
        System.out.println("Tentando remover: " + chave);
        
        NodoListaDuplamenteEncadeada<T> nodoToRemove = search(chave);
        if (nodoToRemove != null) {
            nodoToRemove.getAnterior().setProximo(nodoToRemove.getProximo());
            nodoToRemove.getProximo().setAnterior(nodoToRemove.getAnterior());
            
            System.out.println("Removido: " + chave + ". Lista agora é: " + imprimeEmOrdem());
            return nodoToRemove;
        }
        
        throw new ListaVaziaException(chave + " não encontrado. Lista permanece: " + imprimeEmOrdem());
    }
    
    
    
    public String imprimeEmOrdem() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> current = cabeca.getProximo();
        while (current != cauda) {
            sb.append(current.getChave()).append(", ");
            current = current.getProximo();
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 2);  // Remove trailing comma and space
        return sb.toString();
    }
    
    public String imprimeInverso() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> current = cauda.getAnterior();
        while (current != cabeca) {
            sb.append(current.getChave()).append(", ");
            current = current.getAnterior();
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 2);  // Remove trailing comma and space
        return sb.toString();
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaDuplamenteEncadeada<T> current = search(chave);
        if (current != null && current.getProximo() != cauda) {
            return current.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaDuplamenteEncadeada<T> current = search(chave);
        if (current != null && current.getAnterior() != cabeca) {
            return current.getAnterior();
        }
        return null;
    }

    public T[] toArray(Class<T> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, size());
        if (isEmpty()) return null;
    
        NodoListaDuplamenteEncadeada<T> current = cabeca.getProximo();
        int i = 0;
        while (current != cauda) {
            array[i++] = current.getChave();
            current = current.getProximo();
        }
        return array;
    }
    

    @Override
    public void inserePrimeiro(T elemento) {
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(elemento);
        novoNo.setProximo(cabeca.getProximo());
        cabeca.getProximo().setAnterior(novoNo);
        novoNo.setAnterior(cabeca);
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removeUltimo() {
        NodoListaDuplamenteEncadeada<T> lastNode = cauda.getAnterior();
        if (lastNode != cabeca) {
            lastNode.getAnterior().setProximo(cauda);
            cauda.setAnterior(lastNode.getAnterior());
            cauda.setProximo(null);
            return lastNode;
        }
        return null;
    }
    

    @Override
    public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
        NodoListaDuplamenteEncadeada<T> firstNode = cabeca.getProximo();
        if (firstNode != cauda) {
            firstNode.getProximo().setAnterior(cabeca);
            cabeca.setProximo(firstNode.getProximo());
            return firstNode;
        }
        return null;
    }

    @Override
    public void insert(T chave, int index) {
        System.out.println("Tentando inserir " + chave + " no índice " + index);
        
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);
        NodoListaDuplamenteEncadeada<T> current = cabeca;
    
        for (int i = 0; i < index; i++) {
            current = current.getProximo();
        }
    
        novoNo.setProximo(current.getProximo());
        current.getProximo().setAnterior(novoNo);
        novoNo.setAnterior(current);
        current.setProximo(novoNo);
        
        System.out.println("Inserido: " + chave + " no índice " + index + ". Lista agora é: " + imprimeEmOrdem());
    }    
}
