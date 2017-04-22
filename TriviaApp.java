// Assignment: GroupProject
// Program:    TriviaApp
// Created:    Apr 11, 2017
// Author:     Benjamin Newbold
/**
 * 
 */
package triviaGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 * CLASS
 * 
 * @author Benjamin Newbold
 *
 */
public class TriviaApp extends JFrame {
	/* Container for all the Screens. */
	private JPanel contentPane;
	private String category;
	private int questionNum = 1;
	private int score;
	private String question;
	private static ArrayList<Player> players = new ArrayList<>();
	private String questionPart;

	/* Start Screen Fields. */
	private JPanel pnlStartNorth;
	private JLabel lblStartTitle;
	private JPanel pnlStartCenter;
	private JButton btnStartPlay;
	private JButton btnStartExit;

	/* Shared Fields */
	private JPanel pnlSharedSouth;
	private JButton btnSharedHighScores;

	/* Categories Screen Fields. */
	private JPanel pnlCategoriesNorth;
	private JPanel pnlCategoriesCenter;
	private JLabel lblCategoriesTitle;
	private JButton btnTopic1;
	private JButton btnTopic2;
	private JButton btnTopic3;
	private JButton btnTopic4;

	/* Questions Screen Fields. */
	private JPanel pnlQuestionNorth;
	private JPanel pnlQuestionCenter;
	private JLabel lblQuestionTitle;
	private JLabel lblQuestionQuestion;
	private JButton btnAnswer1;
	private JButton btnAnswer2;
	private JButton btnAnswer3;
	private JButton btnAnswer4;

	/* Scores Screen Fields. */
	private static JTextField playerNametxt;
	private JPanel pnlScoresNorth;
	private JLabel lblScoresAskName;
	private JPanel pnlScoresCenter;
	private JLabel lblScoresScore;
	private JPanel pnlScoresSouth;
	private JButton btnScoresPlayAgain;
	private JButton btnScoresHighScores;
	private JButton btnScoresExit;

	/* High Score Screen Fields */
	private JPanel pnlHighScoresNorth;
	private JLabel lblHighScoresTitle;
	private JMenuBar menuBar;
	private JMenuItem menuMovies;
	private JMenuItem menuSports;
	private JMenuItem menuVideoGames;
	private JMenuItem menuTv;
	private JPanel pnlHighScoresCenter;
	private JLabel lblHighScoresTopScores;
	private JLabel lblHighScoresTopPlayers;
	private JPanel pnlHighScoresSouth;
	private JButton btnHighScoresPlay;
	private JButton btnHighScoresExit;
	//private static int score;

