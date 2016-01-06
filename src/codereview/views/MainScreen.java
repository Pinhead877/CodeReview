/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package codereview.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.*;
import codereview.data.DataHandler;
import codereview.data.Files;

public class MainScreen extends ViewPart {
	
	public static final int MAIL = 0, PASSWORD = 1;
	
	public MainScreen() {
		
	}
	
	public static ReviwerScreen reviwerScreen;
	public static CreatorScreen creatorScreen;
	public static ScoreScreen scoreScreen;
	public static MainMenu mainMenuScreen;
	public static SendSegmentScreen sendReviewScreen;
	public static SegmetsWroteList reviewsListScreen;
	public static LoginScreen loginScreen;
	public static SegmetsWroteList segmentsWroteList;
	
	public static Composite mainScreen;
	
//	private static Team team;
	private static Player player;
	
	public static DataHandler handler;
	

	@Override
	public void createPartControl(Composite parent) {
		
		try {
			handler = new DataHandler();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainScreen = parent;
		
		parent.setLayout(null);
		parent.setBackground(SWTResourceManager.getColor(240, 240, 240));
		
		try {
			login();
		} catch (Exception e) {
			initializeLoginScreen();
			e.printStackTrace();
		}
	}

	public static void firstScreen() {
		initializeScoreScreen();
		initializeMainMenu();
	}

	public static void initializeSendReviewScreen() {
		sendReviewScreen = new SendSegmentScreen(mainScreen, SWT.NONE, player);
		sendReviewScreen.setBounds(0, 0, 236, 540);
		sendReviewScreen.setVisible(true);
	}

	public static void initializeReviewerScreen(Segment seg) {
		reviwerScreen = new ReviwerScreen(mainScreen, SWT.NONE, player, seg);
		reviwerScreen.setVisible(true);
		reviwerScreen.setBounds(0, 169, 236, 368);
	}

	public static void initializeCreatorScreen() {
		creatorScreen = new CreatorScreen(mainScreen, SWT.NONE, player);
		creatorScreen.setVisible(true);
		creatorScreen.setBounds(0, 169, 236, 368);
	}

	public static void initializeMainMenu() {
		mainMenuScreen = new MainMenu(mainScreen, SWT.NONE, player);
		mainMenuScreen.setBounds(0, 169, 236, 368);
		mainMenuScreen.setVisible(true);
	}

	public static void initializeScoreScreen() {
		scoreScreen = new ScoreScreen(mainScreen, SWT.NONE, player);
		scoreScreen.setBounds(0, 0, 594, 167);
		scoreScreen.setVisible(true);
	}
	
	public static void initializeReviewsList() {
		reviewsListScreen = new SegmetsWroteList(mainScreen, SWT.NONE, player);
		reviewsListScreen.setBounds(0, 169, 236, 368);
		reviewsListScreen.setVisible(true);
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	private static void login() throws Exception{
		String [] userDetails = Files.loadLoginFile();
		if(userDetails==null){
			initializeLoginScreen();
			return;
		}
		player = new DataHandler().loadPlayer(userDetails[MAIL], userDetails[PASSWORD]);
		if(player==null){
			initializeLoginScreen();
			return;
		}
		firstScreen();
	}

	public static void initializeLoginScreen() {
		loginScreen = new LoginScreen(mainScreen, SWT.NONE);
		loginScreen.setBounds(0, 0, 246, 540);
		loginScreen.setVisible(true);
	}
	
	public static void initializeSegmentWroteList(){
		segmentsWroteList = new SegmetsWroteList(mainScreen, SWT.NONE, player);
		segmentsWroteList.setBounds(0, 169, 236, 368);
		segmentsWroteList.setVisible(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public static void setPlayer(Player player) {
		MainScreen.player = player;
	}
	
	public static void updatePlayerPoints(Player player, int points) throws Exception{
		player.addPoints(points);
		new DataHandler().updatePlayerPoints(player);
	}
	
}
