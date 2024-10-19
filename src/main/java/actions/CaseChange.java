package actions;

import javax.swing.SwingUtilities;
import access.CommonFeedbackSurveysState;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;

public class CaseChange extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Retrieve the instance of CommonFeedbackSurveysState
        CommonFeedbackSurveysState feedbackState = ServiceManager.getService(CommonFeedbackSurveysState.class);
        try {
            // Get the current editor where the user is working
            Editor editor = e.getData(CommonDataKeys.EDITOR);
            if (editor != null) {
                // Get the document object that allows manipulation of the content
                Document document = editor.getDocument();

                // Check if the document is writable
                if (!document.isWritable()) {
                    System.out.println("Document is not writable.");
                    return;
                } else {
                    System.out.println("Document is Editable");
                }

                // Get the start and end positions of the selected text in the editor
                int start = editor.getSelectionModel().getSelectionStart();
                int end = editor.getSelectionModel().getSelectionEnd();

                // Retrieve the selected text
                String selectedText = document.getText(new TextRange(start, end));
                System.out.println("Selected Text: " + selectedText);

                // If no text is selected, return early
                if (selectedText.isEmpty()) {
                    return;
                }

                // Modify the selected text (for example, change it to uppercase)
                String modifiedText = selectedText.toUpperCase();
                System.out.println("Modified Text: " + modifiedText);

                // Log the document's content before replacement
                System.out.println("Before Replacement: " + document.getText());

                // Replace the selected text in the document using SwingUtilities to ensure this operation happens on the EDT
                SwingUtilities.invokeLater(() -> {
                    document.replaceString(start, end, modifiedText);
                    editor.getContentComponent().revalidate();
                    editor.getContentComponent().repaint(); // Force the editor to refresh
                    System.out.println("After Replacement: " + document.getText());
                });

                // Optionally store the original selected text in the feedbackState if needed
                feedbackState.setFeedback(selectedText); // Storing selected text
            }
        } catch (Exception ex) {
            // Handle exceptions by logging or displaying messages
            ex.printStackTrace();
            System.err.println("Error during case change action: " + ex.getMessage());
        }
    }
}
