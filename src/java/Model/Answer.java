/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bassyouni
 */
public class Answer {
    private int id;
    private int userId;
    private String answer;
    private int questionId;


    public Answer(int id, int userId, String answer, int questionId) {
        this.id = id;
        this.userId = userId;
        this.answer = answer;
        this.questionId = questionId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUseriD() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    
    
}
