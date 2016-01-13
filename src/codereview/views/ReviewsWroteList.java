package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import classes.Review;
import codereview.viewsoverride.CR_Composite;

public class ReviewsWroteList extends CR_Composite {

	public ReviewsWroteList(Composite parent, int style, Player player) {
		super(parent, style);

		Label explainTextLbl = new Label(this, SWT.NONE);
		explainTextLbl.setText("Explaination about what to do...");
		explainTextLbl.setBounds(10, 46, 225, 55);

		List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		list.setBounds(10, 107, 225, 251);
		Review [] reviews = null;
		try {
			reviews = MainScreen.handler.getReviewsByPlayer(player);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if(reviews == null){
			explainTextLbl.setText("Error loading data...");
		}else if(reviews.length==0){
			explainTextLbl.setText("No reviews...");
		}else{
			String [] titles = new String[reviews.length];
			for(int i=0;i<reviews.length;i++){
				titles[i] = "Review ID: "+reviews[i].getRevId()+" Score: "+reviews[i].getScore();
			}
			list.setItems(titles);
			list.addListener(SWT.MouseDoubleClick, new Listener() {
				@Override
				public void handleEvent(Event arg0) {
					int [] selected = list.getSelectionIndices();
					//setVisible(false);
					//TODO - add the action for the click
				}
			});
		}

		Label headerLabel = new Label(this, SWT.BORDER);
		headerLabel.setText("Reviews List");
		headerLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		headerLabel.setAlignment(SWT.CENTER);
		headerLabel.setBounds(10, 10, 194, 30);

		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(ReviewsWroteList.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);
		backBtn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				setVisible(false);
				MainScreen.initializeMainMenu();
			}
		});

	}

}
