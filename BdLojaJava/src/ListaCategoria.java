import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ListaCategoria extends AbstractTableModel{
	private final String[] mNomeColunas = {
			"Id", // 0
			"Nome" // 1
		};
		
		private final List<Categoria> mLista;
		
		public ListaCategoria(List<Categoria> pListaCategoria) {
			mLista = pListaCategoria;
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
			 	return mLista.get(rowIndex).getIdCategoria();
				
			case 1:
				return mLista.get(rowIndex).getNomeCategoria();
				
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
				
			default:
				return null;
			}
		}
		
		public Categoria getCategoria(int row) {
			Categoria categoriaDaLinha = new Categoria();
			categoriaDaLinha.setIdCategoria(mLista.get(row).getIdCategoria());
			categoriaDaLinha.setNomeCategoria(mLista.get(row).getNomeCategoria());
			
			return categoriaDaLinha;
		}
}
