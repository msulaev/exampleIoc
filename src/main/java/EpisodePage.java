import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

class EpisodePage {
    private SelenideElement startIcon = $(".mejs-play");
    private SelenideElement stopIcon = $(".mejs-pause");
    private SelenideElement showButton = $(".btn");
    private SelenideElement alertForAnonymous = $(".alert");

   public boolean canPlayEpisode() {
        startIcon.click();
        return stopIcon.exists();
    }

    public boolean canStopEpisode() {
        stopIcon.click();
        return startIcon.exists();
    }

    public void clickToShowCommentButton() {
        showButton.click();
    }

    String getAlertMessage() {
        return alertForAnonymous.getText();
    }
}
