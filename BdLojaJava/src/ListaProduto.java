import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ListaProduto extends AbstractTableModel{
	private final String[] mNomeColunas = {
			"Id", // 0
			"Nome", // 1
			"Valor", // 2
			"Desc", // 3
			"Qtde", // 4
			"Categoria" //5
		};
		
		private final List<Produto> mLista;
		
		public ListaProduto(List<Produto> pListaProduto) {
			mLista = pListaProduto;
		}
		
		@Override
		public int getRowCount() {
			if(null == mLista) {
				return 0;
			}
			
			return mLista.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return mNomeColunas.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			switch(columnIndex) {
			case 0:
			 	return mLista.get(rowIndex).getIdProduto();
				
			case 1:
				return mLista.get(rowIndex).getNomeProduto();
				
			case 2:
				return mLista.get(rowIndex).getValorProduto();
				
			case 3:
				return mLista.get(rowIndex).getDescProduto();
				
			case 4:
				return mLista.get(rowIndex).getQtdeProduto();
			
			case 5:
				return mLista.get(rowIndex).getIdCategoriaP();
				
			default:
				return 0;
			}
		}
		
		public String getColumnName(int index) {
			return mNomeColunas[index];
		}

		public Class getColClass(int column) {
			switch(column) {
			case 0:
				return Integer.class;
				
			case 1:
				return String.class;
				
			case 2:
				return Double.class;
				
			case 3:
				return String.class;
				
			case 4:
				return Integer.class;
				
			case 5:
				return String.class;
				
			default:
				return null;
			}
		}
		
		public Produto getProduto(int row) {
			Produto produtoDaLinha = new Produto();
			produtoDaLinha.setIdProduto(mLista.get(row).getIdProduto());
			produtoDaLinha.setNomeProduto(mLista.get(row).getNomeProduto());
			produtoDaLinha.setValorProduto(mLista.get(row).getValorProduto());
			produtoDaLinha.setDescProduto(mLista.get(row).getDescProduto());
			produtoDaLinha.setQtdeProduto(mLista.get(row).getQtdeProduto());
			produtoDaLinha.setIdCategoriaP(mLista.get(row).getIdCategoriaP());
			
			return produtoDaLinha;
		}
}
