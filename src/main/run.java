package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class run {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Login Example");
		frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(new Canva());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBounds(10, 10, 150, 950);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Grouping");
		JMenuItem group = new GroupButton();
		JMenuItem ungroup = new UngroupButton();
		JMenuItem changeName = new ChangeNameButton();
		
		menu.add(group);
		menu.add(ungroup);
		menu.add(changeName);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		frame.add(addButtons(buttonPanel));
		frame.setVisible(true);
	}

   private static JPanel addButtons(JPanel panel) {

      ArrayList<JButton> buttList = new ArrayList<>();
      JButton selectButton = new BaseButton("Select");
      buttList.add(selectButton);
      buttList.add(new BaseButton("Association Line"));
      buttList.add(new BaseButton("Generalization Line"));
      buttList.add(new BaseButton("Composition Line"));
      buttList.add(new BaseButton("Class"));
      buttList.add(new BaseButton("Use Case"));

      for (int i = 0; i < 6; i++) {
    	  buttList.get(i).setBackground(new Color(152, 245, 255));
    	  panel.add(buttList.get(i));
      }
      selectButton.setBackground(Color.WHITE);
      
      return panel;
   }
}

