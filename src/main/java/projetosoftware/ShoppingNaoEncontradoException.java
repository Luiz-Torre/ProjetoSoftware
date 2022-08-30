package projetosoftware;

public class ShoppingNaoEncontradoException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public ShoppingNaoEncontradoException(String msg)
	{	super(msg);
	}

	public ShoppingNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	