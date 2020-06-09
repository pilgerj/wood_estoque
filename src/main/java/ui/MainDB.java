package ui;

import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.EstoqueRepository;
import domain.OperacaoRepository;
import infraestructure.EstoqueDAO;
import infraestructure.EstoqueDBDAO;
import infraestructure.EstoqueRepositoryImplementation;
import infraestructure.OperacaoDAO;
import infraestructure.OperacaoDBDAO;
import infraestructure.OperacaoRepositoryImplementation;

public class MainDB {

	
	public static void main(String[] args) {
		
		EstoqueDAO estoqueDAO = new EstoqueDBDAO();
		EstoqueRepositoryImplementation estoqueRepositoryImplementation = new EstoqueRepositoryImplementation(estoqueDAO);
		EstoqueRepository estoqueRepository = estoqueRepositoryImplementation;
		
		OperacaoDAO operacaoDAO = new OperacaoDBDAO();
		OperacaoRepositoryImplementation operacaoRepositoryImplementation = new OperacaoRepositoryImplementation(operacaoDAO);
		OperacaoRepository operacaoRepository = operacaoRepositoryImplementation;
		
		ControleEstoque ce = new ControleEstoque(operacaoRepository, estoqueRepository);
		ControleCadastro cc = new ControleCadastro(estoqueRepository);
		
		new TesteUI(ce, cc);
	}
}
