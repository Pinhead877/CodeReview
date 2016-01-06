package codereview.viewsoverride;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabelButton extends Label{
	
	private Label label;
	private Composite parent;

	public LabelButton(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
		label = new Label(parent, style);
		label.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				label.setBackground(new Color(null, 220, 220, 220, 255));
				label.setForeground(new Color(null, 0, 0, 255));
				label.setCursor(new Cursor(null, SWT.CURSOR_HAND));
			}
			public void mouseExit(MouseEvent e){
				label.setBackground(new Color(null, 240, 240, 240, 255));
				label.setForeground(new Color(null, 0, 0, 0));
				label.setCursor(new Cursor(null, SWT.CURSOR_ARROW));
			}
		});
		label.setSize(60, 20);
	}

	public void setBounds(int x, int y, int width, int height) {
		label.setBounds(x, y, width, height);
	}

	public void setText(String text) {
		label.setText(text);
	}
	
	public void addMouseListener (MouseListener listener) {
		label.addMouseListener(listener);
	}
	
	public void setImage (Image image) {
		label.setImage(image);
	}
	
	public void setFont (Font font) {
		label.setFont(font);
	}
	
	public void setAlignment (int alignment) {
		label.setAlignment(alignment);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
