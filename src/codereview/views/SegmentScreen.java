package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Segment;
import codereview.viewsoverride.CR_Composite;

public class SegmentScreen extends CR_Composite {
	
	private Text codeText;
	private Text review;
	
	public SegmentScreen(Composite parent, int style, Segment seg) {
		super(parent, style);
		
		Label headerLabel = new Label(this, SWT.BORDER);
		headerLabel.setAlignment(SWT.CENTER);
		headerLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		headerLabel.setBounds(10, 10, 194, 30);
		headerLabel.setText("Segment");
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				//MainScreen.initializeReviewsList();
			}
		});
		backBtn.setImage(SWTResourceManager.getImage(ReviwerScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);
		
		codeText = new Text(this, SWT.BORDER | SWT.WRAP);
		codeText.setEditable(false);
		codeText.setText(seg.getCode());
		codeText.setToolTipText(seg.getComment());
		codeText.setBounds(10, 69, 226, 120);
		
		Label codeLabel = new Label(this, SWT.NONE);
		codeLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		codeLabel.setBounds(10, 46, 55, 17);
		codeLabel.setText("Code:");
		
		Label reviewLabel = new Label(this, SWT.NONE);
		reviewLabel.setText("Review:");
		reviewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		reviewLabel.setBounds(10, 195, 55, 17);
		
		review = new Text(this, SWT.BORDER | SWT.WRAP);
		review.setToolTipText((String) null);
		review.setText((String) null);
		review.setEditable(false);
		review.setBounds(9, 218, 226, 120);
		
		Label scoreNumLabel = new Label(this, SWT.NONE);
		scoreNumLabel.setAlignment(SWT.RIGHT);
		scoreNumLabel.setText("Score: 5");
		scoreNumLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		scoreNumLabel.setBounds(173, 195, 62, 17);
	}
}
