package common;

public class StrCadastraArduino extends Struct {

	public StrCadastraArduino(String str) throws StructException {
		super(str);
		quebra();
	}
	
	private String descricaoArduino;
	private int idArduino;
	private int qtdSensor;
	private int qtdAtuador;
	
	private void quebra() throws StructException
	{
		idArduino = getInt(3);
		descricaoArduino = getString(10);
		qtdSensor = getInt(2);
		qtdAtuador = getInt(2);
		
	}

	public String getDescricaoArduino() {
		return descricaoArduino;
	}

	public void setDescricaoArduino(String descricaoArduino) {
		this.descricaoArduino = descricaoArduino;
	}

	public int getIdArduino() {
		return idArduino;
	}

	public void setIdArduino(int idArduino) {
		this.idArduino = idArduino;
	}

}
