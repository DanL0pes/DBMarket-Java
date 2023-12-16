import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class CadastroCategoria extends JDialog{
	private int controleLista = 0;
	private JTable tbCategoria;
	private JLabel lbNomeCategoria;
	private JScrollPane spListaCategoria;
	private JTextField txNomeCategoria;
	private JButton btPesquisar, btCadastrar, btUpdate, btDelete;
	private int categoriaClick = 0;
	
	public CadastroCategoria() {
		this.setTitle("Cadastrar Categoria");
		this.setModal(true);
		this.setSize(320, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		lbNomeCategoria = new JLabel();
		lbNomeCategoria.setText("Nome da Categoria:");
		lbNomeCategoria.setBounds(20,25,125,20);
		add(lbNomeCategoria);
		
		txNomeCategoria = new JTextField();
		txNomeCategoria.setBounds(145,25,125, 20);
		add(txNomeCategoria);
		
		btPesquisar = new JButton(new ImageIcon(((new ImageIcon(getClass().getResource("images/lupa.png"))).getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
		btPesquisar.setBounds(270, 25, 20, 20);
		add(btPesquisar);
		
		
		
		btCadastrar = new JButton();
		btCadastrar.setText("Criar");
		btCadastrar.setBounds(20, 400, 90,40);
		add(btCadastrar);
		
		btUpdate = new JButton();
		btUpdate.setText("Atualizar");
		btUpdate.setBounds(110, 400, 90,40);
		add(btUpdate);
		
		btDelete = new JButton();
		btDelete.setText("Deletar");
		btDelete.setBounds(200, 400, 90,40);
		add(btDelete);

		pesquisa();
		
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pesquisa();
			}
		});
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Statement state;
				String nomeCategoria = JOptionPane.showInputDialog("Digite o nome Categoria que deseja Cadastrar:");
				String idCategoria = "";
				Conexao conex = new Conexao();
				ResultSet resultset;
				String sql = "select idCategoria from tbCategoria where deletedCategoria = 0 AND nomeCategoria = '"+nomeCategoria+"'";
				conex.conectar();	
				try {
					state = (Statement) Conexao.con.createStatement();
					resultset = state.executeQuery(sql);
					while(resultset.next()){
						idCategoria = resultset.getString(1);
					}
				}catch(SQLException erro){
				}
				if(idCategoria == "") {
					sql = "insert into tbCategoria values(null, '"+nomeCategoria+"', 0)";
					try {
						state = (Statement) Conexao.con.createStatement();
						state.executeUpdate(sql);
						cadastroSucesso(0);
					}catch(SQLException erro){
						System.err.println("Got an exception! "); 
			            System.err.println(erro.getMessage());
						cadastroFalha(0);
					}
				}else {
					cadastroFalha(3);
				}
				pesquisa();
				conex.desconectar();
			}
		});
		
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Statement state;
				Conexao conex = new Conexao();
				conex.conectar();
				if(categoriaClick != 0) {
					String nomeCategoria = JOptionPane.showInputDialog("Digite o novo nome Categoria:");
					String sql = "UPDATE tbCategoria SET nomeCategoria = '"+nomeCategoria+"' WHERE idCategoria = "+categoriaClick+" ";
					try {
						state = (Statement) Conexao.con.createStatement();
						state.executeUpdate(sql);
						cadastroSucesso(0);
					}catch(SQLException erro){
						System.err.println("Got an exception! "); 
			            System.err.println(erro.getMessage());
						cadastroFalha(1);
					}
				}else {
					cadastroFalha(4);
				}
				pesquisa();
				conex.desconectar();
				
			}
		});
		
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PreparedStatement state;
				Conexao conex = new Conexao();
				conex.conectar();	
				if(categoriaClick != 0) {
					String sql = "UPDATE tbCategoria SET deletedCategoria = 1 WHERE idCategoria ="+categoriaClick+" ";
					try {
						state = Conexao.con.prepareStatement(sql);
						state.executeUpdate(sql);
						cadastroSucesso(2);
					}catch(SQLException erro){
						cadastroFalha(2);
					}
				}
				pesquisa();
				conex.desconectar();
			}
		});
		
		this.setLayout(null);
		this.setVisible(true);
	}
	private void pesquisa() {
		if(controleLista == 1) {
			getContentPane().remove(spListaCategoria);
		}
		List<Categoria> listaCategoriaStart = new ArrayList<>();
		//Select LIST-START
				Statement state;
				ResultSet resultset;
				Conexao conex = new Conexao();     
				String sql = "select * from tbCategoria where deletedCategoria = 0 AND nomeCategoria like '"+txNomeCategoria.getText()+"%'";
				conex.conectar();	
				try{                  
						
					state = (Statement) Conexao.con.createStatement();
					resultset = state.executeQuery(sql);
						
						while(resultset.next()){
							listaCategoriaStart.add(new Categoria((Integer.parseInt(resultset.getString(1))), (resultset.getString(2))));
						}                         
				}
				catch(SQLException erro){
					System.out.println("Nao foi possível realizar a consulta");
				}
				conex.desconectar();
		//Select LIST-END
		ListaCategoria listaStart = new ListaCategoria(listaCategoriaStart);
		tbCategoria = new JTable(listaStart);
		tbCategoria.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	if (e.getClickCount() == 1) {
	                final JTable jTable= (JTable)e.getSource();
	                final int row = jTable.getSelectedRow();
	                final int column = 0;
	                categoriaClick = (Integer)jTable.getValueAt(row, column);
	            }
	            System.out.println(categoriaClick);
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        }
	    });
		spListaCategoria = new JScrollPane(tbCategoria);
		spListaCategoria.setBounds(20,60,270,330);
		add(spListaCategoria);
		controleLista = 1;
	}
	private void cadastroSucesso(int verify){
		String temp = "Cadastrado";
		switch(verify) {
		case 0:
			temp = "Cadastrado";
			break;
		case 1:
			temp = "Atualizado";
			break;
		case 2:
			temp = "Deletado";
		}
		JOptionPane.showMessageDialog(this,temp+" com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
	}
	private void cadastroFalha(int verify) {
		String temp = "cadastrar";
		switch(verify) {
		case 0:
			temp = "cadastrar";
			break;
		case 1:
			temp = "atualizar";
			break;
		case 2:
			temp = "deletar";
			break;
		case 3:
			temp = "cadastrar, pois já existe.";
			break;
		case 4:
			temp = "atualizar. Selecione uma categoria na tabela!";
			break;
		}
		JOptionPane.showMessageDialog(this,"Não foi possível "+temp+"!", "Falha!", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
