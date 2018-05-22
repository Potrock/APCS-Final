package main.utility;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleSearch {
    private static final String cx = "002863338591013580601:_y2hzypvl08";
    private static Customsearch cs;

    static {
        try {
            cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("APCS-Final")
                    .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyCVtLCJjRR6cBrOnyXK82ksmAL8v0-HLW8"))
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }


    public static String search(String question, String[] answers) throws IOException {
        if (question.contains("Which of")) {
            Customsearch.Cse.List list = cs.cse().list(question).setCx(cx).setFields("queries(request(totalResults))");
            Search result = list.execute();
            if (result.getItems() != null) {
                return getAnswer(result, answers, 2);
            }
        } else {
            Customsearch.Cse.List list = cs.cse().list(question).setCx(cx);
            Search result = list.execute();
            if (result.getItems() != null) {
                return getAnswer(result, answers, 1);
            } else {
                return "No returns";
            }
        }
        return null;
    }

    private static String getAnswer(Search results, String[] answers, int method) {
        if (method == 1) {
            int one = 0, two = 0, three = 0;
            for (Result result : results.getItems()) {
                for (int i = 0; i < answers.length; i++) {
                    if (result.getTitle().contains(answers[i])) {
                        if (i == 0) {
                            one++;
                        } else if (i == 1) {
                            two++;
                        } else if (i == 2) {
                            three++;
                        }
                    }
                }

            }
            if (one >= two && one >= three) {
                return answers[0];
            } else if (two >= one && two >= three) {
                return answers[1];
            } else if (three >= one && three >= two) {
                return answers[2];
            } else {
                return "I'm not sure, sorry.";
            }
        } else if (method == 2) {
            int one = Integer.parseInt(answers[0]);
            int two = Integer.parseInt(answers[1]);
            int three = Integer.parseInt(answers[2]);

            if (one > two && one > three) {
                return "The first one";
            } else if (two > one && two > three) {
                return "The second one";
            } else if (three > one && three > two) {
                return "The third one";
            }
        }
        return "Whoops";
    }
}