package codereview.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Cons;
import classes.Player;
import classes.Segment;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

public class SegmetsWroteList extends Composite {
	
	Segment[] segments;
	private List list;

	public SegmetsWroteList(Composite parent, int style, Player player) {
		super(parent, style);

		Label lblSegmentsList = new Label(this, SWT.BORDER);
		lblSegmentsList.setText("Segments List");
		lblSegmentsList.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSegmentsList.setAlignment(SWT.CENTER);
		lblSegmentsList.setBounds(10, 10, 194, 30);

		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(SegmetsWroteList.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);
		backBtn.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				setVisible(false);
				MainScreen.initializeCreatorScreen();
			}
		});

		Label explainLbl = new Label(this, SWT.NONE);
		explainLbl.setBounds(10, 46, 225, 55);
		explainLbl.setText("Explaination about what to do...");


		segments = null;
		String [] items = null;
		try {
			segments = MainScreen.handler.getSegmentsByPlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		list.setBounds(10, 107, 225, 251);
		if(segments==null){
			explainLbl.setText("Error loading data...");
		}
		else if(segments.length==0){
			explainLbl.setText("No new segments to review...");
		}else{
			items = new String[segments.length];
			for(int i=0;i<segments.length;i++){
				items[i] = segments[i].getCode();
			}
			list.setItems(items);
			list.addListener(SWT.MouseDoubleClick, new Listener() {
				@Override
				public void handleEvent(Event arg0) {
					int [] selected = list.getSelectionIndices();
					setVisible(false);
					MainScreen.initializeSegmentReviewViewScreen(segments[selected[0]],null, Cons.SEGMENT_VIEW, Cons.SEGMENTS_WROTE_SCREEN);
				}
			});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
