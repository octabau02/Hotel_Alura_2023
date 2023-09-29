package views;

import javax.swing.JOptionPane;

public class Exit extends JOptionPane{
	
	public static void showExitDialog() {
		Integer userOption = showConfirmDialog(null, "¿Desea salir?", "Confirmacion", YES_NO_OPTION);
		if(userOption == 0) {
			System.exit(0);
		}
	}

}
