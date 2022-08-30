package projetosoftware;

import java.util.List;


public interface ShoppingDAO
{	
	long inclui(Shopping umShopping); 

	void altera(Shopping umShopping)
		throws ShoppingNaoEncontradoException; 
	
	void exclui(long id) 
		throws ShoppingNaoEncontradoException; 
	
	Shopping recuperaShopping(long numero) 
		throws ShoppingNaoEncontradoException; 
	
	List<Shopping> recuperaShoppings();
}