package teste;

import javax.swing.JFrame;

import common.Status;
import interfaceGrafica.GuiCadastraCenario;
import interfaceGrafica.ListenerGuiCadastraCenario;

public class TesteJanela {

	public static void main(String[] args) {
		
		ListenerGuiCadastraCenario list = new ListenerGuiCadastraCenario()
		{
			
			public void setVentilador(Status s)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setTemperatura(int temp)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setNomeCenario(String nomeCenario)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setLampada(int index, Status s, int value)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setJanela(Status s)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setIluminacao(int ilum)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setComodo(int index)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setArCond(Status s)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void setAquecedor(Status s)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void salvar()
			{
				// TODO Auto-generated method stub
				
			}
			
			public void isPrivado(boolean isPrivado)
			{
				// TODO Auto-generated method stub
				
			}
			
			public void cancelar()
			{
				// TODO Auto-generated method stub
				
			}

			public void setPerciana(Status s)
			{
				// TODO Auto-generated method stub
				
			}
		};
		GuiCadastraCenario t = new GuiCadastraCenario(list);
		
		//t.setVisible(true);
		JFrame j = new JFrame();
		j.setBounds(800, 300, 800, 600);
		j.add(t);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
