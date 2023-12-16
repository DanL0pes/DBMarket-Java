import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FormularioPrincipal extends JFrame{
	private JMenuBar barraMenu;
	private JMenu menuArquivo, menuCadastrar, menuVisualizar, menuAjuda;
	private JMenuItem mItemSair, mItemCategoriaCadastro, mItemProdutoCadastro, mItemSobre;
	
	public FormularioPrincipal() {
		this.setSize(1920,1080);
		this.setTitle("ETEC de Guaianazes");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel lbBemVindo = new JLabel();
		lbBemVindo.setFont(new Font("", Font.BOLD, 30));
		lbBemVindo.setText("Bem-Vindo ao Banco de Dados - Loja");
		lbBemVindo.setBounds(20, 20, 1920, 40);
		add(lbBemVindo);
		
		menuArquivo = new JMenu("Arquivo");
		menuCadastrar = new JMenu("Gerenciar");
		menuAjuda = new JMenu("Ajuda");
		
		mItemSair = new JMenuItem("Sair");
		mItemCategoriaCadastro = new JMenuItem("Categoria");
		mItemProdutoCadastro = new JMenuItem("Produto");
		
		mItemSobre = new JMenuItem("Sobre");
		
		menuArquivo.add(mItemSair);
		
		menuCadastrar.add(mItemCategoriaCadastro);
		menuCadastrar.add(mItemProdutoCadastro);
		
		menuAjuda.add(mItemSobre);

		mItemCategoriaCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastroCategoria cadastroCategora = new CadastroCategoria();
			}
		});
		mItemProdutoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CadastroProduto cadastroProduto = new CadastroProduto();
			}
		});
		mItemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Sobre sobre = new Sobre();
			}
		});
		mItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				close();
			}
		});
		
		barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		barraMenu.add(menuArquivo);
		barraMenu.add(menuCadastrar);
		barraMenu.add(menuAjuda);

		this.setLayout(null);
		this.setVisible(true);
		
	}
	
	private void close() {
		this.dispose();
	}
}
