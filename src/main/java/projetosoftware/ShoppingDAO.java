package projetosoftware;

import java.util.List;


public interface ShoppingDAO
{	
	long inclui(Shopping umProduto); 

	void altera(Shopping umProduto)
		throws ShoppingNaoEncontradoException; 
	
	void exclui(long id) 
		throws ShoppingNaoEncontradoException; 
	
	Shopping recuperaumShopping(long numero) 
		throws ShoppingNaoEncontradoException; 
	
	List<Shopping> recuperaShopping();
}