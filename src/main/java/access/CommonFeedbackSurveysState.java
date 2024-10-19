package access;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

@State(name = "CommonFeedbackSurveysState", storages = @Storage("feedbackSurveys.xml"))
public class CommonFeedbackSurveysState implements PersistentStateComponent<CommonFeedbackSurveysState> {
    private String surveyId;  // Example property
    private String feedback;   // Another example property

    // Getter for surveyId
    public String getSurveyId() {
        return surveyId;
    }

    // Setter for surveyId
    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    // Getter for feedback
    public String getFeedback() {
        return feedback;
    }

    // Setter for feedback
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public CommonFeedbackSurveysState getState() {
        return this;
    }

    @Override
    public void loadState(CommonFeedbackSurveysState state) {
        this.surveyId = state.surveyId;
        this.feedback = state.feedback;
    }
}
