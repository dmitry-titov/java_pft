package ru.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> fromJson = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
        Issue foundIssue = fromJson.stream().filter((i) -> i.getId() == issueId).findFirst().get();
        return !(foundIssue.getStateName().equals("Resolved") || foundIssue.getStateName().equals("Closed"));
    }
}
