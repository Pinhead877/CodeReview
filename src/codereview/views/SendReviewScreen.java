package codereview.views;

import org.eclipse.swt.widgets.Composite;

import codereview.viewsoverride.CR_Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class SendReviewScreen extends CR_Composite {
	private Text text;
	private Text text_1;

	public SendReviewScreen(Composite parent, int style) {
		super(parent, style);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 68, 226, 163);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		text = new Text(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(text);
		scrolledComposite.setMinSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setBounds(10, 296, 226, 85);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		text_1 = new Text(scrolledComposite_1, SWT.BORDER);
		scrolledComposite_1.setContent(text_1);
		scrolledComposite_1.setMinSize(text_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(161, 237, 75, 25);
		btnNewButton.setText("Copy Code");
		
		Button btnBeReviewed = new Button(this, SWT.NONE);
		btnBeReviewed.setBounds(147, 387, 89, 25);
		btnBeReviewed.setText("Be Reviewed");
		
		Label lblNewLabel = new Label(this, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 10, 140, 30);
		lblNewLabel.setText("Ask For A Review");
		
		Label lblTheCode = new Label(this, SWT.NONE);
		lblTheCode.setBounds(10, 46, 66, 15);
		lblTheCode.setText("The Code:");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setImage(SWTResourceManager.getImage(SendReviewScreen.class, "/codereview/assets/back.png"));
		btnNewButton_1.setBounds(204, 10, 25, 25);
		// TODO Auto-generated constructor stub
	}
}
