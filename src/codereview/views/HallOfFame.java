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

import classes.Cons;
import classes.Player;
import codereview.data.DataHandler;

public class HallOfFame extends Composite {
	
	private static final int NUM_LABEL_SIZE = 30;
	private static final int NUM_LABEL_WIDTH = 35;
	private static final int NAME_LABEL_WIDTH = 150;
	private static final int NAME_LABEL_HEIGHT = 30;
	private static final int GAP = 3;
	
	private static final Point NUM_START = new Point(10, 46);
	private static final Point NAME_START = new Point(NUM_START.x+NUM_LABEL_SIZE+GAP, NUM_START.y);
	
	private Label[] numLabels = new Label[Cons.NUM_PLAYERS_IN_HALL_OF_FAME];
	private Label[] nameLabels = new Label[Cons.NUM_PLAYERS_IN_HALL_OF_FAME];
	private Label[] pointsLabels = new Label[Cons.NUM_PLAYERS_IN_HALL_OF_FAME];
	
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
			numLabels[index].setText((index+1)+"");
			numLabels[index].setBounds(NUM_START.x, y, NUM_LABEL_SIZE, NUM_LABEL_SIZE);
			y+=NUM_LABEL_SIZE+GAP;
		}
		
		try {
			ArrayList<Player> players = new DataHandler().getAllPlayers();
			y = NAME_START.y;
			for(int i=0;i<nameLabels.length;i++){
				nameLabels[i] = new Label(this, SWT.NONE);
				nameLabels[i].setAlignment(SWT.LEFT);
				int fontStyle = SWT.NORMAL;
				if(i<players.size() && MainScreen.getPlayer().getId()==players.get(i).getId())
					fontStyle = SWT.BOLD;
				nameLabels[i].setFont(SWTResourceManager.getFont("Microsoft JhengHei UI", 14, fontStyle));
				nameLabels[i].setBounds(NAME_START.x, y, NAME_LABEL_WIDTH, NAME_LABEL_HEIGHT);
				
				pointsLabels[i] = new Label(this, SWT.NONE);
				pointsLabels[i].setAlignment(SWT.RIGHT);
				pointsLabels[i].setFont(SWTResourceManager.getFont("Microsoft JhengHei UI", 14, fontStyle));
				pointsLabels[i].setBounds(NAME_START.x+NAME_LABEL_WIDTH+GAP,y, NUM_LABEL_WIDTH, NUM_LABEL_SIZE);
				try {
						nameLabels[i].setText(players.get(i).getName()+"");
						pointsLabels[i].setText(players.get(i).getPoints()+"");
				} catch (Exception e1) {
					nameLabels[i].setText("-- --");
					pointsLabels[i].setText(":(");
				}
				y+=NAME_LABEL_HEIGHT+GAP;
			}
		} catch (Exception e1) {
			//TODO - add error message in a label
			e1.printStackTrace();
		}
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
