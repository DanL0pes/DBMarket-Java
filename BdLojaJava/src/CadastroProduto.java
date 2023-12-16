import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class CadastroProduto extends JDialog{
	private int controleLista = 0;
	private JTable tbProduto;
	private JScrollPane spListaProduto;
	private JLabel lbNomeProduto;
	private JTextField txNomeProduto;
	private JButton btCadastrar, btDelete, btUpdate, btPesquisar;
	private int produtoClick = 0;
	
	public CadastroProduto() {
		this.setTitle("Cadastrar Produto");
		this.setModal(true);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		lbNomeProduto = new JLabel();
		lbNomeProduto.setText("Nome da Produto:");
		lbNomeProduto.setBounds(20,25,125,20);
		add(lbNomeProduto);
		
		txNomeProduto = new JTextField();
		txNomeProduto.setBounds(135,25,310, 20);
		add(txNomeProduto);
		
		btPesquisar = new JButton(new ImageIcon(((new ImageIcon(getClass().getResource("images/lupa.png"))).getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
		btPesquisar.setBounds(445, 25, 20, 20);
		add(btPesquisar);
		
		btCadastrar = new JButton();
		btCadastrar.setText("Criar");
		btCadastrar.setBounds(13, 410, 148,40);
		add(btCadastrar);
		
		btUpdate = new JButton();
		btUpdate.setText("Atualizar");
		btUpdate.setBounds(164, 410, 150,40);
		add(btUpdate);
		
		btDelete = new JButton();
		btDelete.setText("Deletar");
		btDelete.setBounds(317, 410, 150,40);
		add(btDelete);
		
		pesquisa();
		
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pesquisa();
			}
		});
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				janelaProduto(0);
				pesquisa();
			}
		});
		
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(produtoClick == 0) {	
					cadastroFalha(4);
				}else {
					janelaProduto(1);
				}
				pesquisa();
			}
		});
		
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PreparedStatement state;
				Conexao conex = new Conexao();
				conex.conectar();	
				if(produtoClick != 0) {
					String sql = "UPDATE tbProduto SET deletedProduto = 1 WHERE idProduto ="+produtoClick+" ";
					try {
						state = Conexao.con.prepareStatement(sql);
						state.executeUpdate(sql);
						cadastroSucesso(2);
					}catch(SQLException erro){
						cadastroFalha(2);
					}
				}
				conex.desconectar();
				pesquisa();
			}
		});
		
		this.setLayout(null);
		this.setVisible(true);
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
	private void pesquisa() {
		if(controleLista == 1) {
			getContentPane().remove(spListaProduto);
		}
		List<Produto> listaProdutoStart = new ArrayList<>();
		//Select LIST-START
				Statement state;
				ResultSet resultset;
				Conexao conex = new Conexao();     
				String sql = "select idProduto, nomeProduto, ValorProduto, descProduto, qtdeProduto, nomeCategoria from tbProduto INNER JOIN tbCategoria ON tbProduto.idCategoria = tbCategoria.idCategoria where deletedProduto = 0 AND nomeProduto like '"+txNomeProduto.getText()+"%'";
				conex.conectar();	
				try{                  
						
					state = (Statement) Conexao.con.createStatement();
					resultset = state.executeQuery(sql);
						
						while(resultset.next()){
							listaProdutoStart.add(new Produto((Integer.parseInt(resultset.getString(1))), (resultset.getString(2)), (Double.parseDouble(resultset.getString(3))), (resultset.getString(4)), (Integer.parseInt(resultset.getString(5))), resultset.getString(6)));
						}                         
				}
				catch(SQLException erro){
					System.out.println("Nao foi possível realizar a consulta");
				}
				conex.desconectar();
		//Select LIST-END
		ListaProduto listaStart = new ListaProduto(listaProdutoStart);
		tbProduto = new JTable(listaStart);
		tbProduto.addMouseListener(new MouseListener() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	if (e.getClickCount() == 1) {
	                final JTable jTable= (JTable)e.getSource();
	                final int row = jTable.getSelectedRow();
	                final int column = 0;
	                produtoClick = (Integer)jTable.getValueAt(row, column);
	            }
	            System.out.println(produtoClick);
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
		spListaProduto = new JScrollPane(tbProduto);
		spListaProduto.setBounds(20,60,445,320);
		add(spListaProduto);
		controleLista = 1;
	}
	private void janelaProduto(int verifyComand) {
		JDialog janelaProduto = new JDialog();;
		janelaProduto.setModal(true);
		janelaProduto.setSize(400, 250);
		janelaProduto.setResizable(false);
		janelaProduto.setLocationRelativeTo(null);

		Conexao conex = new Conexao();
		JLabel lbProduto, lbNomeProduto, lbValorProduto, lbDescProduto, lbQtdeProduto, lbIdCategoriaProduto;
		JTextField txNomeProduto, txValorProduto, txDescProduto, txQtdeProduto;
		JComboBox<String> cbIdCategoriaProduto;
		JButton btSalvar;
		
		lbProduto = new JLabel();
		lbProduto.setBounds(20, 10, 360, 30);
		lbProduto.setFont(new Font("", Font.BOLD, 20));
		lbProduto.setText("Criando Produto");
		janelaProduto.add(lbProduto);
		
		lbNomeProduto = new JLabel();
		lbNomeProduto.setBounds(20, 60, 40, 20);
		lbNomeProduto.setText("Nome:");
		janelaProduto.add(lbNomeProduto);
		
		txNomeProduto = new JTextField();
		txNomeProduto.setBounds(60, 60, 100, 20);
		janelaProduto.add(txNomeProduto);
		
		lbValorProduto = new JLabel();
		lbValorProduto.setBounds(180, 60, 60, 20);
		lbValorProduto.setText("Valor:");
		janelaProduto.add(lbValorProduto);
		
		txValorProduto = new JTextField();
		txValorProduto.setBounds(220, 60, 140, 20);
		janelaProduto.add(txValorProduto);
		
		lbDescProduto = new JLabel();
		lbDescProduto.setBounds(20, 90, 40, 20);
		lbDescProduto.setText("Desc:");
		janelaProduto.add(lbDescProduto);
		
		txDescProduto = new JTextField();
		txDescProduto.setBounds(60, 90, 300, 20);
		janelaProduto.add(txDescProduto);
		
		lbQtdeProduto = new JLabel();
		lbQtdeProduto.setBounds(20, 120, 40, 20);
		lbQtdeProduto.setText("Qtde:");
		janelaProduto.add(lbQtdeProduto);
		
		txQtdeProduto = new JTextField();
		txQtdeProduto.setBounds(60, 120, 100, 20);
		janelaProduto.add(txQtdeProduto);
		
		lbIdCategoriaProduto = new JLabel();
		lbIdCategoriaProduto.setBounds(180, 120, 60, 20);
		lbIdCategoriaProduto.setText("Categoria:");
		janelaProduto.add(lbIdCategoriaProduto);
		
		cbIdCategoriaProduto = new JComboBox<String>();
		cbIdCategoriaProduto.setBounds(245, 120, 115, 20);
		conex.conectar();	
		String sql = "select nomeCategoria from tbCategoria where deletedCategoria = 0";
		Statement state;
		ResultSet resultset;
		try {
			state = (Statement) Conexao.con.createStatement();
			resultset = state.executeQuery(sql);
			while(resultset.next()){
				String valor = resultset.getString(1);
				cbIdCategoriaProduto.addItem(valor);
			}
		}catch(SQLException erro){
		}
		janelaProduto.add(cbIdCategoriaProduto);
		
		btSalvar = new JButton();
		btSalvar.setBounds(20, 150, 340, 50);
		btSalvar.setText("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(verifyComand == 0) {
					conex.conectar();
					String idProdutoT = "";
					Statement state;
					ResultSet resultset;
					String sql = "select idProduto from tbProduto where deletedProduto = 0 AND nomeProduto = '"+txNomeProduto.getText()+"'";
					try {
						state = (Statement) Conexao.con.createStatement();
						resultset = state.executeQuery(sql);
						while(resultset.next()){
							idProdutoT = resultset.getString(1);
						}
					}catch(SQLException erro){
					}
					if(idProdutoT == "") {
						sql = "SELECT idCategoria FROM tbCategoria WHERE nomeCategoria = '"+cbIdCategoriaProduto.getSelectedItem()+"'";
						String idCategoria = "";
						try {
							state = (Statement) Conexao.con.createStatement();
							resultset = state.executeQuery(sql);
							while(resultset.next()){
								idCategoria = resultset.getString(1);
							}
						}catch(SQLException erro){
						}
						sql = "insert into tbProduto values(null, '"+txNomeProduto.getText()+"', '"+txValorProduto.getText()+"', '"+txDescProduto.getText()+"', '"+txQtdeProduto.getText()+"', '"+idCategoria+"',0)";
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
					conex.desconectar();
					janelaProduto.dispose();
				}else {
					conex.conectar();
					Statement state;
					String idCategoria = "";
					ResultSet resultset;
					String sql = "SELECT idCategoria FROM tbCategoria WHERE nomeCategoria = '"+cbIdCategoriaProduto.getSelectedItem()+"'";
					try {
						state = (Statement) Conexao.con.createStatement();
						resultset = state.executeQuery(sql);
						while(resultset.next()){
							idCategoria = resultset.getString(1);
						}
					}catch(SQLException erro){
					}
					sql = "UPDATE tbProduto SET nomeProduto = '"+txNomeProduto.getText()+"', valorProduto = '"+txValorProduto.getText()+"', descProduto = '"+txDescProduto.getText()+"', qtdeProduto = '"+txQtdeProduto.getText()+"', idCategoria = '"+idCategoria+"' WHERE idProduto = "+produtoClick+" ";
					try {
						state = (Statement) Conexao.con.createStatement();
						state.executeUpdate(sql);
						cadastroSucesso(1);
					}catch(SQLException erro){
						System.err.println("Got an exception! "); 
			            System.err.println(erro.getMessage());
						cadastroFalha(1);
					}
					conex.desconectar();
					janelaProduto.dispose();
				}
			}
		});
		janelaProduto.add(btSalvar);
		
		if(verifyComand == 0) {
			janelaProduto.setTitle("Cadastrar Produto");
			
		}else {
			janelaProduto.setTitle("Atualizar Produto");
			sql = "select * from tbProduto where deletedProduto = 0 AND idProduto = '"+produtoClick+"'";
			try {
				state = (Statement) Conexao.con.createStatement();
				resultset = state.executeQuery(sql);
				while(resultset.next()){
					lbProduto.setText("Atualizar Produto - "+resultset.getString(2));
					txNomeProduto.setText(resultset.getString(2));
					txValorProduto.setText(resultset.getString(3));
					txDescProduto.setText(resultset.getString(4));
					txQtdeProduto.setText(resultset.getString(5));
					cbIdCategoriaProduto.setSelectedIndex(Integer.parseInt(resultset.getString(6))-1);
				}
			}catch(SQLException erro){
			}
			janelaProduto.dispose();
		}

		janelaProduto.setLayout(null);
		janelaProduto.setVisible(true);
	}
}
