package tad.listasEncadeadas;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    private NodoListaEncadeada<T> cabeca;
    private int tamanho;

    public ListaEncadeadaImpl() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    @Override
    public void insert(T chave) {
        insert(chave, tamanho);
    }

    @Override
    public void insert(T chave, int index) {
        NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<T>(chave);
		if(index > tamanho) {
			throw new IndexOutOfBoundsException("Índice fora dos limites da lista.");
		}
        if (index == 0) {
            novoNodo.setProximo(cabeca);
            cabeca = novoNodo;
        } else {
            NodoListaEncadeada<T> temp = cabeca;
            for (int i = 0; i < index - 1 && temp != null; i++) {
                temp = temp.getProximo();
            }
            if (temp != null) {
                novoNodo.setProximo(temp.getProximo());
                temp.setProximo(novoNodo);
            }
        }
        tamanho++;
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
        if (isEmpty() || chave == null) {
            throw new ListaVaziaException();
        }
    
        NodoListaEncadeada<T> temp = cabeca;
        NodoListaEncadeada<T> prev = null;
    
        if (temp != null && temp.getChave().compareTo(chave) == 0) {
            cabeca = temp.getProximo();
            tamanho--;
            return temp;
        }
    
        while (temp != null && temp.getChave() != null && temp.getChave().compareTo(chave) != 0) {
            prev = temp;
            temp = temp.getProximo();
        }
    
        if (temp == null) {
            throw new ListaVaziaException("Elemento não encontrado!");
        }
    
        prev.setProximo(temp.getProximo());
        tamanho--;
        return temp;
    }    

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> temp = cabeca;
        while (temp != null) {
            if (temp.getChave().compareTo(chave) == 0) {
                return temp;
            }
            temp = temp.getProximo();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return cabeca == null;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        if (tamanho == 0) return null;
    
        @SuppressWarnings("unchecked")
        T[] resultArray = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);

        NodoListaEncadeada<T> current = cabeca;
        int i = 0;
    
        while (current != null) {
            resultArray[i] = current.getChave();
            i++;
            current = current.getProximo();
        }
        return resultArray;
    }
    
    public String imprimeEmOrdem() {
        StringBuilder builder = new StringBuilder();
        NodoListaEncadeada<T> current = cabeca;
    
        while (current != null) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(current.getChave());
            current = current.getProximo();
        }
    
        return builder.toString();
    }
    
    

    @Override
    public String imprimeInverso() {
        return imprimeInversoRecursivamente(cabeca);
    }

    private String imprimeInversoRecursivamente(NodoListaEncadeada<T> nodo) {
        if (nodo == null) return "";

        String next = imprimeInversoRecursivamente(nodo.getProximo());
        if (!next.isEmpty()) {
            next += ", ";
        }

        return next + nodo.toString();
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaEncadeada<T> temp = search(chave);
        return (temp != null) ? temp.getProximo() : null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaEncadeada<T> temp = cabeca;
        NodoListaEncadeada<T> prev = null;

        while (temp != null) {
            if (temp.getChave().compareTo(chave) == 0) {
                return prev;
            }
            prev = temp;
            temp = temp.getProximo();
        }
        return null;
    }
	
}