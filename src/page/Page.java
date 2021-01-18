package page;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public abstract class Page extends JPanel{
	private static final long serialVersionUID = 1L;

	public Page() {
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        this.setSize(300, 500);
        for(int i=0; i<2; i++) {
	    	for(int j=0; j<20; j++) {
	    		Component empty = new JPanel();
	    		if(i==0) this.add(empty, createConstrains(0, j));
	    		if(i==1) this.add(empty, createConstrains(j, 0));
	    	}
        }
	}
	
	protected GridBagConstraints createConstrains(int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 0;
        c.ipady = 0;
        return c;
	}
}
