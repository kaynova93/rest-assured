package ibs.org.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@API"),
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ibs/org/steps"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, pretty")
})
public class RunnerApi {
}
