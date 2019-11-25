package main.utility;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.io.IOException;
import java.security.GeneralSecurityException;

//TODO: finish Javadoc comments at <desc. here>'s

/**
 * <desc. here>
 *
 * @author patrickblais
 */
public class GoogleSearch {

    private static final String cx = "002863338591013580601:_y2hzypvl08";
    private Customsearch cs;

    public GoogleSearch() {
        try {
            cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("APCS-Final")
                    .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyCVtLCJjRR6cBrOnyXK82ksmAL8v0-HLW8"))
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <desc. here>
     *
     * @param question String that is the question to be Google searched <desc. here>
     * @param answers  Array of three strings that are to be Google searched <desc. here>
     * @return A Search object derived from the parameters
     */
    public String search(String question, String[] answers) throws IOException {
        Customsearch.Cse.List list = cs.cse().list(question).setCx(cx);
        Search result = list.execute();
        if (result.getItems() != null)
            return getAnswer(result, answers);
        else
            return "No returns";
    }

    /**
     * <desc. here>
     *
     * @param results <desc. here>
     * @param answers <desc. here>
     * @return A String answer
     */
    private String getAnswer(Search results, String[] answers) {
            int one = 0, two = 0, three = 0;
            for (Result result : results.getItems()) {
                for (int i = 0; i < answers.length; i++) {
                    if (result.getTitle().contains(answers[i])) {
                        if (i == 0)
                            one++;
                        else if (i == 1)
                            two++;
                        else if (i == 2)
                            three++;
                    }
                }

            }
            if (one >= two && one >= three)
                return answers[0];
            else if (two >= one && two >= three)
                return answers[1];
            else if (three >= one && three >= two)
                return answers[2];
            else
                return "I'm not sure, sorry.";
    }
}