package codereview.views;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import classes.Points;
import codereview.viewsoverride.CR_Composite;

public class SendReviewScreen extends CR_Composite {
	private Text codeTextInput;
	private Text commentsTextInput;

	public SendReviewScreen(Composite parent, int style, Player player) {
		super(parent, style);
		
		ScrolledComposite CodeScrolledComposite = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
		CodeScrolledComposite.setBounds(10, 68, 226, 163);
		CodeScrolledComposite.setExpandHorizontal(true);
		CodeScrolledComposite.setExpandVertical(true);
		
		codeTextInput = new Text(CodeScrolledComposite, SWT.WRAP | SWT.MULTI);
		CodeScrolledComposite.setContent(codeTextInput);
		CodeScrolledComposite.setMinSize(codeTextInput.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite CommentScrolledComposite = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
		CommentScrolledComposite.setBounds(10, 296, 226, 85);
		CommentScrolledComposite.setExpandHorizontal(true);
		CommentScrolledComposite.setExpandVertical(true);
		
		commentsTextInput = new Text(CommentScrolledComposite, SWT.WRAP);
		CommentScrolledComposite.setContent(commentsTextInput);
		CommentScrolledComposite.setMinSize(commentsTextInput.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button CopyCodeBtn = new Button(this, SWT.NONE);
		CopyCodeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String textFromEditor = getSelectedText();
				codeTextInput.setText(textFromEditor);
			}
		});
		CopyCodeBtn.setBounds(161, 237, 75, 25);
		CopyCodeBtn.setText("Copy Code");
		
		Button SendSegmentBtn = new Button(this, SWT.NONE);
		SendSegmentBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				player.addPoints(Points.POINTS_FOR_CREATING_NEW_REVIEW);
				try {
					MainScreen.handler.saveSegment(MainScreen.getPlayer(), codeTextInput.getText(), commentsTextInput.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				codeTextInput.setText("");
				commentsTextInput.setText("");
			}
		});
		SendSegmentBtn.setBounds(147, 387, 89, 25);
		SendSegmentBtn.setText("Be Reviewed");
		
		Label LogoLabal = new Label(this, SWT.BORDER);
		LogoLabal.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		LogoLabal.setAlignment(SWT.CENTER);
		LogoLabal.setBounds(10, 10, 140, 30);
		LogoLabal.setText("Ask For A Review");
		
		Label TheCodeLbl = new Label(this, SWT.NONE);
		TheCodeLbl.setBounds(10, 46, 66, 15);
		TheCodeLbl.setText("The Code:");
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeScoreScreen();
				MainScreen.initializeCreatorScreen();
			}
		});
		backBtn.setImage(SWTResourceManager.getImage(SendReviewScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(204, 10, 25, 25);
		
		Label commentsLbl = new Label(this, SWT.NONE);
		commentsLbl.setBounds(10, 275, 66, 15);
		commentsLbl.setText("Comments:");

	}
	
	public String getSelectedText(){
		ITextEditor ite = getActiveTextEditor();
		ITextSelection sel = getSelection(ite);
		return sel.getText()==""? "NO TEXT SELECTED":sel.getText();
	}

	public String getEditorContent(){
		ITextEditor ite = getActiveTextEditor();
		if(ite == null) return "No Editor Open!";

		IDocument doc = ite.getDocumentProvider().getDocument(ite.getEditorInput());
		if (doc == null) return "No Editor Open!";
		return doc.get();
	}

	protected ITextEditor getActiveTextEditor() {
		final IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (!(activeEditor instanceof ITextEditor)){
			return null;
		}
		ITextEditor ite = (ITextEditor)activeEditor;
		return ite;
	}

	public ITextSelection getSelection(ITextEditor textEditor) { 
		ISelectionProvider sp = textEditor.getSelectionProvider(); 
		return sp==null ? null : (ITextSelection) sp.getSelection(); 
	}
}
