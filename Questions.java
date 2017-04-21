// Assignment: GroupProject
// Program:    Questions
// Created:    Apr 20, 2017
// Author:     Benjamin Newbold
//
/**
 * 
 */
package triviaGame;

/**
 * CLASS 
 * @author Benjamin Newbold
 *
 */
public class Questions {
	private String category;
	private String question;
	private String answer;
	private String wrong1;
	private String wrong2;
	private String wrong3;
	/**
	 * CONSTRUCTOR Questions
	 * @param category
	 * @param question
	 * @param answer
	 * @param wrong1
	 * @param wrong2
	 * @param wrong3
	 */
	public Questions(String c, String q, String a, String w1, String w2, String w3) {
		super();
		this.category = c;
		this.question = q;
		this.answer = a;
		this.wrong1 = w1;
		this.wrong2 = w2;
		this.wrong3 = w3;
	}
	/**
	 * @return the category
	 */
	public String getcategory() {
		return category;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @return the wrong1
	 */
	public String getWrong1() {
		return wrong1;
	}
	/**
	 * @return the wrong2
	 */
	public String getWrong2() {
		return wrong2;
	}
	/**
	 * @return the wrong3
	 */
	public String getWrong3() {
		return wrong3;
	}
}

