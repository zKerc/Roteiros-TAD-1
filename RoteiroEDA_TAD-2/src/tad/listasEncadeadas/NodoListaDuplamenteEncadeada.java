package tad.listasEncadeadas;

public class NodoListaDuplamenteEncadeada<T extends Comparable<T>> extends NodoListaEncadeada<T>{
	
	private NodoListaDuplamenteEncadeada<T> anterior;
	
	public NodoListaDuplamenteEncadeada(T chave) {
		super(chave);
		this.anterior = null;
	}
	
	public NodoListaDuplamenteEncadeada() {
		super(null);
		this.anterior = null;
	}
	
	public NodoListaDuplamenteEncadeada<T> getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoListaDuplamenteEncadeada<T> anterior) {
		this.anterior = anterior;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> getProximo() {
		return (NodoListaDuplamenteEncadeada<T>) super.getProximo();
	}

	@Override
	public void setProximo(NodoListaEncadeada<T> proximo) {
		super.setProximo(proximo);
	}

}
