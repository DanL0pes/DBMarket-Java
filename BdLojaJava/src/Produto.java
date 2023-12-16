
public class Produto {

	private String nomeProduto, descProduto; 
	private int  idProduto, qtdeProduto;
	private double valorProduto;
	private String idCategoriaP;
	
	public Produto() {
	}
	public Produto(int idProduto, String nomeProduto, double valorProduto, String descProduto, int qtdeProduto, String idCategoria) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.valorProduto = valorProduto;
		this.descProduto = descProduto;
		this.qtdeProduto = qtdeProduto;
		this.idCategoriaP = idCategoria;
	}
	public int getQtdeProduto() {
		return qtdeProduto;
	}
	public void setQtdeProduto(int qtdeProduto) {
		this.qtdeProduto = qtdeProduto;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	public String getIdCategoriaP() {
		return idCategoriaP;
	}
	public void setIdCategoriaP(String idCategoriaP) {
		this.idCategoriaP = idCategoriaP;
	}
	
}
