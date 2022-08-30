package projetosoftware;

public class VersaoObsoletaException extends Exception {

private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public VersaoObsoletaException(String msg)
	{	super(msg);
	}

	public VersaoObsoletaException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}
