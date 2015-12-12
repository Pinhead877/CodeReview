package codereview.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import classes.Segment;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class ReviewsList extends Composite {

	public ReviewsList(Composite parent, int style, Player player) {
		super(parent, style);

		Label lblSegmentsList = new Label(this, SWT.BORDER);
		lblSegmentsList.setText("Segments List");
		lblSegmentsList.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSegmentsList.setAlignment(SWT.CENTER);
		lblSegmentsList.setBounds(10, 10, 194, 30);

		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(ReviewsList.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);
		backBtn.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				setVisible(false);
				MainScreen.initializeMainMenu();
			}
		});

		Label explainLbl = new Label(this, SWT.NONE);
		explainLbl.setBounds(10, 46, 225, 55);
		explainLbl.setText("Explaination about what to do...");


		Segment[] segments = null;
		String [] items = null;
		try {
			segments = MainScreen.handler.getSegmentsByPlayer(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(segments!=null){
			items = new String[segments.length];
			for(int i=0;i<segments.length;i++){
				items[i] = segments[i].getCode();
			}

			List list = new List(this, SWT.BORDER);
			list.setBounds(10, 107, 225, 321);
			list.setItems(items);
			list.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event arg0) {
					int [] selected = list.getSelectionIndices();
					for(int i=0;i<selected.length;i++)
						System.out.println(selected[i]);
				}
			});
		}else{
			explainLbl.setText("Error loading data...");
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
