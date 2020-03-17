package org.fundacionjala.pivotal.stepdefinitions.ui.workspaces;


import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.fundacionjala.pivotal.selenium.pages.Dashboard;
import org.fundacionjala.pivotal.selenium.pages.common.Navigator;
import org.fundacionjala.pivotal.selenium.pages.workspaces.WorkSpacesSettings;
import org.fundacionjala.pivotal.selenium.pages.workspaces.Workspaces;


import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Administrator on 6/20/2017.
 */
public class WorkspacesSteps {

    private Dashboard dashBoard;

    private Workspaces workSpaces;

    private WorkSpacesSettings workSpacesSettings;


    /**
     * Constructor.
     *
     * @param dashboard          class
     * @param workspaces         class
     * @param workSpacesSettings class
     */
    public WorkspacesSteps(final Dashboard dashboard, final Workspaces workspaces, final WorkSpacesSettings
            workSpacesSettings) {
        this.dashBoard = dashboard;
        this.workSpaces = workspaces;
        this.workSpacesSettings = workSpacesSettings;
    }

    /**
     * Method.
     *
     * @param workspaceName the name.
     */
    @Then("^\"([^\"]*)\" should be displayed in dashboard workspace$")
    public void shouldBeDisplayedInDashboardWorkspace(final String workspaceName) {
        Navigator.goToDashboard();
        dashBoard.clicOnWorkSpaces();
        workSpaces.selectAworkSpace(workspaceName);
        Navigator.goToDashboard();
        dashBoard.clicOnWorkSpaces();
        assertTrue(workSpaces.verifyIfAworkSpaceExist(workspaceName));
    }

    /**
     * METHOD.
     *
     * @param workspaceName the name
     * @param newName       the nnew name.
     */
    @When("^I navigate dashboard workspace and update the \"([^\"]*)\" with \"([^\"]*)\"$")
    public void iNavigateDashboardWorkspaceAndUpdateTheWith(final String workspaceName, final String newName) {
        Navigator.goToDashboard();
        dashBoard.clicOnWorkSpaces();
        workSpacesSettings = workSpaces.editWorkSpace(workspaceName);
        workSpacesSettings.updateWorkSpaceName(newName);
        workSpacesSettings.clickOnSaveButton();
    }

    /**
     * Mehtohd.
     *
     * @param message is the message.
     */
    @Then("^Confirm message should be \"([^\"]*)\"$")
    public void confirmMessageShouldBe(final String message) {
        assertEquals(message, workSpacesSettings.verifyChangesSavedMessage());
    }

    /**
     * Method.
     *
     * @param workspaceName is the name.
     */
    @And("^project name should be \"([^\"]*)\"$")
    public void projectNameShouldBe(final String workspaceName) {
        assertTrue(workSpacesSettings.verifyWorkspaceName(workspaceName));
    }


    /**
     * Method.
     *
     * @param workspaceName is the name.
     */
    @When("^I navigate dashboard workspace and delete the \"([^\"]*)\"$")
    public void iNavigateDashboardWorkspaceAndDeleteThe(final String workspaceName) {
        dashBoard.clicOnWorkSpaces();
        workSpaces.editWorkSpace(workspaceName);
        workSpacesSettings.clickOnDeleteLink();
        workSpacesSettings.clickOnConfirmDeleteButton();
    }


    /**
     * Method.
     */
    @When("^I navigate to dashboard workspace$")
    public void iNavigateToDashboardWorkspace() {
        dashBoard.clicOnWorkSpaces();
    }

    /**
     * Method.
     *
     * @param body are the fields.
     */
    @And("^create a new worksapce as:$")
    public void createANewWorksapceAs(final Map<String, String> body) {
        workSpaces.clickOnCreateWorkspaceButton();
        workSpaces.setWorkspaceName(body.get("Name"));
        workSpaces.clickOnWorkspaceSubmitButton();
    }

}

