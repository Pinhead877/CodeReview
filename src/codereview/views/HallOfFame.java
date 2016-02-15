package codereview.views;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import codereview.data.DataHandler;

public class HallOfFame extends Composite {
	
	private static final int NUM_LABEL_SIZE = 30;
	private static final int NAME_LABEL_WIDTH = 183;
	private static final int NAME_LABEL_HEIGHT = 30;
	private static final int GAP = 3;
	
	private static final Point NUM_START = new Point(10, 46);
	private static final Point NAME_START = new Point(46, 46);
	
	private Label[] numLabels = new Label[11];
	private Label[] nameLabels = new Label[11];
	
	public HallOfFame(Composite parent, int style) {
		super(parent, style);
		
		Label logo = new Label(this, SWT.BORDER);
		logo.setText("Hall of Fame");
		logo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		logo.setAlignment(SWT.CENTER);
		logo.setBounds(10, 10, 188, 30);
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(HallOfFame.class, "/codereview/assets/back.png"));
		backBtn.setBounds(204, 10, 25, 25);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeScoreScreen();
				MainScreen.initializeProfileScreen();
			}
		});
		
		int y = NUM_START.y;
		for(int index = 0; index<numLabels.length;index++){
			numLabels[index] = new Label(this,SWT.NONE);
			numLabels[index].setAlignment(SWT.CENTER);
			numLabels[index].setFont(SWTResourceManager.getFont("Small Fonts", 15, SWT.BOLD));
			numLabels[index].setText(index+"");
			numLabels[index].setBounds(NUM_START.x, y, NUM_LABEL_SIZE, NUM_LABEL_SIZE);
			y+=NUM_LABEL_SIZE+GAP;
		}
		
		try {
//			Object [] players = new DataHandler().getAllPlayers();
			ArrayList<Player> players = new DataHandler().getAllPlayers();
			players.sort(null);
			y = NAME_START.y;
			for(int i=0;i<nameLabels.length;i++){
				nameLabels[i] = new Label(this, SWT.NONE);
				nameLabels[i].setAlignment(SWT.CENTER);
				nameLabels[i].setFont(SWTResourceManager.getFont("Microsoft JhengHei UI", 14, SWT.NORMAL));
				nameLabels[i].setBounds(NAME_START.x, y, NAME_LABEL_WIDTH, NAME_LABEL_HEIGHT);
				try {
					if(players.get(i)!=null)
						nameLabels[i].setText(players.get(i).getName()+" "+players.get(i).getPoints());
				} catch (Exception e1) {
					nameLabels[i].setText("NA");
					e1.printStackTrace();
				}
				y+=NAME_LABEL_HEIGHT+GAP;
			}
		} catch (Exception e1) {
			//TODO - add error message in a label
			e1.printStackTrace();
		}
		
//		Label nameLabel = new Label(this, SWT.NONE);
//		nameLabel.setFont(SWTResourceManager.getFont("Microsoft JhengHei UI", 14, SWT.NORMAL));
//		nameLabel.setBounds(46, 46, 183, 30);
//		nameLabel.setText("Player Name");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
