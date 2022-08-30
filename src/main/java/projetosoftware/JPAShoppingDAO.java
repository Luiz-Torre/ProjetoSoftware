package projetosoftware;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class JPAShoppingDAO implements ShoppingDAO
{	
	public long inclui(Shopping umShopping) 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	// transiente - objeto novo: ainda não persistente
			// persistente - apos ser persistido 
			// destacado - objeto persistente não vinculado a um entity manager


			//Criando a sessão
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

	public void altera(Shopping umShopping) throws ShoppingNaoEncontradoException, VersaoObsoletaException
	{	EntityManager em = null;
		EntityTransaction tx = null;
		Shopping shopping = null;
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();
			
			
			shopping = em.find(Shopping.class,  umShopping.getId(), LockModeType.PESSIMISTIC_WRITE);
			
			if(shopping == null)
			{
				tx.rollback();
				throw new ShoppingNaoEncontradoException("Shopping não encontrado");
			}
			em.merge(umShopping); 
			tx.commit();
		} 
		catch(OptimisticLockException e) {
			if(tx != null) {
				tx.rollback();
			} throw new VersaoObsoletaException("Erro ao efetuar a operação devido ao fato de os dados terem sidos alterados por outro usuário recentemente");
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

	public void exclui(long numero) throws ShoppingNaoEncontradoException 
	{	EntityManager em = null;
		EntityTransaction tx = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();
			tx = em.getTransaction();
			tx.begin();

			Shopping shopping = em.find(Shopping.class, new Long(numero));
			
			if(shopping == null)
			{	tx.rollback();
				throw new ShoppingNaoEncontradoException("Shopping não encontrado");
			}

			em.remove(shopping);
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

	public Shopping recuperaShopping(long numero) throws ShoppingNaoEncontradoException
	{	EntityManager em = null;
		
		try
		{	
			em = FabricaDeEntityManager.criarSessao();

			Shopping umShopping = em.find(Shopping.class, numero);
			
			// Características no método find():
			// 1. É genérico: não requer um cast.
			// 2. Retorna null caso a linha não seja encontrada no banco.

			if(umShopping == null)
			{	throw new ShoppingNaoEncontradoException("Shopping não encontrado");
			}
			return umShopping ;
		} 
		finally
		{   em.close();
		}
	}

	public List<Shopping> recuperaShoppings()
	{	EntityManager em = null;
		
		try
		{	em = FabricaDeEntityManager.criarSessao();


			@SuppressWarnings("unchecked")
			List<Shopping> shopping = em.createQuery("select s from Shopping s order by s.id").getResultList();
			// Retorna um List vazio caso a tabela correspondente esteja vazia.
			
			return shopping;
		} 
		finally
		{   em.close();
		}
	}
}
