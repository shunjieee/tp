package seedu.address.account.account;

/**
 * Represents a secret question for an Account.
 * Each SecretQuestion is associated with a question and an answer.
 */
public class SecretQuestion {
    private String question;
    private String answer;

    /**
     * Constructs a SecretQuestion instance with the specified question and answer.
     *
     * @param question The question of the secret question.
     * @param answer The answer of the secret question.
     */
    public SecretQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Returns the question of the secret question.
     *
     * @return The question of the secret question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the answer of the secret question.
     *
     * @return The answer of the secret question.
     */
    public String getAnswer() {
        return answer;
    }
}
