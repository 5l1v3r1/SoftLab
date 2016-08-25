package mj.error;

import java.lang.*;

/*
 * The ErrorHander Class uses the singleton pattern to print error
 * @author Damien
 */
public class ErrorHandler {

    public static final String ERROR_TEMPLATE_CONSOLE = "\033[32m\033[44m Error occurred at %s on token of kind %d: \n"
            + "\033[32m\033[44m Token String: %s \n"
            + "\033[32m\033[44m Token value: %d \n"
            + "\033[32m\033[44m Program message: %s \n"
            + "\033[32m\033[44m Exception message: %s \n"
            + "------------------------------------------------------------------------------------------- \n";

    //The Template used to display images
    public  static String CURRENT_TEMPLATE;

    //The displayed message if it is an system exception
    private static  String systemMessage = "null";

    //The error message coming from the token
    private static  String tokenformattedString = "null";

    //The last error the handler caught
    private static Error lastError;

    /*
     * Singleton Initialiser. Console template defined by default.
     */
    public static void Init(String Template) {
        CURRENT_TEMPLATE = Template;

        if(Template == null) CURRENT_TEMPLATE = ERROR_TEMPLATE_CONSOLE;
    }

    /*
     * Handles error as soon as there are detected.
     * The handler keeps the last error and print the information using the current template.
     */
    public static void Handle(Error error) {
        lastError = error;

        if(lastError.SystemExc != null) systemMessage = lastError.SystemExc.getMessage();
        if( lastError.T.string != null) tokenformattedString = lastError.T.string;

        System.out.print(String.format(CURRENT_TEMPLATE, lastError.DatetimeOfError, lastError.T.kind,
                tokenformattedString,
                lastError.T.value,
                lastError.Msg,
                systemMessage));

        Cleanup();
    }

    /*
     * Clean some printed information.
     */
    private static void Cleanup() {
        systemMessage = "null";
        tokenformattedString = "null";
    }

}