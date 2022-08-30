package projetosoftware;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@SuppressWarnings("unused")
@Entity
@Table(name="shopping")


public class Shopping
{	
	private Long id;
	private String nome;
	private String rua;
	private String bairro;
	private String estado;

	private int versao;
	
	
  


	// Construtor padr�o, obrigat�rio ter!!
	public Shopping()
	{
	}

	
	public Shopping(String nome, 
	               String rua, 
	               String bairro,
	               String estado)
	{	this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.estado = estado;
	}

	// ********* M�todos do Tipo Get *********

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId()
	{	return id;
	}
	
	@Column(name="nome")

	public String getNome()
	{	return nome;
	}
	
	@Column(name="bairro")
	public String getBairro()
	{	return bairro;
	}
	

	@Column(name="rua")
	public String getRua()
	{	return rua;
	}
	
	// @Transient � usado sinalizar que o mesmo n�o deve possuir uma tabela no BD.


	@Column(name="estado")
	public String getEstado()
	{	return estado;
	}
	
	@Version
	 public int getVersao() {
			return versao;
		}

	// ********* M�todos do Tipo Set *********

	//unused � devido ao fato de o id ser usado apenas no banco de dados, dessa forma, o aviso pode ser ignorado.
	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}

	public void setNome(String nome)
	{	this.nome = nome;
	}



	public void setVersao(int versao) {
		this.versao = versao;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}

