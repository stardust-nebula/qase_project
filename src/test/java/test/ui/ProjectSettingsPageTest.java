package test.ui;

import model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.service.LoginPageService;
import ui.service.ProjectSettingsPageService;
import ui.service.ProjectsPageService;

public class ProjectSettingsPageTest extends BaseTest{

    private static final String demoProjectName = "Demo Project";
    private ProjectSettingsPageService projectSettingsPageService;

    @BeforeClass
    public void setUp() {
        new LoginPageService().loginValidCredentials(new User());
        new ProjectsPageService()
                .clickOnMeatballsIconForProjectByName(demoProjectName)
                .clickOnSettingsOptionByProject(demoProjectName);
        projectSettingsPageService = new ProjectSettingsPageService();
    }

    @Test(testName = "Verify success alert appears on successful changing project's description")
    public void verifySuccessAlertOnChangingProjectDescription(){
        String newDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.";
        String expectedSuccessText = "Project settings were successfully updated!";
        projectSettingsPageService.changeProjectSettingsAndSave(newDescription);
        String actualSuccessText = projectSettingsPageService.getTextFromUpdateProjectSettingsSuccessAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(projectSettingsPageService.isAlertOnSuccessfulProjectUpdateAppears());
        softAssert.assertEquals(actualSuccessText, expectedSuccessText);
        softAssert.assertAll();
    }
}