import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class DataStoreTest {

    @Steps
    DataStore datastore;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void loadAccountDataCouldResolveRightPath() {

    }

    @Test
    public void loadAccountDataHasValidData() {

    }

    @Test
    public void loadAccountDataShouldRaiseExceptionWhenAccountIsInvalid() {

    }

    @Test
    public void saveAccountDataCreatesFile() {

    }

    @Test
    public void saveAccountDataMakesDirectoryIfItIsNotExist() {

    }

    @Test
    public void loadUserDataCouldResolveRightPath() {

    }

    @Test
    public void loadUserDataHasValidData() {

    }

    @Test
    public void loadUserDataShouldRaiseExceptionWhenAccountIsInvalid() {

    }
}