	// array lists
	static List<Questions> questionList = new LinkedList<>();
	static List<Questions> sportsList = new ArrayList<>();
	static List<Questions> moviesList = new ArrayList<>();
	static List<Questions> tvList = new ArrayList<>();
	static List<Questions> videoGamesList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initializeQuestions();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaApp frame = new TriviaApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println(moviesList);
	}

	/**
	 * Create the frame.
	 */
	public TriviaApp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		createContentPane();
	}

	/**
	 * METHOD void createContentPane
	 */
	public void createContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// addStartScreen();
		// addCategoriesScreen();
		addQuestionsScreen();
		addScoresScreen();
		addHighScoresScreen();
	}

	/**
	 * METHOD void addStartScreen
	 */
	public void addStartScreen() {
		createPnlStartNorth();
		createPnlSharedSouth();
		createPnlStartCenter();
		contentPane.add(pnlSharedSouth, BorderLayout.SOUTH);
		contentPane.add(pnlStartCenter, BorderLayout.CENTER);
		contentPane.add(pnlStartNorth, BorderLayout.NORTH);
	}

	/**
	 * METHOD void createBtnSharedHighScores
	 */
	public void createBtnSharedHighScores() {
		btnSharedHighScores = new JButton("High Scores");
		btnSharedHighScores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSharedHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				addHighScoresScreen();
				revalidate();
				repaint();

			}
		});
		pnlSharedSouth.add(btnSharedHighScores);
	}

	/**
	 * METHOD void createpnlSharedSouth
	 */
	public void createPnlSharedSouth() {
		pnlSharedSouth = new JPanel();
		createBtnSharedHighScores();
	}

	public void createPlayerAndAppendToFile() {
		if (playerNametxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please Input Player Name", "Player Name Empty",
					JOptionPane.ERROR_MESSAGE);
		} else {

			Player player = new Player(playerNametxt.getText(), score, category);
			players.add(player);

			FileWriter fw = null;
			BufferedWriter bw = null;
			PrintWriter pw = null;

			try {
				fw = new FileWriter("HighScores.csv", true);
				bw = new BufferedWriter(fw);
				pw = new PrintWriter(bw);

				for (Player p : players) {
					pw.print(p.toString());
				}
			} catch (IOException e) {
				System.out.println("File Could Not Be Found");
			} finally {

				pw.close();
			}

		}
	}

	/**
	 * METHOD void createBtnStartExit
	 * 
	 * @param pnlStartCenter
	 * @param btnStartExit
	 */
	public void createBtnStartExit() {
		btnStartExit = new JButton("Exit");
		btnStartExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStartExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pnlStartCenter.add(btnStartExit);
	}

	/**
	 * METHOD void createBtnStartPlay
	 * 
	 * @param pnlStartCenter
	 * @param btnStartPlay
	 */
	public void createBtnStartPlay() {
		pnlStartCenter.setLayout(new GridLayout(1, 2, 20, 20));
		btnStartPlay = new JButton("Play");
		btnStartPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStartPlay.setSize(new Dimension(175, 60));
		btnStartPlay.setPreferredSize(new Dimension(175, 60));
		pnlStartCenter.add(btnStartPlay);
		btnStartPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		btnStartPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				addCategoriesScreen();
				revalidate();
				repaint();
			}
		});
	}

	/**
	 * METHOD void createPnlStartCenter
	 * 
	 * @param pnlStartCenter
	 */
	public void createPnlStartCenter() {
		pnlStartCenter = new JPanel();
		pnlStartCenter.setBorder(new EmptyBorder(175, 50, 175, 50));
		createBtnStartPlay();
		createBtnStartExit();
	}

	/**
	 * METHOD void createPnlStart
	 * 
	 * @param pnlStartNorth
	 * @param lblStartTitle
	 */
	public void createLblStartTitle() {
		lblStartTitle = new JLabel("Would you like to play some Trivia?");
		lblStartTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pnlStartNorth.add(lblStartTitle);
	}

	/**
	 * METHOD void createPnlStartNorth
	 * 
	 * @param pnlStartNorth
	 */
	public void createPnlStartNorth() {
		pnlStartNorth = new JPanel();
		pnlStartNorth.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		createLblStartTitle();

	}

	/**
	 * METHOD void addCategoriesScreen
	 */
	public void addCategoriesScreen() {
		createPnlCategoriesNorth();
		createPnlCategoriesCenter();
		createPnlSharedSouth();
		contentPane.add(pnlCategoriesCenter, BorderLayout.CENTER);
		contentPane.add(pnlSharedSouth, BorderLayout.SOUTH);
		contentPane.add(pnlCategoriesNorth, BorderLayout.NORTH);
	}

	/**
	 * METHOD void createBtnTopic
	 */
	public void createBtnTopic(JButton btnName, String btnCaption) {
		btnName = new JButton(btnCaption);
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = btnCaption;
				contentPane.removeAll();
				addQuestionsScreen();
				revalidate();
				repaint();
			}
		});
		btnName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlCategoriesCenter.add(btnName);
	}

	/**
	 * METHOD void createPnlCategoriesCenter
	 */
	public void createPnlCategoriesCenter() {
		pnlCategoriesCenter = new JPanel();
		pnlCategoriesCenter.setBorder(new EmptyBorder(50, 10, 50, 10));
		pnlCategoriesCenter.setLayout(new GridLayout(2, 2, 20, 20));
		createBtnTopic(btnTopic1, "Video Games");
		createBtnTopic(btnTopic2, "Sports");
		createBtnTopic(btnTopic3, "Movies");
		createBtnTopic(btnTopic4, "TV");
	}

	/**
	 * METHOD void createLblCategoriesTitle
	 */
	public void createLblCategoriesTitle() {
		lblCategoriesTitle = new JLabel("Choose your Category");
		lblCategoriesTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pnlCategoriesNorth.add(lblCategoriesTitle);
	}

	/**
	 * METHOD void createPnlCategoriesNorth
	 */
	public void createPnlCategoriesNorth() {
		pnlCategoriesNorth = new JPanel();
		pnlCategoriesNorth.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		createLblCategoriesTitle();
	}

	/**
	 * METHOD void addQuestionsScreen
	 */
	public void addQuestionsScreen() {
		createPnlQuestionNorth();
		createPnlQuestionCenter();
		contentPane.add(pnlQuestionNorth, BorderLayout.NORTH);
		contentPane.add(pnlQuestionCenter, BorderLayout.CENTER);
	}

	/**
	 * METHOD void createPnlQuestionNorth
	 */
	public void createPnlQuestionNorth() {
		pnlQuestionNorth = new JPanel();
		pnlQuestionNorth.setLayout(new GridLayout(2, 1, 0, 20));
		createLblQuestionTitle();
		createLblQuestionQuestion();
	}

	/**
	 * METHOD void createQuestionTitle
	 */
	public void createLblQuestionTitle() {
		lblQuestionTitle = new JLabel(category);
		lblQuestionTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuestionTitle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblQuestionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlQuestionNorth.add(lblQuestionTitle);
	}

	/**
	 * METHOD void createLblQuestionQuestion
	 */
	public void createLblQuestionQuestion() {
		question = "Q" + questionNum + ": " + getQuestionPart();
		lblQuestionQuestion = new JLabel(question);
		lblQuestionQuestion.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblQuestionQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		pnlQuestionNorth.add(lblQuestionQuestion);
	}

	/**
	 * METHOD String getQuestionPart
	 * 
	 * @return
	 */
	public String getQuestionPart() {
		return category.equals("TV") ? tvList.get(questionNum - 1).getQuestion()
				: category.equals("Movies") ? moviesList.get(questionNum - 1).getQuestion()
						: category.equals("Sports") ? sportsList.get(questionNum - 1).getQuestion()
								: videoGamesList.get(questionNum - 1).getQuestion();
	}

	/**
	 * METHOD void createPnlQuestionCenter
	 */
	public void createPnlQuestionCenter() {
		pnlQuestionCenter = new JPanel();
		pnlQuestionCenter.setBorder(new EmptyBorder(50, 10, 50, 10));
		pnlQuestionCenter.setLayout(new GridLayout(2, 2, 20, 20));
		createBtnQuestionAnswer(btnAnswer1, "Answer 1");
		createBtnQuestionAnswer(btnAnswer2, "Answer 2");
		createBtnQuestionAnswer(btnAnswer3, "Answer 3");
		createBtnQuestionAnswer(btnAnswer4, "Answer 4");
	}

	/**
	 * METHOD void createBtnQuestionAnswer
	 */
	public void createBtnQuestionAnswer(JButton buttonName, String answer) {
		buttonName = new JButton(answer);
		buttonName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (questionNum < 10) {
					questionNum++;
					question = "Q" + questionNum + ": How many cups of sugar does it take to get to the moon?";
					lblQuestionQuestion.setText(question);
				} else {
					questionNum = 1;
					contentPane.removeAll();
					addScoresScreen();
					revalidate();
					repaint();
				}
			}
		});
		buttonName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlQuestionCenter.add(buttonName);
	}

	/**
	 * METHOD void addScoresScreen
	 */
	public void addScoresScreen() {
		createPnlScoresNorth();
		createPnlScoresCenter();
		createPnlScoresSouth();
		contentPane.add(pnlScoresNorth, BorderLayout.NORTH);
		contentPane.add(pnlScoresCenter, BorderLayout.CENTER);
		contentPane.add(pnlScoresSouth, BorderLayout.SOUTH);

	}

	/**
	 * METHOD void createBtnScoresExit
	 */
	public void createBtnScoresExit() {
		btnScoresExit = new JButton("Exit");
		btnScoresExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnScoresExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createPlayerAndAppendToFile();
				System.exit(0);
			}

		});
		pnlScoresSouth.add(btnScoresExit);

	}

	/**
	 * METHOD void createBtnScoresHighScores
	 */
	public void createBtnScoresHighScores() {
		btnScoresHighScores = new JButton("High Scores");
		btnScoresHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerNametxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Input Player Name", "Player Name Empty",
							JOptionPane.ERROR_MESSAGE);
				} else {
					createPlayerAndAppendToFile();
					contentPane.removeAll();
					addHighScoresScreen();
					revalidate();
					repaint();
				}

			}
		});
		btnScoresHighScores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlScoresSouth.add(btnScoresHighScores);
	}

	/**
	 * METHOD void createBtnScoresPlayAgain
	 */
	public void createBtnScoresPlayAgain() {
		btnScoresPlayAgain = new JButton("Play Again?");
		btnScoresPlayAgain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnScoresPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerNametxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Input Player Name", "Player Name Empty",
							JOptionPane.ERROR_MESSAGE);
				} else {
					createPlayerAndAppendToFile();
					contentPane.removeAll();
					addCategoriesScreen();
					revalidate();
					repaint();
				}
			}
		});
		pnlScoresSouth.add(btnScoresPlayAgain);
	}

	/**
	 * METHOD void createPnlScoresSouth
	 */
	public void createPnlScoresSouth() {
		pnlScoresSouth = new JPanel();
		pnlScoresSouth.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlScoresSouth.setLayout(new GridLayout(1, 3, 10, 0));
		createBtnScoresPlayAgain();
		createBtnScoresHighScores();
		createBtnScoresExit();
	}

	/**
	 * METHOD void createLblScoresScore
	 */
	public void createLblScoresScore() {
		lblScoresScore = new JLabel("Your Score was 10/10");
		lblScoresScore.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 45));
		lblScoresScore.setHorizontalAlignment(SwingConstants.CENTER);
		pnlScoresCenter.add(lblScoresScore);
	}

	/**
	 * METHOD void createPnlScoresCenter
	 */
	public void createPnlScoresCenter() {
		pnlScoresCenter = new JPanel();
		pnlScoresCenter.setLayout(new GridLayout(0, 1, 0, 0));
		createLblScoresScore();
	}

	/**
	 * METHOD void createTxtBoxScoresName
	 */
	public void createTxtBoxScoresName() {
		playerNametxt = new JTextField();
		playerNametxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlScoresNorth.add(playerNametxt);
		playerNametxt.setColumns(10);
	}

	/**
	 * METHOD void createLblScoresAskName
	 */
	public void createLblScoresAskName() {
		lblScoresAskName = new JLabel("What is your name?");
		lblScoresAskName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlScoresNorth.add(lblScoresAskName);
	}

	/**
	 * METHOD void createPnlScoresNorth
	 */
	public void createPnlScoresNorth() {
		pnlScoresNorth = new JPanel();
		createLblScoresAskName();
		createTxtBoxScoresName();

	}

	/**
	 * METHOD void addHighScoresScreen
	 */
	public void addHighScoresScreen() {
		createPnlHighScoresNorth();
		createPnlHighScoresCenter();
		createPnlHighScoresSouth();
		contentPane.add(pnlHighScoresSouth, BorderLayout.SOUTH);
		contentPane.add(pnlHighScoresNorth, BorderLayout.NORTH);
		contentPane.add(pnlHighScoresCenter, BorderLayout.CENTER);
	}

	/**
	 * METHOD void createBtnHighScoresExit
	 */
	public void createBtnHighScoresExit() {
		btnHighScoresExit = new JButton("Exit");
		btnHighScoresExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnHighScoresExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHighScoresSouth.add(btnHighScoresExit);
	}

	/**
	 * METHOD void createBtnHighScoresPlay
	 */
	public void createBtnHighScoresPlay() {
		btnHighScoresPlay = new JButton("Play");
		btnHighScoresPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				addCategoriesScreen();
				revalidate();
				repaint();

			}
		});
		btnHighScoresPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHighScoresSouth.add(btnHighScoresPlay);
	}

	/**
	 * METHOD void createPnlHighScoresSouth
	 */
	public void createPnlHighScoresSouth() {
		pnlHighScoresSouth = new JPanel();
		pnlHighScoresSouth.setBorder(new EmptyBorder(10, 50, 10, 50));
		pnlHighScoresSouth.setLayout(new GridLayout(0, 2, 50, 0));
		createBtnHighScoresPlay();
		createBtnHighScoresExit();
	}

	/**
	 * METHOD void createLblHighScoresTopScores
	 */
	public void createLblHighScoresTopScores() {
		lblHighScoresTopScores = new JLabel(
				"<html>\r\n<b>Player Score</b><br>\r\n10/10<br>\r\n10/10<br>\r\n9/10<br>\r\n8/10<br>\r\n7/10<br>\r\n7/10<br>\r\n6/10<br>\r\n5/10<br>\r\n3/10<br>\r\n2/10</html>");
		lblHighScoresTopScores.setForeground(new Color(255, 255, 255));
		lblHighScoresTopScores.setVerticalAlignment(SwingConstants.TOP);
		lblHighScoresTopScores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHighScoresCenter.add(lblHighScoresTopScores);
	}

	/**
	 * METHOD void createLblHighScoresTopPlayers
	 */
	public void createLblHighScoresTopPlayers() {
		lblHighScoresTopPlayers = new JLabel(
				"<html>\r\n<b>Player Name</b><br>\r\nPlayer1<br>\r\nPlayer2<br>\r\nPlayer3<br>\r\nPlayer4<br>\r\nPlayer5<br>\r\nPlayer6<br>\r\nPlayer7<br>\r\nPlayer8<br>\r\nPlayer9<br>\r\nPlayer10</html>");
		lblHighScoresTopPlayers.setForeground(new Color(255, 255, 255));
		lblHighScoresTopPlayers.setVerticalAlignment(SwingConstants.TOP);
		lblHighScoresTopPlayers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHighScoresCenter.add(lblHighScoresTopPlayers);
	}

	/**
	 * METHOD void createPnlHighScoresCenter
	 */
	public void createPnlHighScoresCenter() {
		pnlHighScoresCenter = new JPanel();
		pnlHighScoresCenter.setBackground(new Color(0, 0, 255));
		pnlHighScoresCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlHighScoresCenter.setLayout(new GridLayout(0, 2, 0, 0));
		createLblHighScoresTopPlayers();
		createLblHighScoresTopScores();
	}

	/**
	 * METHOD void createMenuBar
	 */
	public void createMenuBar() {
		menuBar = new JMenuBar();
		pnlHighScoresNorth.add(menuBar);

		menuSports = new JMenuItem("Sports");
		menuSports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlHighScoresCenter.setBackground(menuSports.getBackground());
			}
		});
		menuSports.setForeground(new Color(255, 255, 255));
		menuSports.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuSports.setBackground(Color.BLUE);
		menuBar.add(menuSports);
		menuSports.setHorizontalAlignment(SwingConstants.CENTER);

		menuMovies = new JMenuItem("Movies");
		menuMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlHighScoresCenter.setBackground(menuMovies.getBackground());
			}
		});
		menuMovies.setForeground(new Color(255, 255, 255));
		menuMovies.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuMovies.setBackground(Color.RED);
		menuBar.add(menuMovies);
		menuMovies.setHorizontalAlignment(SwingConstants.CENTER);

		menuVideoGames = new JMenuItem("Video Games");
		menuVideoGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlHighScoresCenter.setBackground(menuVideoGames.getBackground());
			}
		});
		menuVideoGames.setForeground(new Color(255, 255, 255));
		menuVideoGames.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuVideoGames.setBackground(new Color(50, 205, 50));
		menuBar.add(menuVideoGames);
		menuVideoGames.setHorizontalAlignment(SwingConstants.CENTER);

		menuTv = new JMenuItem("TV");
		menuTv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlHighScoresCenter.setBackground(menuTv.getBackground());
			}
		});
		menuTv.setForeground(new Color(255, 255, 255));
		menuTv.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuTv.setBackground(new Color(255, 165, 0));
		menuBar.add(menuTv);
		menuTv.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * METHOD void createLblHighScoresTitle
	 */
	public void createLblHighScoresTitle() {
		lblHighScoresTitle = new JLabel("High Scores");
		lblHighScoresTitle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblHighScoresTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblHighScoresTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pnlHighScoresNorth.add(lblHighScoresTitle);
	}

	/**
	 * METHOD void createPnlHighScoresNorth
	 */
	public void createPnlHighScoresNorth() {
		pnlHighScoresNorth = new JPanel();
		pnlHighScoresNorth.setLayout(new GridLayout(0, 1, 0, 0));
		createLblHighScoresTitle();
		createMenuBar();
	}

	/**
	 * METHOD void initializeQuestions
	 */
	public static void initializeQuestions() {
		try (Scanner reader = new Scanner(TestingQuestions.class.getResourceAsStream("questions.csv"))) {
			while (reader.hasNext()) {
				Questions question = getQuestion(reader.nextLine());
				if (question != null) {
					questionList.add(question);
				}
			}
		}
		for (Questions q : questionList) {
			if (q.getcategory().equals("Sports")) {
				sportsList.add(q);
			} else if (q.getcategory().equals("Movies")) {
				moviesList.add(q);
			} else if (q.getcategory().equals("Video Games")) {
				videoGamesList.add(q);
			} else
				tvList.add(q);
		}
	}

	/**
	 * METHOD Questions getQuestion
	 * 
	 * @param line
	 * @return
	 */
	private static Questions getQuestion(String nextLine) {

		String[] line = nextLine.split(",");
		Questions question = null;
		try {
			question = new Questions(line[0], line[1], line[2], line[3], line[4], line[5]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			System.err.println(nextLine + ".. cound not be read in as a Question.");
		}
		return question;
	}
}
