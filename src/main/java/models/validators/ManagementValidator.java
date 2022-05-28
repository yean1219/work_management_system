package models.validators;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import actions.views.ManagementView;
import constants.MessageConst;

public class ManagementValidator {

    public static List<String> validate(ManagementView mv) {
        List<String> errors = new ArrayList<String>();

        String requesterError = validateRequester(mv.getRequester());
        if(!requesterError.equals("")) {
            errors.add(requesterError);
        }
        String contentError = validateContent(mv.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);
        }

        String DeadlineError = validateDeadline(mv.getDeadline());
        if(!DeadlineError.equals("")) {
            errors.add(DeadlineError);
        }
        return errors;

    }

    private static String validateRequester(String requester) {
        if (requester == null || requester.equals("")) {
            return MessageConst.E_NOREQUESTER.getMessage();
        }
        return"";
    }

    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        return"";

    }
    private static String validateDeadline(Timestamp Deadline) {
        if (Deadline == null) {
            return MessageConst.E_NODEADLINE.getMessage();
        }
        return"";
    }
}
