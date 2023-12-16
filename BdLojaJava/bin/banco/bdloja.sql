-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06-Dez-2023 às 15:56
-- Versão do servidor: 10.4.28-MariaDB
-- versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdloja`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcategoria`
--

CREATE TABLE `tbcategoria` (
  `idCategoria` int(11) NOT NULL,
  `nomeCategoria` varchar(150) NOT NULL,
  `deletedCategoria` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tbcategoria`
--

INSERT INTO `tbcategoria` (`idCategoria`, `nomeCategoria`, `deletedCategoria`) VALUES
(1, 'Brinquedos', 0),
(2, 'Eletrônico', 0),
(3, 'Ferramentas', 0),
(4, 'Saúde', 0),
(5, 'Arte', 0),
(6, 'Jogos', 0),
(7, 'Beleza', 0),
(8, 'Animais Domésticos', 0),
(9, 'Ignição', 0),
(10, 'Peças Automóveis', 0),
(11, 'Roupas', 0),
(12, 'Eletrodoméstico', 0),
(13, 'Bebidas', 0),
(14, 'Comida Preparada', 0),
(15, 'Alimento Congelado', 0),
(16, 'Antiguidades', 0),
(17, 'Bandeiras', 0),
(18, 'Materiais Escolares', 0),
(19, 'Esculturas', 0),
(20, 'Roupas para Bebê', 0),
(21, 'Cursos', 0),
(22, 'X', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbproduto`
--

CREATE TABLE `tbproduto` (
  `idProduto` int(11) NOT NULL,
  `nomeProduto` varchar(150) DEFAULT NULL,
  `valorProduto` decimal(10,0) DEFAULT NULL,
  `descProduto` varchar(200) DEFAULT NULL,
  `qtdeProduto` int(11) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `deletedProduto` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tbproduto`
--

INSERT INTO `tbproduto` (`idProduto`, `nomeProduto`, `valorProduto`, `descProduto`, `qtdeProduto`, `idCategoria`, `deletedProduto`) VALUES
(1, 'Rosto Sócrates', 1999, 'Rosto de Sócrates esculpido a mão em gesso', 2, 19, 0),
(2, 'Carrinho Rx-7', 199, 'Carrinho de controle remoto de boa qualidade', 50, 1, 0),
(3, 'Chave Inglesa', 249, 'Chave Inglesa de tamanho 3', 99, 3, 0),
(4, 'Financeiro', 1000, 'Legal', 2, 21, 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tbcategoria`
--
ALTER TABLE `tbcategoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Índices para tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  ADD PRIMARY KEY (`idProduto`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbcategoria`
--
ALTER TABLE `tbcategoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  MODIFY `idProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tbproduto`
--
ALTER TABLE `tbproduto`
  ADD CONSTRAINT `tbproduto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `tbcategoria` (`idCategoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
