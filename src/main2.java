import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

public class main2 extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public main2() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		table = new JTable();
		table.setBounds(600, 300, -597, -295);
		add(table);
		
	}

}
