package projetosoftware;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

public class JPAShoppingDAO implements ShoppingDAO
{	
	public long inclui(Shopping umShopping) 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	// transiente - objeto novo: ainda n�o persistente
			// persistente - apos ser persistido 
			// destacado - objeto persistente n�o vinculado a um entity manager


			//Criando a sess�o
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
			//Colocando ele para persistencia
			em.persist(umShopping);
			
			tx.commit();
			return umShopping.getId();
		} 
		catch(RuntimeException e)
		{	if (tx != null)
			{	
				try
				{	tx.rollback();
				}
				catch(RuntimeException he)
				{ }
			}
			throw e;
		}
		finally
		{	
			em.close();
		}
	}

	public void altera(Shopping umShopping) throws ShoppingNaoEncontradoException
	{	EntityManager em = null;
		EntityTransaction tx = null;
		Shopping shopping = null;
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
==>
			
			if(produto == null)
			{
==>	
==>
			}
==>	
			tx.commit();
		} 
		catch(RuntimeException e)
		{ 
			if (tx != null)
		    {   
				try
		        {	tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public void exclui(long numero) throws ProdutoNaoEncontradoException 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();

==>			Produto produto = em.find(Produto.class, new Long(numero), LockModeType.PESSIMISTIC_WRITE);
			
			if(produto == null)
			{	tx.rollback();
				throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
			}

==>			
			tx.commit();
		} 
		catch(RuntimeException e)
		{   
			if (tx != null)
		    {   
				try
		        {	tx.rollback();
		        }
		        catch(RuntimeException he)
		        { }
		    }
		    throw e;
		}
		finally
		{   em.close();
		}
	}

	public Produto recuperaUmProduto(long numero) throws ProdutoNaoEncontradoException
	{	EntityManager em = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();

==>			Produto umProduto = em.find(Produto.class, numero);
			
			// Caracter�sticas no m�todo find():
			// 1. � gen�rico: n�o requer um cast.
			// 2. Retorna null caso a linha n�o seja encontrada no banco.

			if(umProduto == null)
			{	throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
			}
			return umProduto;
		} 
		finally
		{   em.close();
		}
	}

	public List<Produto> recuperaProdutos()
	{	EntityManager em = null;
		
		try
		{	em = FabricaDeEntityManager.criarSessao();

==>

			// Retorna um List vazio caso a tabela correspondente esteja vazia.
			
			return produtos;
		} 
		finally
		{   em.close();
		}
	}
}
