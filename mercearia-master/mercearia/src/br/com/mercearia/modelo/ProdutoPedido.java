package br.com.mercearia.modelo;

public class ProdutoPedido {
	private Produto produto;
	private Pedido pedido;
	private int pedidoId;
	private String funcId;
	
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
