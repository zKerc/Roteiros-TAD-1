package tad.pilha;

public class MinhaPilha<E> implements PilhaIF<E> {

    private static final int DEFAULT_SIZE = 7;
    private int topo;
    private E[] meuDados;

    @SuppressWarnings("unchecked")
    public MinhaPilha() {
        this(DEFAULT_SIZE);
    }

	@SuppressWarnings("unchecked")
	public MinhaPilha(int tamanho) {
		if (tamanho <= 0) {
			throw new IllegalArgumentException("O tamanho da pilha deve ser maior que zero.");
		}
		this.meuDados = (E[]) new Object[tamanho];
		this.topo = -1;
	}
	

	@Override
	public void empilhar(E item) throws PilhaCheiaException {
		if (topo == meuDados.length - 1) {
			throw new PilhaCheiaException();
		}
		meuDados[++topo] = item;
	}

    @Override
    public E desempilhar() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException();
        }
        E item = meuDados[topo];
        meuDados[topo--] = null;
        return item;
    }

    @Override
    public E topo() {
        if (isEmpty()) {
            return null;
        }
        return meuDados[topo];
    }

    @Override
    public boolean isEmpty() {
        return topo == -1;
    }

	@Override
	public PilhaIF<E> multitop(int k) {
		PilhaIF<E> resultado = new MinhaPilha<E>(); // Cria uma nova pilha para armazenar o resultado
	
		if (k <= 0 || k > tamanho()) { // Verifica se k é inválido
			return resultado; // Retorna a pilha vazia
		}
	
		for (int i = topo; i > topo - k; i--) {
			try {
				resultado.empilhar(meuDados[i]);
			} catch (PilhaCheiaException e) {
				// Você pode tratar a exceção aqui ou apenas imprimir um log
				e.printStackTrace();
			}
		}
	
		return resultado; // Retorna a pilha contendo os k elementos superiores.
	}
	

    private int tamanho() {
        return topo + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MinhaPilha)) {
            return false;
        }

        MinhaPilha<?> outraPilha = (MinhaPilha<?>) obj;
        if (outraPilha.tamanho() != this.tamanho()) {
            return false;
        }

        for (int i = 0; i <= topo; i++) {
            if (!this.meuDados[i].equals(outraPilha.meuDados[i])) {
                return false;
            }
        }

        return true;
    }
}
